package org.example.projeto2.controllers.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.example.projeto2.ApplicationContextProvider;
import org.springframework.stereotype.Component;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

@Component
public class GerirFuncionariosController {

    @FXML
    public void handleListarFuncionarios() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/Funcoes/funcionarios/listar_funcionarios.fxml"));
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Listar Funcionários");
            stage.setScene(new Scene(root, 700, 500));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Não foi possível abrir a tela de Listar Funcionários.");
        }
    }

    @FXML
    public void handleCriarFuncionario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/Funcoes/funcionarios/criar_funcionario.fxml"));
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Criar Novo Funcionário");
            stage.setScene(new Scene(root, 600, 600));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Não foi possível abrir a tela de Criar Novo Funcionário.");
        }
    }

    @FXML
    public void handleEditarFuncionario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/Funcoes/funcionarios/editar_apagar_funcionario.fxml"));
            loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Editar / Apagar Funcionário");
            stage.setScene(new Scene(root, 600, 600));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Não foi possível abrir a tela de Editar/Apagar Funcionário.");
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
