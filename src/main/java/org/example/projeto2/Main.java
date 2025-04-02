package org.example.projeto2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carregar o arquivo Login.fxml
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));

        // Tentar carregar o ícone da janela
        try {
            // Caminho do ícone
            Image appIcon = new Image(getClass().getResourceAsStream("/images/logo.png"));
            primaryStage.getIcons().add(appIcon);
            System.out.println("Ícone carregado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao tentar carregar o ícone: " + e.getMessage());
        }

        // Configurar o título e a cena da janela
        primaryStage.setTitle("Login Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Iniciar a aplicação JavaFX
        launch(args);
    }
}