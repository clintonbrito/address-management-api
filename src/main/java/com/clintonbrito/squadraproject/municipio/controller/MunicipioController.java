package com.clintonbrito.squadraproject.municipio.controller;

import com.clintonbrito.squadraproject.municipio.dto.AtualizarMunicipioDTO;
import com.clintonbrito.squadraproject.municipio.dto.CadastroMunicipioDTO;
import com.clintonbrito.squadraproject.municipio.mapper.MunicipioMapper;
import com.clintonbrito.squadraproject.municipio.model.Municipio;
import com.clintonbrito.squadraproject.municipio.service.MunicipioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipio")
@RequiredArgsConstructor
public class MunicipioController {

    private final MunicipioService municipioService;
    private final MunicipioMapper municipioMapper;

    @PostMapping
    public ResponseEntity<List<Municipio>> salvar(@RequestBody @Valid CadastroMunicipioDTO dto) {
        Municipio municipio = municipioMapper.toEntity(dto);
        List<Municipio> municipioSalvo = municipioService.salvar(municipio);
        return ResponseEntity.ok(municipioSalvo);
    }

//    @GetMapping
//    public ResponseEntity<?> pesquisar(
//            @RequestParam(value = "codigoUf", required = false) Long codigoUf,
//            @RequestParam(value = "sigla", required = false) String sigla,
//            @RequestParam(value = "nome", required = false) String nome,
//            @RequestParam(value = "status", required = false) Integer status
//    ) {
//
//        if(codigoUf == null && sigla == null && nome == null && status != null) {
//            List<Municipio> municipios = municipioService.pesquisarPorStatus(status);
//            return ResponseEntity.ok(municipios);
//        }
//
//        if(codigoUf != null || sigla  != null || nome  != null) {
//            Municipio municipio = municipioService.obterUf(codigoUf, sigla, nome);
//            return ResponseEntity.ok(municipio);
//        }
//
//        List<Municipio> municipios = municipioService.listarUfs();
//
//        return ResponseEntity.ok(municipios);
//    }

//    @PutMapping
//    public ResponseEntity<List<Municipio>> atualizar(@RequestBody @Valid AtualizarMunicipioDTO dto) {
//        Municipio municipio = municipioMapper.toEntity(dto);
//        List<Municipio> municipioSalva = municipioService.atualizar(municipio);
//        return ResponseEntity.ok(municipioSalva);
//    }

}
