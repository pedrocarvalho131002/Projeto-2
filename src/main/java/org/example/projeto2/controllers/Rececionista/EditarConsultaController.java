package org.example.projeto2.controllers.Rececionista;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.projeto2.models.*;
import org.example.projeto2.models.Enum.EstadoConsulta;
import org.example.projeto2.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Component
public class EditarConsultaController {

    /* --------------------- FXML ------------------------- */
    @FXML private TextField                consultaIdField;
    @FXML private ComboBox<Paciente>       pacienteCombo;
    @FXML private ComboBox<TipoConsulta>   tipoConsultaCombo;
    @FXML private ComboBox<Funcionario>    funcionarioCombo;
    @FXML private ComboBox<EstadoConsulta> estadoConsultaCombo;
    @FXML private DatePicker               dataConsultaPicker;
    @FXML private TextField                horaConsultaField;

    /* -------------------- Services ---------------------- */
    @Autowired private ConsultaService      consultaService;
    @Autowired private PacienteService      pacienteService;
    @Autowired private TipoConsultaService  tipoConsultaService;
    @Autowired private FuncionarioService   funcionarioService;

    /* ------------- consulta actualmente carregada -------- */
    private Consulta consultaEmEdicao;

    /* --------------------- INIT ------------------------- */
    @FXML
    public void initialize() {

        pacienteCombo.setItems(FXCollections.observableArrayList(pacienteService.listarTodos()));
        tipoConsultaCombo.setItems(FXCollections.observableArrayList(tipoConsultaService.listarTodos()));
        funcionarioCombo.setItems(FXCollections.observableArrayList(funcionarioService.listarTodos()));
        estadoConsultaCombo.setItems(FXCollections.observableArrayList(EstadoConsulta.values()));

        estadoConsultaCombo.setDisable(true); // Inicialmente desativado
    }

    /* =====================================================
                  BOTÃO  «Carregar Dados»
       ===================================================== */
    @FXML
    public void handleCarregarConsulta() {
        try {
            int id = Integer.parseInt(consultaIdField.getText().trim());
            loadConsulta(id);
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "ID da consulta deve ser numérico.");
        }
    }

    /* -----------------   loadConsulta   ------------------ */
    private void loadConsulta(int id) {
        Optional<Consulta> opt = consultaService.buscarPorId(id);
        if (opt.isEmpty()) {
            mostrarAlerta("Erro", "Consulta não encontrada (ID " + id + ").");
            return;
        }

        consultaEmEdicao = opt.get();

        // preenche todos os campos
        pacienteCombo.setValue(consultaEmEdicao.getIdPaciente());
        funcionarioCombo.setValue(consultaEmEdicao.getIdFuncionario());
        tipoConsultaCombo.setValue(consultaEmEdicao.getIdTipo());
        dataConsultaPicker.setValue(consultaEmEdicao.getDataConsulta());

        if (consultaEmEdicao.getHoraConsulta() != null)
            horaConsultaField.setText(consultaEmEdicao.getHoraConsulta().format(DateTimeFormatter.ofPattern("HH:mm")));
        else
            horaConsultaField.clear();

        try {
            estadoConsultaCombo.setValue(EstadoConsulta.valueOf(consultaEmEdicao.getEstado().toUpperCase()));
        } catch (IllegalArgumentException e) {
            mostrarAlerta("Erro", "Estado inválido: " + consultaEmEdicao.getEstado());
        }

        // 1️⃣  Desativar edição do campo ID
        consultaIdField.setEditable(false);

        // 2️⃣  Permitir mudar o estado
        estadoConsultaCombo.setDisable(false);
    }

    /* =====================================================
                    BOTÃO  «Atualizar Consulta»
       ===================================================== */
    @FXML
    public void handleAtualizarConsulta() {

        if (consultaEmEdicao == null) {
            mostrarAlerta("Erro", "Carregue primeiro uma consulta.");
            return;
        }

        // copia valores do ecrã para o objecto
        consultaEmEdicao.setIdPaciente(pacienteCombo.getValue());
        consultaEmEdicao.setIdFuncionario(funcionarioCombo.getValue());
        consultaEmEdicao.setIdTipo(tipoConsultaCombo.getValue());
        consultaEmEdicao.setDataConsulta(dataConsultaPicker.getValue());

        /* hora */
        if (!horaConsultaField.getText().trim().isEmpty()) {
            try {
                LocalTime hora = LocalTime.parse(horaConsultaField.getText().trim(),
                        DateTimeFormatter.ofPattern("HH:mm"));
                consultaEmEdicao.setHoraConsulta(hora);
            } catch (DateTimeParseException ex) {
                mostrarAlerta("Erro", "Hora inválida (use HH:mm).");
                return;
            }
        }

        // Atualizar o estado
        if (estadoConsultaCombo.getValue() != null) {
            consultaEmEdicao.setEstado(estadoConsultaCombo.getValue().name().toLowerCase());
        }

        consultaService.salvar(consultaEmEdicao);
        mostrarAlerta("Sucesso", "Consulta atualizada com sucesso!");
    }

    /* =====================================================
              MÉTODO INVOCADO PELO ADMIN (ou outro)
              antes de abrir o popup
       ===================================================== */
    public void preLoadConsulta(int idConsulta) {
        consultaIdField.setText(String.valueOf(idConsulta));
        loadConsulta(idConsulta);
    }

    /* ----------------- util ------------------------------ */
    private void mostrarAlerta(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.showAndWait();
    }
}
