package com.apifinance.apirestfinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiRestFinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiRestFinanceApplication.class, args);

        //Si al buscar http://localhost:8080/ping, debe salir en la página "pong"
        //String testController = new TestController().ping();


    }

}
