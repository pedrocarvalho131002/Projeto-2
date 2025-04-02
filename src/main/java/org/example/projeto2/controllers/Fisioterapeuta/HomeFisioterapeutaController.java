package org.example.projeto2.controllers.Fisioterapeuta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.projeto2.ApplicationContextProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HomeFisioterapeutaController {

    @FXML
    private ImageView logo;


    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/images/FisioLife.png"));
        logo.setImage(image);
    }

    @FXML
    public void handleMinhasConsultas(ActionEvent event) {
        System.out.println("Abrir página de listagem das minhas consultas...");
        // Implementar troca de cena se necessário
    }

    @FXML
    public void handleAlterarEstado(ActionEvent event) {
        System.out.println("Abrir página para alterar estado da consulta...");
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));

            // Permite que o Spring injete corretamente o controller de login
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);

            Parent loginRoot = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene loginScene = new Scene(loginRoot, currentStage.getWidth(), currentStage.getHeight());

            currentStage.setScene(loginScene);
            currentStage.setTitle("Login");
            currentStage.setMaximized(true);
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao voltar ao login");
            alert.setContentText("Não foi possível carregar a página de login.");
            alert.showAndWait();
        }
    }
}
