package com.apifinance.apirestfinance.view;

import java.util.Scanner;

public class Menus {

    private static final Scanner input = new Scanner(System.in);

    public static void showStartMenu() {
        System.out.println("Bienvenido, autentíquese (Use los dígitos 1-2 para elegir una opción):");
        System.out.println();
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar sesión mediante correo electrónico y contraseña");
        int option = input.nextInt();
        switch (option) {
            case 1:
                break;
            case 2:
                break;
            default:
                while (option > 2 || option < 1) {
                    System.out.println("Opcion no valida");
                    System.out.println();
                    option = input.nextInt();
                }

        }
    }

    public static void showHomeMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Bienvenido, autentíquese (Use los dígitos 1-2 para elegir una opción):");
        System.out.println();
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar sesión mediante correo electrónico y contraseña");
        int option = input.nextInt();
        switch (option) {
            case 1:

                break;
            case 2:
                break;
            default:
                while (option > 2 || option < 1) {
                    System.out.println("Opcion no valida");
                    System.out.println();
                    option = input.nextInt();
                }

        }
    }

    public static void registerMenu() {
        boolean usernameValidation = false;
        boolean emailValidation = false;
        boolean passwordValidation = false;

        while(!usernameValidation){
            Scanner input = new Scanner(System.in);
            System.out.println("Nombre de usuario");
            String name = input.nextLine();
        }



        System.out.println("Correo electrónico");
        String email = input.nextLine();

        System.out.println("Contraseña");
        String password = input.nextLine();


    }
}