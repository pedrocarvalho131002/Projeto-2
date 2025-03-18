package org.example.projeto2.repositories;

import org.example.projeto2.models.Pacientelesao;
import org.example.projeto2.models.PacientelesaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacientelesaoRepository extends JpaRepository<Pacientelesao, PacientelesaoId> {
}
