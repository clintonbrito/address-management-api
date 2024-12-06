package com.clintonbrito.squadraproject.endereco.dto.custom;

public record MunicipioParaEnderecoDTO(
        Long codigoMunicipio,
        Long codigoUF,
        String nome,
        Integer status,
        UfParaEnderecoDTO uf
) {
    public MunicipioParaEnderecoDTO {
        if (uf != null && uf.codigoUF() != null) {
            codigoUF = uf.codigoUF();
        }
    }
}
