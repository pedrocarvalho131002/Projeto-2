package org.example.projeto2.services;

import org.example.projeto2.models.Consulta;
import org.example.projeto2.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta salvar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    // Exemplo de método no ConsultaService
    public Optional<Consulta> buscarPorId(Integer id) {
        return consultaRepository.findById(id);
    }


    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }


    public List<Consulta> listarTodasComRelacionamentos() {
        return consultaRepository.findAllComRelacionamentos();
    }


    public List<Consulta> listarPorFuncionario(Integer funcId) {
        return consultaRepository.findAllByFuncionario(funcId);
    }


}
