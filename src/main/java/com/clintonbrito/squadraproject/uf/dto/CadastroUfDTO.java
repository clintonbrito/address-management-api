package com.clintonbrito.squadraproject.uf.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroUfDTO(
        @NotBlank(message = "Campo obrigatório")
        String sigla,

        @NotBlank(message = "Campo obrigatório")
        String nome,

        @NotBlank(message = "Campo obrigatório")
        Integer status
) {
}
