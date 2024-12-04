package com.clintonbrito.squadraproject.uf.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "tb_uf")
@Data
public class Uf implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_uf_gen")
    @SequenceGenerator(name = "sequence_uf_gen", sequenceName = "sequence_uf", allocationSize = 1)
    @Column(name = "codigo_uf")
    private Long codigoUF;

    @Column(length = 2, nullable = false)
    private String sigla;

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 1, nullable = false)
    private Integer status;
}
