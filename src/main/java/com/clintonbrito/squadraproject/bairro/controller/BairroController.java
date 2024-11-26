package com.clintonbrito.squadraproject.bairro.controller;

import com.clintonbrito.squadraproject.bairro.dto.AtualizarBairroDTO;
import com.clintonbrito.squadraproject.bairro.dto.CadastroBairroDTO;
import com.clintonbrito.squadraproject.bairro.dto.RespostaBairroDTO;
import com.clintonbrito.squadraproject.bairro.mapper.BairroMapper;
import com.clintonbrito.squadraproject.bairro.model.Bairro;
import com.clintonbrito.squadraproject.bairro.service.BairroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bairro")
@RequiredArgsConstructor
public class BairroController {

    private final BairroService bairroService;
    private final BairroMapper bairroMapper;

    @PostMapping
    public ResponseEntity<List<RespostaBairroDTO>> salvar(@RequestBody @Valid CadastroBairroDTO dto) {
        Bairro bairro = bairroMapper.toEntity(dto);
        List<RespostaBairroDTO> bairroSalvo = bairroService.salvar(bairro);
        return ResponseEntity.ok(bairroSalvo);
    }

//    @GetMapping
//    public ResponseEntity<?> pesquisar(
//            @RequestParam(value = "codigoMunicipio", required = false) Long codigoMunicipio,
//            @RequestParam(value = "codigoUf", required = false) Long codigoUf,
//            @RequestParam(value = "nome", required = false) String nome,
//            @RequestParam(value = "status", required = false) Integer status
//    ) {
//
//        if(codigoMunicipio == null && codigoUf == null && nome == null && status != null) {
//            List<RespostaBairroDTO> municipios = bairroService.pesquisarPorStatus(status);
//            return ResponseEntity.ok(municipios);
//        }
//
//        if(codigoMunicipio == null && codigoUf != null && nome == null && status == null) {
//            List<RespostaBairroDTO> municipios = bairroService.pesquisarPorCodigoUf(codigoUf);
//            return ResponseEntity.ok(municipios);
//        }
//
//        if(codigoMunicipio == null && codigoUf == null && nome != null && status == null) {
//            List<RespostaBairroDTO> municipios = bairroService.pesquisarPorNome(nome);
//            return ResponseEntity.ok(municipios);
//        }
//
//        if(codigoMunicipio  != null) {
//            RespostaBairroDTO municipio = bairroService.obterMunicipio(codigoMunicipio);
//            return ResponseEntity.ok(municipio);
//        }
//
//        List<RespostaBairroDTO> municipios = bairroService.listarMunicipios();
//
//        return ResponseEntity.ok(municipios);
//    }
//
//    @PutMapping
//    public ResponseEntity<List<RespostaBairroDTO>> atualizar(@RequestBody @Valid AtualizarBairroDTO dto) {
//        Bairro bairro = bairroMapper.toEntity(dto);
//        List<RespostaBairroDTO> municipioSalva = bairroService.atualizar(bairro);
//        return ResponseEntity.ok(municipioSalva);
//    }

}
