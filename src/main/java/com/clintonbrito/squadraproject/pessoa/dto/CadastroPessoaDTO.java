package com.clintonbrito.squadraproject.pessoa.dto;

import com.clintonbrito.squadraproject.endereco.dto.CadastroEnderecoDTO;
import jakarta.validation.constraints.*;

import java.util.List;

public record CadastroPessoaDTO(

        @NotBlank(message = "Campo obrigatório.")
        @Size(min = 3, max = 60, message = "O nome deve conter entre {min} e {max} caracteres")
        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras e espaços")
        String nome,

        @NotBlank(message = "Campo obrigatório.")
        @Size(min = 3, max = 60, message = "O sobrenome deve conter entre {min} e {max} caracteres")
        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras e espaços")
        String sobrenome,

        @NotNull(message = "Campo obrigatório.")
        @Min(value = 0, message = "A idade deve ser maior ou igual a 0")
        @Max(value = 130, message = "A idade deve ser menor ou igual a 130")
        Integer idade,

        @NotBlank(message = "Campo obrigatório.")
        @Size(min = 3, max = 60, message = "O login deve conter entre {min} e {max} caracteres.")
        @Pattern(regexp = "^[a-z0-9._-]+$", message = "O login deve conter apenas letras minúsculas, números e os caracteres . _ -")
        String login,

        @NotBlank(message = "Campo obrigatório.")
        @Size(min = 8, max = 60, message = "A senha deve conter entre {min} e {max} caracteres.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$",
                message = "A senha deve conter pelo menos uma letra minúscula, uma letra maiúscula, um número e um caractere especial."
        )
        String senha,

        @NotNull(message = "Campo obrigatório.")
        @Min(value = 1, message = "O status deve ser 1 ou 2")
        @Max(value = 2, message = "O status deve ser 1 ou 2")
        Integer status,

        @NotNull(message = "Campo obrigatório.")
        @Size(min = 1, message = "Você deve cadastrar pelo menos 1 endereço.")
        List<CadastroEnderecoDTO> enderecos

) {
}
