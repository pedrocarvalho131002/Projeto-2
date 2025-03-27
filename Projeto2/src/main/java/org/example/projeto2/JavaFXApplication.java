package org.example.projeto2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFXApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() throws Exception {
        // Inicializa o contexto Spring
        springContext = SpringApplication.run(Projeto2Application.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carregar o arquivo FXML utilizando o Spring
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        loader.setControllerFactory(springContext::getBean); // Permite que o Spring gerencie o controlador
        Parent root = loader.load();

        // Configurações da janela (Stage)
        primaryStage.setTitle("Clínica de Fisioterapia");
        primaryStage.setScene(new Scene(root, 800, 600)); // Janela central com largura e altura
        try {
            // Caminho do ícone
            Image appIcon = new Image(getClass().getResourceAsStream("/images/logo.png"));
            primaryStage.getIcons().add(appIcon);
            System.out.println("Ícone carregado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao tentar carregar o ícone: " + e.getMessage());
        }

        // Adicionando a configuração para maximizar a janela ao abrir
        primaryStage.setMaximized(true);

        primaryStage.setResizable(true); // Pode tornar redimensionável se necessário
        primaryStage.show(); // Exibe a janela
    }

    @Override
    public void stop() throws Exception {
        // Fecha o contexto Spring ao encerrar a aplicação
        springContext.close();
    }
}