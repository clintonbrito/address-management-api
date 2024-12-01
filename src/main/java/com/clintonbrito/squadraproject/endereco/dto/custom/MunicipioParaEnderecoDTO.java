package com.clintonbrito.squadraproject.endereco.dto.custom;

public record MunicipioParaEnderecoDTO(
        Long codigoMunicipio,
        Long codigoUf,
        String nome,
        Integer status,
        UfParaEnderecoDTO uf
) {
    public MunicipioParaEnderecoDTO {
        if (uf != null && uf.codigoUf() != null) {
            codigoUf = uf.codigoUf();
        }
    }
}
