package org.example.projeto2.controllers.Fisioterapeuta;

import org.example.projeto2.controllers.LoginController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.projeto2.models.Consulta;
import org.example.projeto2.models.Enum.EstadoConsulta;
import org.example.projeto2.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AlterarEstadoController {

    @FXML
    private TextField consultaIdField;
    @FXML
    private ComboBox<EstadoConsulta> estadoCombo;

    @Autowired
    private ConsultaService consultaService;

    @FXML
    public void initialize() {
        // Carrega os estados
        estadoCombo.getItems().setAll(EstadoConsulta.values());
    }

    // Exemplo do handleAtualizarEstado
    @FXML
    public void handleAtualizarEstado() {
        try {
            int idConsulta = Integer.parseInt(consultaIdField.getText().trim());
            Optional<Consulta> optConsulta = consultaService.buscarPorId(idConsulta);

            if (optConsulta.isEmpty()) {
                mostrarAlerta("Erro", "Consulta não encontrada com ID: " + idConsulta);
                return;
            }

            // Consulta que o fisioterapeuta quer alterar
            Consulta consulta = optConsulta.get();

            // Verificar se o fisioterapeuta logado é o dono desta consulta
            Integer fisioterapeutaLogadoId = LoginController.fisioterapeutaLogadoId;
            if (!consulta.getIdFuncionario().getId().equals(fisioterapeutaLogadoId)) {
                // Se NÃO coincidir, não pode alterar
                mostrarAlerta("Acesso negado", "Você não tem permissão para alterar o estado desta consulta.");
                return;
            }

            // Se chegou aqui, o fisioterapeuta é o dono da consulta
            EstadoConsulta novoEstado = estadoCombo.getValue();
            if (novoEstado == null) {
                mostrarAlerta("Erro", "Selecione o novo estado!");
                return;
            }

            consulta.setEstado(novoEstado.name());
            consultaService.salvar(consulta);

            mostrarAlerta("Sucesso", "Estado da consulta atualizado!");
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "ID da consulta deve ser numérico.");
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
