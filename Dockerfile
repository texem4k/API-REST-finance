# =========================================================
# FASE 1 - Compilar Angular
# =========================================================
FROM node:22-alpine AS frontend-build

WORKDIR /frontend

COPY frontend/package*.json ./

RUN npm ci

COPY frontend/ ./

RUN npm run build


# =========================================================
# FASE 2 - Compilar Spring Boot
# =========================================================
FROM eclipse-temurin:17-jdk-alpine AS backend-build

WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline -B

COPY src ./src

# Copiamos el Angular compilado a los recursos estáticos de Spring Boot
COPY --from=frontend-build /frontend/dist/frontend/browser/ ./src/main/resources/static/

RUN ./mvnw clean package -DskipTests -B


# =========================================================
# FASE 3 - Ejecutar aplicación
# =========================================================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=backend-build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]