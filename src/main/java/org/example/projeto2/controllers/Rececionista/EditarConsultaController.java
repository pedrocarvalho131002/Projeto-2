package org.example.projeto2.controllers.Rececionista;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

@Component
public class EditarConsultaController {

    @FXML private TextField consultaIdField;
    @FXML private TextField pacienteIdField;
    @FXML private TextField tipoConsultaField;
    @FXML private TextField funcionarioIdField;
    @FXML private DatePicker dataConsultaPicker;
    @FXML private TextField horaConsultaField;
    @FXML private TextField estadoField;

    @FXML
    public void handleAtualizarConsulta() {
        // Aqui virá a lógica de edição (usar ConsultaService depois)
        System.out.println("Consulta atualizada!");
    }
}
