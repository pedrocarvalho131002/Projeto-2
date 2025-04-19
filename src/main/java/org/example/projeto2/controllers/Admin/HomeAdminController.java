package org.example.projeto2.controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.projeto2.ApplicationContextProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HomeAdminController {

    @FXML
    private ImageView logo;

    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/images/FisioLife.png"));
        logo.setImage(image);
    }

    @FXML
    public void handleGerirFuncionarios(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/Funcoes/gerir_funcionarios.fxml"));
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Gerir Funcionários");
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao abrir Gerir Funcionários");
            alert.setContentText("Não foi possível carregar o ecrã de Gerir Funcionários.");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleGerirConsultas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/Funcoes/gerir_consultas.fxml"));
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Gerir Consultas");
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao abrir Gerir Consultas");
            alert.setContentText("Não foi possível carregar o ecrã de Gerir Consultas.");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleVerEstatisticas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/Funcoes/ver_estatisticas.fxml"));
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Ver Estatísticas");
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao abrir Ver Estatísticas");
            alert.setContentText("Não foi possível carregar o ecrã de Estatísticas.");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));

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
