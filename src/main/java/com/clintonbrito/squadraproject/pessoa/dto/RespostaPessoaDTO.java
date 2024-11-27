package com.clintonbrito.squadraproject.pessoa.dto;

import com.clintonbrito.squadraproject.endereco.model.Endereco;

import java.util.List;

public record RespostaPessoaDTO(
        Long codigoPessoa,
        String nome,
        String sobrenome,
        Integer idade,
        String login,
        String senha,
        Integer status,
        List<Endereco> enderecos
) {
}
