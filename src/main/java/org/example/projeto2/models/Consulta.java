package org.example.projeto2.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consulta_id_gen")
    @SequenceGenerator(name = "consulta_id_gen", sequenceName = "consulta_id_consulta_seq", allocationSize = 1)
    @Column(name = "id_consulta", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo")
    private TipoConsulta idTipo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paciente")
    private Paciente idPaciente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_funcionario")
    private Funcionario idFuncionario;

    @Column(name = "data_consulta", nullable = false)
    private LocalDate dataConsulta;

    @Column(name = "hora_consulta", nullable = false)
    private LocalTime horaConsulta;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @ManyToMany
    @JoinTable(name = "consultafuncionario",
            joinColumns = @JoinColumn(name = "id_consulta"),
            inverseJoinColumns = @JoinColumn(name = "id_funcionario"))
    private Set<Funcionario> funcionarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idConsulta")
    private Set<Pagamento> pagamentos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoConsulta getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoConsulta idTipo) {
        this.idTipo = idTipo;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Funcionario getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Funcionario idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalTime getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(LocalTime horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Set<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(Set<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

}