package org.example.projeto2;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Projeto2Application {
    public static void main(String[] args) {
        // Inicializa o JavaFX junto com o projeto utilizando a classe JavaFXApplication
        Application.launch(JavaFXApplication.class, args);
    }
}