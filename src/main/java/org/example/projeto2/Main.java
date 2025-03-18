package org.example.projeto2;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Main {

    public static void main(String[] args) {
        // Inicia o contexto do Spring Boot
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
    }
}