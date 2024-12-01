package com.clintonbrito.squadraproject.endereco.dto.custom;

public record BairroParaEnderecoDTO(
        Long codigoBairro,
        Long codigoMunicipio,
        String nome,
        Integer status,
        MunicipioParaEnderecoDTO municipio
) {
    public BairroParaEnderecoDTO {
        if (municipio != null && municipio.codigoMunicipio() != null) {
            codigoMunicipio = municipio.codigoMunicipio();
        }
    }
}