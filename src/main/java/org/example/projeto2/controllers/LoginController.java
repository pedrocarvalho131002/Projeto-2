package org.example.projeto2.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import org.example.projeto2.services.LoginService;
import org.example.projeto2.services.LoginService.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginController {

    @Autowired
    private LoginService loginService;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ImageView logo;

    @FXML
    private Label loginErrorLabel;

    @FXML
    public void initialize() {
        // Carregar o logotipo (FisioLife.png)
        logo.setImage(new Image(getClass().getResourceAsStream("/images/FisioLife.png")));

        // Oculta o erro inicialmente
        loginErrorLabel.setVisible(false);
    }

    @FXML
    public void handleLogin() {
        String idInput = usernameField.getText().trim();
        String senha = passwordField.getText().trim();

        // Validação básica
        if (idInput.isEmpty() || senha.isEmpty()) {
            showError("Preencha todos os campos.");
            return;
        }

        Integer funcionarioId;
        try {
            funcionarioId = Integer.parseInt(idInput);
        } catch (NumberFormatException e) {
            showError("ID deve ser numérico.");
            return;
        }

        Optional<LoginResult> loginResultOpt = loginService.login(funcionarioId, senha);

        if (loginResultOpt.isPresent()) {
            // Login válido
            loginErrorLabel.setVisible(false); // Esconde erro, se visível
            redirectWithDelay(loginResultOpt.get());
        } else {
            showError("Credenciais inválidas.");
        }
    }

    private void showError(String mensagem) {
        loginErrorLabel.setText(mensagem);
        loginErrorLabel.setVisible(true);
    }

    private void redirectWithDelay(LoginResult loginResult) {
        new Thread(() -> {
            try {
                Thread.sleep(800);
                Platform.runLater(() -> redirectToHome(loginResult));
            } catch (InterruptedException e) {
                e.printStackTrace();
                Platform.runLater(() -> showError("Erro interno ao redirecionar."));
            }
        }).start();
    }

    private void redirectToHome(LoginResult loginResult) {
        try {
            // Aqui podes redirecionar consoante o cargo
            String cargo = loginResult.getCargo(); // supondo que cargo está disponível no LoginResult

            String fxmlPath = switch (cargo.toLowerCase()) {
                case "administrador" -> "/fxml/Admin/home_admin.fxml";
                case "fisioterapeuta" -> "/fxml/Fisioterapeuta/home_fisioterapeuta.fxml";
                case "rececionista" -> "/fxml/Rececionista/home_rececionista.fxml";
                default -> "/fxml/home.fxml";
            };

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent homeRoot = loader.load();

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            Stage currentStage = getCurrentStage();
            currentStage.setScene(new Scene(homeRoot, bounds.getWidth(), bounds.getHeight()));
            currentStage.setTitle("Clínica de Fisioterapia");
            currentStage.setResizable(true);
            currentStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showError("Erro ao carregar a página inicial.");
        }
    }

    @FXML
    public void handleExit() {
        Stage stage = getCurrentStage();
        stage.close();
        Platform.exit();
    }

    private Stage getCurrentStage() {
        return (Stage) usernameField.getScene().getWindow();
    }
}
