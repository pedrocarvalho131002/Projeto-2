package org.example.projeto2.services;

import org.example.projeto2.models.TipoFuncionario;
import org.example.projeto2.repositories.TipoFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoFuncionarioService {

    @Autowired
    private TipoFuncionarioRepository tipoFuncionarioRepository;

    public List<TipoFuncionario> listarTodos() {
        return tipoFuncionarioRepository.findAll();
    }

    // Se precisares buscar por ID
    public TipoFuncionario buscarPorId(Integer id) {
        return tipoFuncionarioRepository.findById(id).orElse(null);
    }
}
