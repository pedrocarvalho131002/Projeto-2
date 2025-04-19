package org.example.projeto2.controllers.Fisioterapeuta;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.projeto2.controllers.LoginController; // para acessar fisioterapeutaLogadoId
import org.example.projeto2.models.Consulta;
import org.example.projeto2.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MinhasConsultasController {

    @FXML private TableView<Consulta> tabelaConsultas;
    @FXML private TableColumn<Consulta, Integer> colIdConsulta;
    @FXML private TableColumn<Consulta, String> colPaciente;
    @FXML private TableColumn<Consulta, String> colTipo;
    @FXML private TableColumn<Consulta, String> colData;
    @FXML private TableColumn<Consulta, String> colHora;
    @FXML private TableColumn<Consulta, String> colEstado;

    @Autowired
    private ConsultaService consultaService;

    @FXML
    public void initialize() {
        // Configurar colunas
        colIdConsulta.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getId()));

        colPaciente.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getIdPaciente() != null ? c.getValue().getIdPaciente().getNome() : ""
                )
        );

        colTipo.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getIdTipo() != null ? c.getValue().getIdTipo().getMetodo() : ""
                )
        );

        colData.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getDataConsulta() != null ? c.getValue().getDataConsulta().toString() : ""
                )
        );

        colHora.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getHoraConsulta() != null ? c.getValue().getHoraConsulta().toString() : ""
                )
        );

        colEstado.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getEstado() != null ? c.getValue().getEstado() : ""
                )
        );

        // Obter ID do fisioterapeuta logado a partir do LoginController
        Integer fisioterapeutaId = LoginController.fisioterapeutaLogadoId;
        if (fisioterapeutaId == null) {
            // se não existir, mostra a tabela vazia
            tabelaConsultas.setItems(FXCollections.emptyObservableList());
            return;
        }

        // Carregar apenas as consultas do fisioterapeuta logado
        List<Consulta> consultasDoFisio = consultaService.listarPorFuncionario(fisioterapeutaId);
        tabelaConsultas.setItems(FXCollections.observableArrayList(consultasDoFisio));
    }
}
