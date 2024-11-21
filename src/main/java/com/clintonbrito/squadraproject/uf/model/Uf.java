package com.clintonbrito.squadraproject.uf.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_uf")
@Data
public class Uf {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    @SequenceGenerator(name = "mySeqGen", sequenceName = "my_sequence_name", allocationSize = 1)
    @Column(name = "codigo_uf")
    private Long codigoUF;

    @Column(length = 2, nullable = false)
    private String sigla;

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 1, nullable = false)
    private Integer status;
}
