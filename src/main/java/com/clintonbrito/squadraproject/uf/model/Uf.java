package com.clintonbrito.squadraproject.uf.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_uf")
@Data
public class Uf {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "codigo_uf")
    private Long codigoUf;

    @Column(length = 2, nullable = false)
    private String sigla;

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 1, nullable = false)
    private Integer status;
}
