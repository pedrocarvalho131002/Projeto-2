package org.example.projeto2.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "lesoes")
public class Lesoe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesoes_id_gen")
    @SequenceGenerator(name = "lesoes_id_gen", sequenceName = "lesao_conhecidas_id_lesao_seq", allocationSize = 1)
    @Column(name = "id_lesao", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @ManyToMany
    @JoinTable(name = "pacientelesao",
            joinColumns = @JoinColumn(name = "id_lesao"),
            inverseJoinColumns = @JoinColumn(name = "id_paciente"))
    private Set<Paciente> pacientes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(Set<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

}