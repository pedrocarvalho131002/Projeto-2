package org.example.projeto2.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tipo_pagamento")
public class TipoPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_pagamento_id_gen")
    @SequenceGenerator(name = "tipo_pagamento_id_gen", sequenceName = "tipo_pagamento_id_tipo_pagamento_seq", allocationSize = 1)
    @Column(name = "id_tipo_pagamento", nullable = false)
    private Integer id;

    @Column(name = "metodo", nullable = false, length = 50)
    private String metodo;

    @OneToMany(mappedBy = "idTipoPagamento")
    private Set<Pagamento> pagamentos = new LinkedHashSet<>();

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

    public Set<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(Set<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

}