package com.clintonbrito.squadraproject.endereco.dto;

import jakarta.validation.constraints.*;

public record CadastroEnderecoDTO(
        @NotNull(message = "Campo obrigatório.")
        @Min(value = 1, message = "O código da Pessoa deve ser um número entre 1 e 999999999.")
        @Max(value = 999999999, message = "O código da Pessoa deve ser um número entre 1 e 999999999.")
        Long codigoPessoa,

        @NotNull(message = "Campo obrigatório.")
        @Min(value = 1, message = "O código do Bairro deve ser um número entre 1 e 999999999.")
        @Max(value = 999999999, message = "O código do Bairro deve ser um número entre 1 e 999999999.")
        Long codigoBairro,

        @NotBlank(message = "Campo obrigatório.")
        @Size(min = 4, max = 100, message = "O nome da rua deve conter entre {min} e {max} caracteres")
        @Pattern(regexp = "^[a-zA-Zà-ÿÀ-Ú0-9\\s.,#-]+$", message = "O nome da rua deve conter apenas letras, números e os caracteres . , # -")
        String nomeRua,

        @NotNull(message = "Campo obrigatório.")
        @Min(value = 1, message = "O número deve ser maior ou igual a 1")
        @Max(value = 99999, message = "O número deve ser menor ou igual a 99999")
        Integer numero,

        @Size(max = 20, message = "O complemento deve conter no máximo {max} caracteres")
        @Pattern(regexp = "^[a-zA-Z0-9\\s.,#-]+$", message = "O complemento deve conter apenas letras, números e os caracteres . , # -")
        String complemento,

        @NotBlank(message = "Campo obrigatório.")
        @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "O CEP deve ser um número no formato 00000-000.")
        String cep
) {
}
