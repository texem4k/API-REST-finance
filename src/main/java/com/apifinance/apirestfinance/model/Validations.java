package com.apifinance.apirestfinance.model;

public class Validations {

    public static boolean validateName(String name){
        return name.matches("^[A-Za-z].*$");
    }

    public static boolean validateEmail(String email){
        return email.matches("^([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,6})$");
    }

    public static boolean validatePassword(String password){
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!¡¿?*.])(?=\\S+$).{8,}$");
    }
}
