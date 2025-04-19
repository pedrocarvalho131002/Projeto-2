package org.example.projeto2.repositories;

import org.example.projeto2.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByNome(String nome);

    // CORRETO: buscar funcionários com id_tipo = 1
    List<Funcionario> findByIdTipo_Id(Integer id);

}
