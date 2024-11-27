package com.clintonbrito.squadraproject.pessoa.model;

import com.clintonbrito.squadraproject.endereco.model.Endereco;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_pessoa")
@Data
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_pessoa_gen")
    @SequenceGenerator(name = "sequence_pessoa_gen", sequenceName = "sequence_pessoa", allocationSize = 1)
    @Column(name = "codigo_pessoa")
    private Long codigoPessoa;

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(length = 150, nullable = false)
    private String sobrenome;

    @Column(length = 3, nullable = false)
    private Integer idade;

    @Column(length = 50, nullable = false)
    private String login;

    @Column(length = 50, nullable = false)
    private String senha;

    @Column(length = 1, nullable = false)
    private Integer status;

    @OneToMany(mappedBy = "pessoa")
    private List<Endereco> enderecos;
}
