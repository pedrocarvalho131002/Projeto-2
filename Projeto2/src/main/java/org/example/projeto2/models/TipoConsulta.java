package org.example.projeto2.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tipo_consulta")
public class TipoConsulta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_consulta_id_gen")
    @SequenceGenerator(name = "tipo_consulta_id_gen", sequenceName = "tipo_consulta_id_tipo_consulta_seq", allocationSize = 1)
    @Column(name = "id_tipo_consulta", nullable = false)
    private Integer id;

    @Column(name = "metodo", nullable = false, length = 50)
    private String metodo;

    @OneToMany(mappedBy = "idTipo")
    private Set<Consulta> consultas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }

}