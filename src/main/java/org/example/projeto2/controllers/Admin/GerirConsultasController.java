package org.example.projeto2.controllers.Admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.projeto2.ApplicationContextProvider;
import org.example.projeto2.models.Consulta;
import org.example.projeto2.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GerirConsultasController {

    /* tabela + colunas */
    @FXML private TableView<Consulta> tabelaConsultas;
    @FXML private TableColumn<Consulta,Integer> colIdConsulta;
    @FXML private TableColumn<Consulta,String>  colPaciente;
    @FXML private TableColumn<Consulta,String>  colFuncionario;
    @FXML private TableColumn<Consulta,String>  colTipo;
    @FXML private TableColumn<Consulta,String>  colData;
    @FXML private TableColumn<Consulta,String>  colHora;
    @FXML private TableColumn<Consulta,String>  colEstado;

    @Autowired
    private ConsultaService consultaService;

    /* ---------------- inicialização ---------------- */

    @FXML
    public void initialize() {

        colIdConsulta.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getId()));

        colPaciente.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getIdPaciente().getNome()));

        colFuncionario.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getIdFuncionario().getNome()));

        colTipo.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getIdTipo().getMetodo()));

        colData.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getDataConsulta().toString()));

        colHora.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getHoraConsulta().toString()));

        colEstado.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getEstado()));

        carregarTabela();
    }

    /* ---------------- handlers ---------------- */

    @FXML
    private void handleAtualizar() {
        carregarTabela();
    }

    @FXML
    private void handleEditarEliminar() {

        Consulta sel = tabelaConsultas.getSelectionModel().getSelectedItem();
        if (sel == null) {
            alerta("Seleção", "Selecione uma consulta na tabela.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/Rececionista/Funcoes/editar_consulta.fxml"));  // caminho certo
            loader.setControllerFactory(
                    ApplicationContextProvider.getApplicationContext()::getBean);

            /* carrega o FXML primeiro */
            Parent root = loader.load();

            /* depois obtém o controller já criado pelo Spring/FXML */
            org.example.projeto2.controllers.Rececionista.EditarConsultaController ctrl =
                    loader.getController();
            ctrl.preLoadConsulta(sel.getId());          // método que preenche os campos

            Stage pop = new Stage();
            pop.initModality(Modality.APPLICATION_MODAL);
            pop.setTitle("Editar / Eliminar Consulta");
            pop.setScene(new Scene(root));
            pop.setResizable(false);
            pop.showAndWait();

            carregarTabela();   // refresca a lista depois de fechar o popup

        } catch (IOException e) {
            e.printStackTrace();
            alerta("Erro", "Não foi possível abrir a janela de edição.");
        }
    }


    /* ---------------- util ---------------- */

    private void carregarTabela() {
        tabelaConsultas.setItems(FXCollections.observableArrayList(
                consultaService.listarTodasComRelacionamentos()));
    }

    private void alerta(String titulo,String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION,msg);
        a.setHeaderText(null);
        a.setTitle(titulo);
        a.showAndWait();
    }
}
