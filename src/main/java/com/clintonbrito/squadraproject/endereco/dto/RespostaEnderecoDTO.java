package com.clintonbrito.squadraproject.endereco.dto;

public record RespostaEnderecoDTO(
        Long codigoBairro,
        Long codigoMunicipio,
        String nome,
        Integer status
) {
}
