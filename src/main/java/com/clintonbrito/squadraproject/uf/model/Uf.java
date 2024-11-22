package com.clintonbrito.squadraproject.uf.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_uf")
public class Uf implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_uf_gen")
    @SequenceGenerator(name = "sequence_uf_gen", sequenceName = "sequence_uf", allocationSize = 1)
    @Column(name = "codigo_uf")
    private Long codigoUf;

    @Column(length = 2, nullable = false)
    private String sigla;

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 1, nullable = false)
    private Integer status;

    public Long getCodigoUf() {
        return codigoUf;
    }

    public void setCodigoUf(Long codigoUf) {
        this.codigoUf = codigoUf;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
