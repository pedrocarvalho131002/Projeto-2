package org.example.projeto2.controllers.Rececionista;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import org.example.projeto2.models.Enum.EstadoConsulta;
import org.springframework.stereotype.Component;

@Component
public class RegistarConsultaController {

    @FXML
    private TextField pacienteField;

    @FXML
    private TextField funcionarioField;

    @FXML
    private ComboBox<String> tipoConsultaCombo;

    @FXML
    private ComboBox<EstadoConsulta> estadoConsultaCombo;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private TextField horaField;

    @FXML
    public void initialize() {
        estadoConsultaCombo.setItems(FXCollections.observableArrayList(EstadoConsulta.values()));
    }

    @FXML
    public void handleSubmeterConsulta(ActionEvent event) {
        String paciente = pacienteField.getText();
        String funcionario = funcionarioField.getText();
        String tipo = tipoConsultaCombo.getValue();
        String data = dataPicker.getValue() != null ? dataPicker.getValue().toString() : "";
        String hora = horaField.getText();
        EstadoConsulta estado = estadoConsultaCombo.getValue();

        if (paciente.isEmpty() || funcionario.isEmpty() || tipo == null || data.isEmpty() || hora.isEmpty() || estado == null) {
            mostrarAlerta("Campos obrigatórios", "Por favor, preencha todos os campos.");
        } else {
            mostrarAlerta("Consulta registada", "Consulta registada com sucesso!");
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
