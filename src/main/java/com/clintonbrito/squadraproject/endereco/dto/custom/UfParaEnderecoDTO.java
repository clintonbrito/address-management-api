package com.clintonbrito.squadraproject.endereco.dto.custom;

public record UfParaEnderecoDTO(
        Long codigoUf,
        String nome,
        String sigla,
        Integer status
) {
}
