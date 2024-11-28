package com.clintonbrito.squadraproject.bairro.model;

import com.clintonbrito.squadraproject.municipio.model.Municipio;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "tb_bairro")
@Data
public class Bairro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_bairro_gen")
    @SequenceGenerator(name = "sequence_bairro_gen", sequenceName = "sequence_bairro", allocationSize = 1)
    @Column(name = "codigo_bairro")
    private Long codigoBairro;

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 1, nullable = false)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "codigo_municipio")
    private Municipio municipio;
}
