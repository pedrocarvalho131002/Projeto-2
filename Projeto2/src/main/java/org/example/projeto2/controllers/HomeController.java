package org.example.projeto2.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Node;

public class HomeController {

    // Método para o botão "Sair"
    @FXML
    public void handleLogout(ActionEvent event) {
        // Exemplo de ação ao clicar no botão "Sair"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Você saiu com sucesso!");
        alert.showAndWait();

        // Fecha a janela atual (opcional)
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    // Método para o botão "Agendar Consulta"
    @FXML
    public void handleAgendarConsulta(ActionEvent event) {
        // Exemplo de ação ao clicar no botão "Agendar Consulta"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Agendar Consulta");
        alert.setHeaderText(null);
        alert.setContentText("Você selecionou: Agendar Consulta.");
        alert.showAndWait();

        // Lógica para abrir a tela de agendamento pode ser adicionada aqui
    }

    // Método para o botão "Histórico de Pacientes"
    @FXML
    public void handleHistoricoPacientes(ActionEvent event) {
        // Exemplo de ação ao clicar no botão "Histórico de Pacientes"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Histórico de Pacientes");
        alert.setHeaderText(null);
        alert.setContentText("Você selecionou: Histórico de Pacientes.");
        alert.showAndWait();

        // Lógica para navegar até a tela de histórico de pacientes pode ser adicionada aqui
    }

    // Método para o botão "Equipe"
    @FXML
    public void handleEquipe(ActionEvent event) {
        // Exemplo de ação ao clicar no botão "Equipe"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Equipe");
        alert.setHeaderText(null);
        alert.setContentText("Você selecionou: Equipe da clínica.");
        alert.showAndWait();

        // Lógica para acessar as informações da equipe pode ser adicionada aqui
    }

    // Método para o botão "Configurações"
    @FXML
    public void handleConfiguracoes(ActionEvent event) {
        // Exemplo de ação ao clicar no botão "Configurações"
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Configurações");
        alert.setHeaderText(null);
        alert.setContentText("Você acessou: Configurações do sistema.");
        alert.showAndWait();

        // Lógica para abrir as configurações do sistema pode ser adicionada aqui
    }
}