package org.example.projeto2.controllers.Rececionista;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class ExcluirConsultaController {

    @FXML private TextField consultaIdField;

    @FXML
    public void handleExcluirConsulta() {
        // Aqui virá a lógica de exclusão (usar ConsultaService depois)
        System.out.println("Consulta excluída!");
    }
}
