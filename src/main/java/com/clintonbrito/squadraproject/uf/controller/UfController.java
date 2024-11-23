package com.clintonbrito.squadraproject.uf.controller;

import com.clintonbrito.squadraproject.uf.dto.AtualizarUfDTO;
import com.clintonbrito.squadraproject.uf.dto.CadastroUfDTO;
import com.clintonbrito.squadraproject.uf.mapper.UfMapper;
import com.clintonbrito.squadraproject.uf.model.Uf;
import com.clintonbrito.squadraproject.uf.service.UfService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
            @RequestParam(value = "codigoUf", required = false) Long codigoUf,
            @RequestParam(value = "sigla", required = false) String sigla,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "status", required = false) Integer status
    ) {

        if(codigoUf == null && sigla == null && nome == null && status != null) {
            List<Uf> ufs = ufService.pesquisarPorStatus(status);
            return ResponseEntity.ok(ufs);
        }

        if(codigoUf != null || sigla  != null || nome  != null) {
            Uf uf = ufService.obterUf(codigoUf, sigla, nome);
            return ResponseEntity.ok(uf);
        }

        List<Uf> ufs = ufService.listarUfs();

        return ResponseEntity.ok(ufs);
    }

    @PutMapping
    public ResponseEntity<List<Uf>> atualizar(@RequestBody @Valid AtualizarUfDTO dto) {
        Uf uf = ufMapper.toEntity(dto);
        List<Uf> ufSalva = ufService.atualizar(uf);
        return ResponseEntity.ok(ufSalva);
    }

}
