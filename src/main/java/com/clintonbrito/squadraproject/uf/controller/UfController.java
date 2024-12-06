package com.clintonbrito.squadraproject.uf.controller;

import com.clintonbrito.squadraproject.uf.dto.AtualizarUfDTO;
import com.clintonbrito.squadraproject.uf.dto.CadastroUfDTO;
import com.clintonbrito.squadraproject.uf.mapper.UfMapper;
import com.clintonbrito.squadraproject.uf.model.Uf;
import com.clintonbrito.squadraproject.uf.service.UfService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uf")
@RequiredArgsConstructor
public class UfController {

    private final UfService ufService;
    private final UfMapper ufMapper;

    @PostMapping
    public ResponseEntity<List<Uf>> salvar(@RequestBody @Valid CadastroUfDTO dto) {
        Uf uf = ufMapper.toEntity(dto);
        List<Uf> ufSalva = ufService.salvar(uf);
        return ResponseEntity.ok(ufSalva);
    }

    @GetMapping
    public ResponseEntity<?> pesquisar(
            @RequestParam(value = "codigoUF", required = false)
            @Min(value = 1, message = "O código da UF deve ser um número entre 1 e 999999999.")
            @Max(value = 999999999, message = "O código da UF deve ser um número entre 1 e 999999999.")
            Long codigoUF,

            @RequestParam(value = "sigla", required = false)
            @Length(min = 2, max = 2, message = "A sigla da UF deve conter 2 caracteres.")
            @Pattern(regexp = "^[A-Z]+$", message = "A sigla deve conter apenas letras maiúsculas")
            String sigla,

            @RequestParam(value = "nome", required = false)
            @Length(min = 3, max = 60, message = "O nome da UF deve conter entre 3 e 60 caracteres.")
            @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O nome da UF deve conter apenas letras e espaços")
            String nome,

            @RequestParam(value = "status", required = false)
            @Min(value = 1, message = "O status deve ser um número inteiro positivo.")
            Integer status
    ) {

        Object ufs = ufService.pesquisaPorFiltros(codigoUF, sigla, nome, status);

        return ResponseEntity.ok(ufs);
    }

    @PutMapping
    public ResponseEntity<List<Uf>> atualizar(@RequestBody @Valid AtualizarUfDTO dto) {
        Uf uf = ufMapper.toEntity(dto);
        List<Uf> ufSalva = ufService.atualizar(uf);
        return ResponseEntity.ok(ufSalva);
    }

}
