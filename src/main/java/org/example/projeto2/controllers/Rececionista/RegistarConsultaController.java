package org.example.projeto2.controllers.Rececionista;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.example.projeto2.models.Consulta;
import org.example.projeto2.models.Enum.EstadoConsulta;
import org.example.projeto2.models.Funcionario;
import org.example.projeto2.models.Paciente;
import org.example.projeto2.models.TipoConsulta;
import org.example.projeto2.services.ConsultaService;
import org.example.projeto2.services.FuncionarioService;
import org.example.projeto2.services.PacienteService;
import org.example.projeto2.services.TipoConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class RegistarConsultaController {

    @FXML private ComboBox<Paciente> pacienteCombo;
    @FXML private ComboBox<Funcionario> funcionarioCombo;
    @FXML private ComboBox<TipoConsulta> tipoConsultaCombo;
    //@FXML private ComboBox<EstadoConsulta> estadoConsultaCombo;
    @FXML private DatePicker dataPicker;
    @FXML private TextField horaField;

    @Autowired private PacienteService pacienteService;
    @Autowired private FuncionarioService funcionarioService;
    @Autowired private TipoConsultaService tipoConsultaService;
    @Autowired private ConsultaService consultaService;


    @FXML
    public void initialize() {
        pacienteCombo.setItems(FXCollections.observableArrayList(pacienteService.listarTodos()));
        funcionarioCombo.setItems(FXCollections.observableArrayList(funcionarioService.listarFisioterapeutas()));
        tipoConsultaCombo.setItems(FXCollections.observableArrayList(tipoConsultaService.listarTodos()));
        //estadoConsultaCombo.setItems(FXCollections.observableArrayList(EstadoConsulta.values()));
    }


    @FXML
    public void handleSubmeterConsulta(ActionEvent event) {
        Paciente paciente = pacienteCombo.getValue();
        Funcionario funcionario = funcionarioCombo.getValue();
        TipoConsulta tipo = tipoConsultaCombo.getValue();
        //EstadoConsulta estado = estadoConsultaCombo.getValue();

        LocalDate data = dataPicker.getValue();
        String horaStr = horaField.getText();

        if (paciente == null || funcionario == null || tipo == null || data == null || horaStr.isEmpty() /*|| estado == null*/) {
            mostrarAlerta("Campos obrigatórios", "Por favor, preencha todos os campos.");
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime hora = LocalTime.parse(horaStr, formatter);

            Consulta novaConsulta = new Consulta();
            novaConsulta.setIdPaciente(paciente);
            novaConsulta.setIdFuncionario(funcionario);
            novaConsulta.setIdTipo(tipo);
            novaConsulta.setDataConsulta(data);
            novaConsulta.setHoraConsulta(hora);
            novaConsulta.setEstado("agendada");

            consultaService.salvar(novaConsulta);
            mostrarAlerta("Consulta registada", "Consulta registada com sucesso!");

            //fechar o pop-up
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (DateTimeParseException e) {
            mostrarAlerta("Erro", "Formato de hora inválido. Use HH:mm");
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
