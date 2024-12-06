package com.clintonbrito.squadraproject.pessoa.controller;

import com.clintonbrito.squadraproject.pessoa.dto.AtualizarPessoaDTO;
import com.clintonbrito.squadraproject.pessoa.dto.CadastroPessoaDTO;
import com.clintonbrito.squadraproject.pessoa.dto.RespostaPessoaDTO;
import com.clintonbrito.squadraproject.pessoa.mapper.PessoaMapper;
import com.clintonbrito.squadraproject.pessoa.model.Pessoa;
import com.clintonbrito.squadraproject.pessoa.service.PessoaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;
    private final PessoaMapper pessoaMapper;

    @PostMapping
    public ResponseEntity<List<RespostaPessoaDTO>> salvar(@RequestBody @Valid CadastroPessoaDTO dto) {
        Pessoa pessoa = pessoaMapper.toEntity(dto);
        List<RespostaPessoaDTO> pessoaSalva = pessoaService.salvar(pessoa);
        return ResponseEntity.ok(pessoaSalva);
    }

    @GetMapping
    public ResponseEntity<?> pesquisar(
            @RequestParam(value = "codigoPessoa", required = false)
            @Min(value = 1, message = "O código da Pessoa deve ser um número entre 1 e 999999999.")
            @Max(value = 999999999, message = "O código da Pessoa deve ser um número entre 1 e 999999999.")
            Long codigoPessoa,

            @RequestParam(value = "login", required = false)
            @Size(min = 3, max = 50, message = "O login deve conter entre {min} e {max} caracteres.")
            @Pattern(regexp = "^[a-z0-9._-]+$", message = "O login deve conter apenas letras minúsculas, números e os caracteres . _ -")
            String login,

            @RequestParam(value = "status", required = false)
            @Min(value = 1, message = "O status deve ser um número inteiro positivo.")
            Integer status
    ) {

        Object pessoas = pessoaService.pesquisaPorFiltros(codigoPessoa, login, status);

        return ResponseEntity.ok(pessoas);
    }

    @PutMapping
    public ResponseEntity<List<RespostaPessoaDTO>> atualizar(@RequestBody @Valid AtualizarPessoaDTO dto) {
        Pessoa pessoa = pessoaMapper.toEntity(dto);
        List<RespostaPessoaDTO> pessoaSalva = pessoaService.atualizar(pessoa);
        return ResponseEntity.ok(pessoaSalva);
    }

}
