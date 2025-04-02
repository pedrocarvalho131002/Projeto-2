package org.example.projeto2.services;

import org.example.projeto2.models.TipoConsulta;
import org.example.projeto2.repositories.TipoConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoConsultaService {

    @Autowired
    private TipoConsultaRepository tipoConsultaRepository;

    public List<TipoConsulta> listarTodos() {
        return tipoConsultaRepository.findAll();
    }
}
