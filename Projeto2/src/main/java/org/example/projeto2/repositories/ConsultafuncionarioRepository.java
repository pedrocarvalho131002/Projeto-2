package org.example.projeto2.repositories;

import org.example.projeto2.models.Consultafuncionario;
import org.example.projeto2.models.ConsultafuncionarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultafuncionarioRepository extends JpaRepository<Consultafuncionario, ConsultafuncionarioId> {
}
