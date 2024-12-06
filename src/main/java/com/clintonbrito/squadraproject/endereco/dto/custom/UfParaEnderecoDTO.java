package com.clintonbrito.squadraproject.endereco.dto.custom;

public record UfParaEnderecoDTO(
        Long codigoUF,
        String nome,
        String sigla,
        Integer status
) {
}
