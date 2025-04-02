package org.example.projeto2.controllers.Rececionista;

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
public class HomeRececionistaController {

    @FXML
    private ImageView logo;

    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/images/FisioLife.png"));
        logo.setImage(image);
    }

    @FXML
    public void handleRegistarConsulta(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Rececionista/Funcoes/registar_consulta.fxml"));
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            // Criar nova janela (Stage)
            Stage popupStage = new Stage();
            popupStage.setTitle("Registar Consulta");
            popupStage.setScene(new Scene(root, 500, 500)); // tamanho do popup
            popupStage.setResizable(false);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(((Node) event.getSource()).getScene().getWindow()); // janela mãe
            popupStage.show();


        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao abrir a página");
            alert.setContentText("Não foi possível abrir o ecrã de registo de consulta.");
            alert.showAndWait();
        }
    }



    @FXML
    public void handleEditarConsulta(ActionEvent event) {
        System.out.println("Abrir página de edição de consulta...");
    }

    @FXML
    public void handleListarConsultas(ActionEvent event) {
        System.out.println("Abrir listagem de consultas...");
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));

            // Usa o Spring para injetar dependências no controller
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
