package com.apifinance.apirestfinance.model;

public class Validations {


    /**
     * Valida el nombre de usuario, debe estar compuesto por sólo letras
     * @param name
     * @return
     */
    public static boolean validateName(String name){
        return name.matches("^[a-zA-Z]{2,}$");
    }

    public static boolean validateEmail(String email){
        return email.matches("^([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,6})$");
    }

    public static boolean validatePassword(String password){
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!¡¿?*.])(?=\\S+$).{8,}$");
    }
}
