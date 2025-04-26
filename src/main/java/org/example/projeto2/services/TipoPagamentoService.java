package org.example.projeto2.services;

import org.example.projeto2.models.TipoPagamento;
import org.example.projeto2.repositories.TipoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPagamentoService {

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    public List<TipoPagamento> listarTodos() {
        return tipoPagamentoRepository.findAll();
    }
}
