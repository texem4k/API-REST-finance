package com.apifinance.apirestfinance.control.exceptions;

import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

/**
 * Clase que se encarga de todas las excepciones que puedan generar controller y service. Devolviendo un JSON legible
 */

@ControllerAdvice
public class GlobalManageExceptions extends RuntimeException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryAssigmentError.class)
    public ResponseEntity<String> manageCategoryAssigmentError(CategoryAssigmentError ex) {
        JsonObject obj = new JsonObject();
        obj.addProperty("status", 400);
        obj.addProperty("code", "Category assigment error");
        obj.addProperty("title", "Asignación de categoría fallida");
        obj.addProperty("detail", "El tipo de transacción de la transacción y el de la categoría no coinciden");
        obj.addProperty("timestamp", LocalDateTime.now().toString());

        return new ResponseEntity<>(obj.toString(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserDetailsError.class)
    public ResponseEntity<String> manageUserDetailsError(UserDetailsError ex) {
        JsonObject obj = new JsonObject();
        obj.addProperty("status", 400);
        obj.addProperty("code", "User creation error");
        obj.addProperty("title", "Datos no válidos");
        obj.addProperty("detail", "Los datos introducidos para crear al usuario no son válidos");
        obj.addProperty("timestamp", LocalDateTime.now().toString());

        return new ResponseEntity<>(obj.toString(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryNotValid.class)
    public ResponseEntity<String> manageCategoryNotValid(CategoryNotValid ex) {
        JsonObject obj = new JsonObject();
        obj.addProperty("status", 400);
        obj.addProperty("code", "Category error");
        obj.addProperty("title", "Nombre de categoría no válido");
        obj.addProperty("detail", "El nombre introducido contiene dígitos o tiene menos 4 carácteres");
        obj.addProperty("timestamp", LocalDateTime.now().toString());

        return new ResponseEntity<>(obj.toString(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserPasswordError.class)
    public ResponseEntity<String> manageUserPasswordError(UserPasswordError ex) {
        JsonObject obj = new JsonObject();
        obj.addProperty("status", 400);
        obj.addProperty("code", "User password error");
        obj.addProperty("title", "Contraseña no válida");
        obj.addProperty("detail", "La contraseña introducida no es válida, ");
        obj.addProperty("timestamp", LocalDateTime.now().toString());

        return new ResponseEntity<>(obj.toString(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserPasswordError.class)
    public ResponseEntity<String> manageTransactionDetailsError(TransactionDetailsError ex) {
        JsonObject obj = new JsonObject();
        obj.addProperty("status", 400);
        obj.addProperty("code", "Transaction creation error");
        obj.addProperty("title", "Datos de transacción no válidos");
        obj.addProperty("detail", "La transacción debe tener al menos un nombre y una cantidad");
        obj.addProperty("timestamp", LocalDateTime.now().toString());

        return new ResponseEntity<>(obj.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manageGeneralErrors(Exception ex) {
        return new ResponseEntity<>("Ocurrió un error inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
