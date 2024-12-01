package com.clintonbrito.squadraproject.endereco.dto;

import com.clintonbrito.squadraproject.endereco.dto.custom.BairroParaEnderecoDTO;

public record RespostaEnderecoDTO(
        Long codigoEndereco,
        Long codigoPessoa,
        Long codigoBairro,
        String nomeRua,
        Integer numero,
        String complemento,
        String cep,
        BairroParaEnderecoDTO bairro
) {
}
