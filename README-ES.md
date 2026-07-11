# API REST de finanzas básicas (Con Spring Boot)

## Descripción dle proyecto

La finalidad de este proyecto es aprender un poco sobre cómo desarrollar API REST en Java con Spring Boot.  
Un framework que ayuda a transformar objetos a datos relacionales SQL de forma sencilla usando anotaciones.  
Esta idea surgió por la IA Claude y por la necesidad personal de hacer algo productivo que pueda destacar,  
en mayor o menor medida, en el portfolio de Github, además de aprender algo nuevo claro.

La finalidad de este proyecto es aprender y descubrir más sobre backend, además como se dijo antes, poder  
lucir un github algo más completo ya que la uni apenas haces proyectos interesantes que puedas resaltar como portfolio.


La API está formada por 3 módulos esenciales y diferenciadores:

- Controller
  - Representa el mapping de acciones HTTP con GET y POST, estos métodos llaman a las funciones del service
  - Es la entrada de datos originada por el front-end de la página
  - Este llama a las funciones del service y les pasa los datos recibidos
- Service
  - Representa la lógica de negocio y realiza toda la lógica "pesada" de validaciones y la creación de objetos requeridos.
  - Además realiza la llamada al método correspondiente del repositorio
- Repository
  - Es una interfaz, que hereda de JPARepository, este contiene los métodos esenciales que usa Spring Boot para realizar acciones en la BD
  - Los métodos los implementa Spring Boot de manerá automática mediante reglas gramaticales, sólo se necesita definir aquellos métodos que se vayan a usar.  
  - Estas reglas son palabras reservadas.
    - De forma que si añades en el repositorio de Usuario, añades el método "findById", éste buscará los usuarios que concidan con el Id que se le pase cómo parámetro

## Estructuras de datos

Como estructura de datos tenemos:
- User
  - Representa al usuario, contiene atributos cómo nombre, email, contraseña, UUID y la lista de transacciones asociadas a él
- Transaction
  - Representa una transacción, contiene nombre, cantidad, Categoría, Tipo de transacción (Ingreso/Gasto), el dueño de la transacción y entre otros
- Category
  - Representa una categoría de transacción, estas pueden ser "Comida", "Entretenimiento", "Viajes"...
- Categorization
  - Es la relación entre una palabra clave y una categoría
  - Si en la descricpión de una transacción pone "Película Backrooms" pues la palabra clave es "película" y al categoría será "Entretenimiento"
## Características

- Usa test unitarios de JUnit para los Service
- Arquitectura MVC
  - Model -> Contiene la base de las estructuras de datos usadas
  - View -> Un fichero la estructura de los menús básicos (Sin terminar)
  - Control -> Contiene Controller, Service, Repository y Excepciones personalizadas. Con su JSON para cada excepción.
- Conexión con una base de datos Neon funcional
- Poca complejidad, contiene operaciones esenciales como añadir, eliminar y buscar usuarios/transacciones
- Sistema simple de asignación de categorías de forma automática a cada transacción en función de la descripción de la transacción
  - Por ejemplo "pizza con amigos" -> Categoría "Comida"