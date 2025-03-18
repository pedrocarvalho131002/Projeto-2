package org.example.projeto2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class PacientelesaoId implements java.io.Serializable {
    private static final long serialVersionUID = -6439008123509378311L;
    @Column(name = "id_paciente", nullable = false)
    private Integer idPaciente;

    @Column(name = "id_lesao", nullable = false)
    private Integer idLesao;

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdLesao() {
        return idLesao;
    }

    public void setIdLesao(Integer idLesao) {
        this.idLesao = idLesao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PacientelesaoId entity = (PacientelesaoId) o;
        return Objects.equals(this.idPaciente, entity.idPaciente) &&
                Objects.equals(this.idLesao, entity.idLesao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPaciente, idLesao);
    }

}