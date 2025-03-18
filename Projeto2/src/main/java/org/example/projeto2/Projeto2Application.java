package org.example.projeto2;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Projeto2Application {

    public static void main(String[] args) {
        // Inicia o contexto do Spring Boot
        ConfigurableApplicationContext context = SpringApplication.run(Projeto2Application.class, args);

        // Inicializa o JavaFX
        launchJavaFXApp(context);
    }

    private static void launchJavaFXApp(ConfigurableApplicationContext context) {
        // A classe Application do JavaFX precisa ser iniciada separadamente
        Application.launch(JavaFXApp.class);
    }

    // Classe JavaFX separada
    public static class JavaFXApp extends Application {
        private ConfigurableApplicationContext context;

        @Override
        public void start(javafx.stage.Stage primaryStage) {
            // Aqui você pode acessar beans do Spring Boot
            context = SpringApplication.run(Projeto2Application.class);

            // Sua lógica JavaFX aqui
            javafx.scene.Scene scene = new javafx.scene.Scene(new javafx.scene.layout.StackPane(), 300, 250);
            primaryStage.setTitle("Projeto Spring Boot + JavaFX");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        @Override
        public void stop() throws Exception {
            super.stop();
            if (context != null) {
                context.close();  // Fecha o contexto Spring Boot ao encerrar a aplicação
            }
        }
    }
}