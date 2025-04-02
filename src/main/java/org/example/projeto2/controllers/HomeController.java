package org.example.projeto2.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    public void handleLogout(ActionEvent event) {
        fecharJanela(event);
    }

    @FXML
    public void handleAgendarConsulta(ActionEvent event) {
        // lógica para agendar consulta
    }

    @FXML
    public void handleHistoricoPacientes(ActionEvent event) {
        // lógica para histórico
    }

    @FXML
    public void handleEquipe(ActionEvent event) {
        // lógica para equipe
    }

    @FXML
    public void handleConfiguracoes(ActionEvent event) {
        // lógica para configurações
    }

    private void fecharJanela(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
