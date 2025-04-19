package org.example.projeto2.controllers.Rececionista;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.projeto2.models.Consulta;
import org.example.projeto2.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

@Component
public class ListarConsultasController implements Initializable {

    @FXML private TableView<Consulta> tabelaConsultas;
    @FXML private TableColumn<Consulta, Integer> colIdConsulta;
    @FXML private TableColumn<Consulta, String> colPaciente;
    @FXML private TableColumn<Consulta, String> colFuncionario;
    @FXML private TableColumn<Consulta, String> colTipo;
    @FXML private TableColumn<Consulta, String> colData;
    @FXML private TableColumn<Consulta, String> colHora;
    @FXML private TableColumn<Consulta, String> colEstado;

    @Autowired
    private ConsultaService consultaService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura as 'cellValueFactory' de cada coluna
        colIdConsulta.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getId())
        );

        colPaciente.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getIdPaciente() != null ? c.getValue().getIdPaciente().getNome() : ""
                )
        );

        colFuncionario.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getIdFuncionario() != null ? c.getValue().getIdFuncionario().getNome() : ""
                )
        );

        colTipo.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getIdTipo() != null ? c.getValue().getIdTipo().getMetodo() : ""
                )
        );

        colData.setCellValueFactory(c -> {
            if (c.getValue().getDataConsulta() != null) {
                return new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getDataConsulta().toString()
                );
            } else {
                return new javafx.beans.property.SimpleObjectProperty<>("");
            }
        });

        colHora.setCellValueFactory(c -> {
            if (c.getValue().getHoraConsulta() != null) {
                // Formatamos a hora como HH:mm
                DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
                String horaStr = c.getValue().getHoraConsulta().format(f);
                return new javafx.beans.property.SimpleObjectProperty<>(horaStr);
            } else {
                return new javafx.beans.property.SimpleObjectProperty<>("");
            }
        });

        colEstado.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getEstado() != null ? c.getValue().getEstado() : ""
                )
        );

        // Carrega as consultas da BD com JOIN FETCH
        tabelaConsultas.setItems(
                FXCollections.observableArrayList(
                        consultaService.listarTodasComRelacionamentos()
                )
        );
    }
}
