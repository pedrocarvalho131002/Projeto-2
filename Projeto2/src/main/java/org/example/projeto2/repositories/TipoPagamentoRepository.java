package org.example.projeto2.repositories;

import org.example.projeto2.models.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Integer> {
}
