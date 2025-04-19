package org.example.projeto2.controllers.Admin.funcionarios;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.projeto2.models.Funcionario;
import org.example.projeto2.models.TipoFuncionario;
import org.example.projeto2.services.FuncionarioService;
import org.example.projeto2.services.TipoFuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EditarApagarFuncionarioController {

    // ---------- campos de pesquisa ----------
    @FXML private TextField idField;             // ID a pesquisar

    // ---------- campos de edição ----------
    @FXML private TextField       nomeField;
    @FXML private ComboBox<TipoFuncionario> cargoCombo;
    @FXML private TextField       emailField;
    @FXML private TextField       nifField;
    @FXML private TextField       codPostalField;
    @FXML private TextField       localidadeField;
    @FXML private TextField       ruaField;
    @FXML private TextField       cidadeField;
    @FXML private PasswordField   passwordField;
    @FXML private TextField       teleField;

    // ---------- serviços ----------
    @Autowired private FuncionarioService     funcionarioService;
    @Autowired private TipoFuncionarioService tipoFuncionarioService;

    // ---------- estado interno ----------
    private Funcionario funcionarioEmEdicao;

    // -----------------------------------------------------------
    //  INIT
    // -----------------------------------------------------------
    @FXML
    public void initialize() {
        // Preencher a combo com todos os cargos disponíveis
        cargoCombo.setItems(FXCollections.observableArrayList(
                tipoFuncionarioService.listarTodos()
        ));
    }

    // -----------------------------------------------------------
    //  BOTÃO: CARREGAR DADOS
    // -----------------------------------------------------------
    @FXML
    public void handleCarregarDados() {
        try {
            int id = Integer.parseInt(idField.getText().trim());

            Optional<Funcionario> fOpt = funcionarioService.buscarPorId(id);
            if (fOpt.isEmpty()) {
                mostrarAlerta("Erro", "Funcionário não encontrado.");
                return;
            }

            funcionarioEmEdicao = fOpt.get();

            // --- preencher todos os campos ---
            nomeField       .setText(funcionarioEmEdicao.getNome());
            cargoCombo      .getSelectionModel().select(funcionarioEmEdicao.getIdTipo());
            emailField      .setText(funcionarioEmEdicao.getEmail());
            nifField        .setText(funcionarioEmEdicao.getNif());
            codPostalField  .setText(funcionarioEmEdicao.getCodigoPostal());
            localidadeField .setText(funcionarioEmEdicao.getLocalidade());
            ruaField        .setText(funcionarioEmEdicao.getRua());
            cidadeField     .setText(funcionarioEmEdicao.getCidade());
            passwordField   .setText(funcionarioEmEdicao.getPassword());
            teleField       .setText(funcionarioEmEdicao.getNTelemovel());

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "ID inválido.");
        }
    }

    // -----------------------------------------------------------
    //  BOTÃO: EDITAR
    // -----------------------------------------------------------
    @FXML
    public void handleEditar() {
        if (funcionarioEmEdicao == null) {
            mostrarAlerta("Erro", "Carregue primeiro um funcionário.");
            return;
        }

        // Validação simples
        if (nomeField.getText().isBlank() || cargoCombo.getValue() == null
                || emailField.getText().isBlank()) {
            mostrarAlerta("Erro", "Preencha pelo menos Nome, Cargo e Email.");
            return;
        }

        // --- actualizar objecto ---
        funcionarioEmEdicao.setNome         (nomeField.getText().trim());
        funcionarioEmEdicao.setIdTipo       (cargoCombo.getValue());
        funcionarioEmEdicao.setEmail        (emailField.getText().trim());
        funcionarioEmEdicao.setNif          (nifField.getText().trim());
        funcionarioEmEdicao.setCodigoPostal (codPostalField.getText().trim());
        funcionarioEmEdicao.setLocalidade   (localidadeField.getText().trim());
        funcionarioEmEdicao.setRua          (ruaField.getText().trim());
        funcionarioEmEdicao.setCidade       (cidadeField.getText().trim());
        funcionarioEmEdicao.setPassword     (passwordField.getText().trim());
        funcionarioEmEdicao.setNTelemovel   (teleField.getText().trim());

        funcionarioService.salvar(funcionarioEmEdicao);

        mostrarAlerta("Sucesso", "Funcionário actualizado com êxito!");
        fecharJanela();         // fecha o popup após edição
    }

    // -----------------------------------------------------------
    //  BOTÃO: APAGAR
    // -----------------------------------------------------------
    @FXML
    public void handleApagar() {
        if (funcionarioEmEdicao == null) {
            mostrarAlerta("Erro", "Carregue primeiro um funcionário.");
            return;
        }

        // Confirmação simples
        Alert conf = new Alert(Alert.AlertType.CONFIRMATION,
                "Tem a certeza que deseja apagar este funcionário?",
                ButtonType.YES, ButtonType.NO);
        conf.setHeaderText(null);
        conf.setTitle("Confirmar Apagar");

        if (conf.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
            funcionarioService.deletar(funcionarioEmEdicao.getId());
            mostrarAlerta("Sucesso", "Funcionário removido.");
            fecharJanela();
        }
    }

    // -----------------------------------------------------------
    //  UTILS
    // -----------------------------------------------------------
    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void fecharJanela() {
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }
}
