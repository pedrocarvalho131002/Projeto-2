package org.example.projeto2.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tipo_funcionario")
public class TipoFuncionario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_funcionario_id_gen")
    @SequenceGenerator(name = "tipo_funcionario_id_gen", sequenceName = "tipo_funcionario_id_tipo_funcionario_seq", allocationSize = 1)
    @Column(name = "id_tipo_funcionario", nullable = false)
    private Integer id;

    @Column(name = "cargo", nullable = false, length = 100)
    private String cargo;

    @OneToMany(mappedBy = "idTipo")
    private Set<Funcionario> funcionarios = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}