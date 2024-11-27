package com.clintonbrito.squadraproject.endereco.model;

import com.clintonbrito.squadraproject.bairro.model.Bairro;
import com.clintonbrito.squadraproject.pessoa.model.Pessoa;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "tb_endereco")
@Data
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_endereco_gen")
    @SequenceGenerator(name = "sequence_endereco_gen", sequenceName = "sequence_endereco", allocationSize = 1)
    @Column(name = "codigo_endereco")
    private Long codigoEndereco;

    @Column(name = "codigo_pessoa", insertable = false, updatable = false)
    private Long codigoPessoa;

    @Column(name = "codigo_bairro", insertable = false, updatable = false)
    private Long codigoBairro;

    @Column(name = "nome_rua", length = 100, nullable = false)
    private String nomeRua;

    @Column(length = 10, nullable = false)
    private Integer numero;

    @Column(length = 60)
    private String complemento;

    @Column(length = 9, nullable = false)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "codigo_pessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "codigo_bairro")
    private Bairro bairro;
}
