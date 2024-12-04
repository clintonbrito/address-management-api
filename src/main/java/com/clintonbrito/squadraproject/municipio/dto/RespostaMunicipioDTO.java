package com.clintonbrito.squadraproject.municipio.dto;

public record RespostaMunicipioDTO(
        Long codigoMunicipio,
        Long codigoUF,
        String nome,
        Integer status
) {
}
