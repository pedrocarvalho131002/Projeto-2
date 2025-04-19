package org.example.projeto2.controllers.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.springframework.stereotype.Component;

@Component
public class VerEstatisticasController {

    @FXML
    public void handleGerarRelatorio() {
        System.out.println("Gerar estatísticas / relatórios...");
        // Exemplo: exibir gráfico ou calcular totals
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
