package com.clintonbrito.squadraproject.uf.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroUfDTO(
        @NotBlank(message = "Campo obrigatório")
        String sigla,

        @NotBlank(message = "Campo obrigatório")
        String nome,

        @NotNull(message = "Campo obrigatório")
        Integer status
) {
}
