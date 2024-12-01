package com.clintonbrito.squadraproject.pessoa.dto;

import com.clintonbrito.squadraproject.endereco.dto.RespostaEnderecoDTO;

import java.util.List;

public record RespostaDetalhadaPessoaDTO(
        Long codigoPessoa,
        String nome,
        String sobrenome,
        Integer idade,
        String login,
        String senha,
        Integer status,
        List<RespostaEnderecoDTO> enderecos
) {
}
