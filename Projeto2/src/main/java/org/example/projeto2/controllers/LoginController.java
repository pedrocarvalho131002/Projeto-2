package org.example.projeto2.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.example.projeto2.services.LoginService.LoginResult;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.Optional;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import org.example.projeto2.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    @Autowired
    private LoginService loginService;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ImageView logo; // O elemento definido no FXML

    @FXML
    public void initialize() {
        // Carrega a imagem e associa ao ImageView no controlador:
        logo.setImage(new Image(getClass().getResourceAsStream("/images/white_logo.png")));
    }
    /**
     * Método chamado ao pressionar o botão de Login.
     */
    @FXML
    public void handleLogin() {
        // Obtém os valores digitados no formulário
        String idInput = usernameField.getText().trim(); // ID do funcionário (deve ser numérico)
        String senha = passwordField.getText().trim(); // Senha do funcionário

        // Verifica se os campos estão preenchidos
        if (idInput.isEmpty() || senha.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos Vazios", "Por favor, preencha todos os campos de login.");
            return;
        }

        // Converter o ID de String para Integer
        Integer funcionarioId;
        try {
            funcionarioId = Integer.valueOf(idInput); // Tenta converter para número
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "ID Inválido", "O ID deve ser um número válido.");
            return;
        }

        // Chama o serviço de login para validar as credenciais e obter informações do funcionário
        Optional<LoginResult> loginResultOpt = loginService.login(funcionarioId, senha);

        if (loginResultOpt.isPresent()) {
            // Credenciais válidas: obtém os detalhes do funcionário logado
            LoginResult loginResult = loginResultOpt.get();
            Integer funcionarioIdRetornado = loginResult.getId(); // Pega o ID vindo do resultado
            String senhaCadastrada = loginResult.getSenha(); // Senha cadastrada

            // Exibe uma mensagem de sucesso
            showAlert(Alert.AlertType.INFORMATION, "Login Bem-Sucedido", "Bem-vindo(a) ao sistema!");

            // Exibe informações no console (opcional)
            System.out.println("Funcionário logado (ID): " + funcionarioIdRetornado);
            System.out.println("Senha cadastrada no sistema: " + senhaCadastrada);

            // Redireciona o usuário para a tela Home após um breve atraso
            redirectWithDelay(1500);

        } else {
            // Credenciais inválidas: exibe uma mensagem de erro
            showAlert(Alert.AlertType.ERROR, "Falha no Login", "As credenciais fornecidas estão incorretas.");
        }
    }

    /**
     * Método para redirecionar à tela Home após um atraso (em milissegundos).
     */
    private void redirectWithDelay(int delayMillis) {
        // Cria um novo thread separado para aguardar e redirecionar
        new Thread(() -> {
            try {
                Thread.sleep(delayMillis); // Pausa por um tempo configurável
                Platform.runLater(this::redirectToHome); // Executa o redirecionamento na thread principal do JavaFX
            } catch (InterruptedException e) {
                e.printStackTrace();
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, "Erro Interno", "Falha ao redirecionar para a tela principal."));
            }
        }).start();
    }

    /**
     * Redireciona para a tela Home carregando o respectivo arquivo FXML.
     */
    private void redirectToHome() {
        try {
            // Carrega o arquivo FXML da página Home
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            Parent homeRoot = loader.load();

            // Obter dimensões da tela visíveis
            Screen screen = Screen.getPrimary(); // Obtém a tela principal
            Rectangle2D screenBounds = screen.getVisualBounds(); // Obtém os limites visíveis

            // Configura a nova cena para a tela Home
            Scene homeScene = new Scene(homeRoot, screenBounds.getWidth(), screenBounds.getHeight());
            Stage currentStage = getCurrentStage(); // Obtém o Stage atual

            currentStage.setScene(homeScene); // Define a nova cena
            currentStage.setTitle("Clínica de Fisioterapia"); // Define o título da janela
            currentStage.setResizable(true); // Permitindo redimensionamento
            currentStage.show(); // Exibe a nova tela

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela Home.");
        }
    }

    @FXML
    public void handleExit() {
        // Encerra a aplicação
        Stage currentStage = getCurrentStage();
        currentStage.close();
        Platform.exit(); // Garante o encerramento do aplicativo
    }

    /**
     * Exibe uma mensagem de alerta para o usuário.
     */
    private void showAlert(Alert.AlertType alertType, String titulo, String mensagem) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Obtém o Stage atual baseado no campo de texto do formulário.
     */
    private Stage getCurrentStage() {
        return (Stage) usernameField.getScene().getWindow();
    }
}