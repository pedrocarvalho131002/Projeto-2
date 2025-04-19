package org.example.projeto2.controllers.Admin.funcionarios;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.projeto2.models.Funcionario;
import org.example.projeto2.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ListarFuncionariosController implements Initializable {

    @FXML private TableView<Funcionario> tabelaFuncionarios;

    @FXML private TableColumn<Funcionario, Integer> colId;
    @FXML private TableColumn<Funcionario, String>  colNome;
    @FXML private TableColumn<Funcionario, String>  colCargo;
    @FXML private TableColumn<Funcionario, String>  colEmail;
    @FXML private TableColumn<Funcionario, String>  colNif;
    @FXML private TableColumn<Funcionario, String>  colCodigoPostal;
    @FXML private TableColumn<Funcionario, String>  colLocalidade;
    @FXML private TableColumn<Funcionario, String>  colRua;
    @FXML private TableColumn<Funcionario, String>  colCidade;
    @FXML private TableColumn<Funcionario, String>  colTelemovel;

    @Autowired
    private FuncionarioService funcionarioService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colId            .setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getId()));
        colNome          .setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty (c.getValue().getNome()));
        colCargo         .setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty (
                c.getValue().getIdTipo() != null ? c.getValue().getIdTipo().getCargo() : ""));
        colEmail         .setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty (c.getValue().getEmail()));
        colNif           .setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty (c.getValue().getNif()));
        colCodigoPostal  .setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty (c.getValue().getCodigoPostal()));
        colLocalidade    .setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty (c.getValue().getLocalidade()));
        colRua           .setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty (c.getValue().getRua()));
        colCidade        .setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty (c.getValue().getCidade()));
        colTelemovel     .setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty (c.getValue().getNTelemovel()));

        // carrega tudo
        tabelaFuncionarios.setItems(
                FXCollections.observableArrayList(funcionarioService.listarTodos())
        );
    }
}
