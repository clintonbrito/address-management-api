package com.clintonbrito.squadraproject.municipio.model;

import com.clintonbrito.squadraproject.uf.model.Uf;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "tb_municipio")
@Data
public class Municipio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_mun_gen")
    @SequenceGenerator(name = "sequence_mun_gen", sequenceName = "sequence_municipio", allocationSize = 1)
    @Column(name = "codigo_municipio")
    private Long codigoMunicipio;

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 1, nullable = false)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "codigo_uf")
    private Uf uf;
}
