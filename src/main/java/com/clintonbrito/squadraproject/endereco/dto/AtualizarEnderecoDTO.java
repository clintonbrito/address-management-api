package com.clintonbrito.squadraproject.endereco.dto;

import jakarta.validation.constraints.*;

public record AtualizarEnderecoDTO(
        @NotNull(message = "Campo obrigatório.")
        Long codigoBairro,

        @NotNull(message = "Campo obrigatório.")
        Long codigoMunicipio,

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
