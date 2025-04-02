package org.example.projeto2.repositories;

import org.example.projeto2.models.TipoConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoConsultaRepository extends JpaRepository<TipoConsulta, Integer> {
}
