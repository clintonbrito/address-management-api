package com.clintonbrito.squadraproject.municipio.dto;

public record RespostaMunicipioDTO(
        Long codigoMunicipio,
        Long codigoUf,
        String nome,
        Integer status
) {
}
