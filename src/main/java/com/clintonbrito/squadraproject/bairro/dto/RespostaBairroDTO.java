package com.clintonbrito.squadraproject.bairro.dto;

public record RespostaBairroDTO(
        Long codigoBairro,
        Long codigoMunicipio,
        String nome,
        Integer status
) {
}
