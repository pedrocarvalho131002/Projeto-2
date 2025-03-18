package org.example.projeto2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "consultafuncionario")
public class Consultafuncionario {
    @SequenceGenerator(name = "consultafuncionario_id_gen", sequenceName = "consulta_id_consulta_seq", allocationSize = 1)
    @EmbeddedId
    private ConsultafuncionarioId id;

    @MapsId("idConsulta")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_consulta", nullable = false)
    private Consulta idConsulta;

    @MapsId("idFuncionario")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario idFuncionario;

    public ConsultafuncionarioId getId() {
        return id;
    }

    public void setId(ConsultafuncionarioId id) {
        this.id = id;
    }

    public Consulta getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Consulta idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Funcionario getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Funcionario idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

}