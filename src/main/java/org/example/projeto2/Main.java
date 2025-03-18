package org.example.projeto2;

import org.example.projeto2.services.LoginService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        // Iniciar o contexto do Spring Boot
        ApplicationContext context = SpringApplication.run(Main.class, args);

        // Obter o serviço de login do contexto
        LoginService loginService = context.getBean(LoginService.class);

        // Criar scanner para ler entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Solicitar nome de usuário
        System.out.print("Digite o nome do funcionário: ");
        String nome = scanner.nextLine();

        // Solicitar senha
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        // Verificar login
        boolean sucesso = loginService.login(nome, senha);

        // Exibir resultado no terminal
        if (sucesso) {
            System.out.println("✅ Login bem-sucedido! Bem-vindo, " + nome + "!");
        } else {
            System.out.println("❌ Falha no login! Nome ou senha incorretos.");
        }
    }
}
