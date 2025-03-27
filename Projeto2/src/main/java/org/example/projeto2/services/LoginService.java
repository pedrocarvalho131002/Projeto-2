package org.example.projeto2.services;

import org.example.projeto2.models.Funcionario;
import org.example.projeto2.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Optional<LoginResult> login(Integer id, String password) {
        // Converter o ID de Long para Integer
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(id.intValue());

        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();

            // Verifica se a senha está correta
            if (funcionario.getPassword().equals(password)) {
                // Retorna LoginResult com o ID e senha
                return Optional.of(new LoginResult(funcionario.getId(), funcionario.getPassword()));
            }
        }

        // Retorna vazio se as credenciais forem inválidas
        return Optional.empty();
    }

    // Classe interna que encapsula o resultado do login
    public static class LoginResult {
        private final Integer id;       // ID do funcionário
        private final String senha;  // Senha do funcionário

        public LoginResult(Integer id, String senha) {
            this.id = id;
            this.senha = senha;
        }

        public Integer getId() {
            return id;
        }

        public String getSenha() {
            return senha;
        }
    }
}