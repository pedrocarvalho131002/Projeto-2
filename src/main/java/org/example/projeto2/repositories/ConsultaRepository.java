package org.example.projeto2.repositories;

import org.example.projeto2.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    @Query("""
           SELECT c 
           FROM Consulta c
           JOIN FETCH c.idPaciente 
           JOIN FETCH c.idFuncionario 
           JOIN FETCH c.idTipo
           """)
    List<Consulta> findAllComRelacionamentos();

    // Novo método: buscar consultas de um dado funcionário
    @Query("""
           SELECT c
           FROM Consulta c
           JOIN FETCH c.idPaciente 
           JOIN FETCH c.idFuncionario f
           JOIN FETCH c.idTipo
           WHERE f.id = :funcId
           """)
    List<Consulta> findAllByFuncionario(Integer funcId);
}
