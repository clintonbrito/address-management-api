package com.clintonbrito.squadraproject.municipio.dto;

import jakarta.validation.constraints.*;

public record AtualizarMunicipioDTO(
        @NotNull(message = "Campo obrigatório.")
        Long codigoMunicipio,

        @NotNull(message = "Campo obrigatório.")
        @Min(value = 1, message = "O código da UF deve ser um número entre 1 e 999999999.")
        @Max(value = 999999999, message = "O código da UF deve ser um número entre 1 e 999999999.")
        Long codigoUF,

        @NotBlank(message = "Campo obrigatório.")
        @Size(min = 3, max = 60, message = "O nome deve conter entre {min} e {max} caracteres")
        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras e espaços")
        String nome,

        @NotNull(message = "Campo obrigatório.")
        @Min(value = 1, message = "O status deve ser 1 ou 2")
        @Max(value = 2, message = "O status deve ser 1 ou 2")
        Integer status
) {
}
