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

    public boolean login(String nome, String password) {
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByNome(nome);

        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();
            return funcionario.getPassword().equals(password); // 🔥 Verifica senha
        }

        return false;
    }
}
