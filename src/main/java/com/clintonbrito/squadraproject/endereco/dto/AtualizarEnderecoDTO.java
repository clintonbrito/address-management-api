package com.clintonbrito.squadraproject.endereco.dto;

import jakarta.validation.constraints.*;

public record AtualizarEnderecoDTO(
//        @NotNull(message = "Campo obrigatório.") // Pode ser nulo para novos endereços
        Long codigoEndereco,

        @NotNull(message = "Campo obrigatório.")
        Long codigoPessoa,

        @NotNull(message = "Campo obrigatório.")
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
