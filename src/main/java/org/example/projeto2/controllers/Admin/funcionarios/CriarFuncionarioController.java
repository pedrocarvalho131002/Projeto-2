package org.example.projeto2.controllers.Admin.funcionarios;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.projeto2.models.Funcionario;
import org.example.projeto2.models.TipoFuncionario;
import org.example.projeto2.services.FuncionarioService;
import org.example.projeto2.services.TipoFuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CriarFuncionarioController {

    @FXML private TextField nomeField;
    @FXML private ComboBox<TipoFuncionario> cargoCombo;
    @FXML private TextField emailField;
    @FXML private TextField nifField;
    @FXML private TextField codigoPostalField;
    @FXML private TextField localidadeField;
    @FXML private TextField ruaField;
    @FXML private TextField cidadeField;
    @FXML private PasswordField passwordField;
    @FXML private TextField nTelemovelField;

    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private TipoFuncionarioService tipoFuncionarioService;

    @FXML
    public void initialize() {
        cargoCombo.setItems(FXCollections.observableArrayList(tipoFuncionarioService.listarTodos()));
        // Assim a combo exibe todos os tipos de funcionário ("administrador", "fisioterapeuta", etc.)
    }


    @FXML
    public void handleCriar() {
        // Validar campos
        if (nomeField.getText().isEmpty() || cargoCombo.getValue() == null || emailField.getText().isEmpty()) {
            mostrarAlerta("Erro", "Preencha todos os campos obrigatórios!");
            return;
        }

        Funcionario f = new Funcionario();
        f.setNome(nomeField.getText());
        f.setIdTipo(cargoCombo.getValue());
        f.setEmail(emailField.getText());

        f.setNif(nifField.getText());
        f.setCodigoPostal(codigoPostalField.getText());
        f.setLocalidade(localidadeField.getText());
        f.setRua(ruaField.getText());
        f.setCidade(cidadeField.getText());
        f.setPassword(passwordField.getText());
        f.setNTelemovel(nTelemovelField.getText());


        funcionarioService.salvar(f);
        mostrarAlerta("Sucesso", "Funcionário criado!");

        Stage stage = (Stage) nomeField.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
