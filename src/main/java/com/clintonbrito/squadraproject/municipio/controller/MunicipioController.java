package com.clintonbrito.squadraproject.municipio.controller;

import com.clintonbrito.squadraproject.municipio.dto.AtualizarMunicipioDTO;
import com.clintonbrito.squadraproject.municipio.dto.CadastroMunicipioDTO;
import com.clintonbrito.squadraproject.municipio.dto.RespostaMunicipioDTO;
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
    public ResponseEntity<List<RespostaMunicipioDTO>> salvar(@RequestBody @Valid CadastroMunicipioDTO dto) {
        Municipio municipio = municipioMapper.toEntity(dto);
        List<RespostaMunicipioDTO> municipioSalvo = municipioService.salvar(municipio);
        return ResponseEntity.ok(municipioSalvo);
    }

    @GetMapping
    public ResponseEntity<?> pesquisar(
            @RequestParam(value = "codigoMunicipio", required = false) Long codigoMunicipio,
            @RequestParam(value = "codigoUf", required = false) Long codigoUf,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "status", required = false) Integer status
    ) {

        if(codigoMunicipio == null && codigoUf == null && nome == null && status != null) {
            List<RespostaMunicipioDTO> municipios = municipioService.pesquisarPorStatus(status);
            return ResponseEntity.ok(municipios);
        }

        if(codigoMunicipio == null && codigoUf != null && nome == null && status == null) {
            List<RespostaMunicipioDTO> municipios = municipioService.pesquisarPorCodigoUf(codigoUf);
            return ResponseEntity.ok(municipios);
        }

        if(codigoMunicipio == null && codigoUf == null && nome != null && status == null) {
            List<RespostaMunicipioDTO> municipios = municipioService.pesquisarPorNome(nome);
            return ResponseEntity.ok(municipios);
        }

        if(codigoMunicipio  != null) {
            RespostaMunicipioDTO municipio = municipioService.obterMunicipio(codigoMunicipio);
            return ResponseEntity.ok(municipio);
        }

        List<RespostaMunicipioDTO> municipios = municipioService.listarMunicipios();

        return ResponseEntity.ok(municipios);
    }

    @PutMapping
    public ResponseEntity<List<RespostaMunicipioDTO>> atualizar(@RequestBody @Valid AtualizarMunicipioDTO dto) {
        Municipio municipio = municipioMapper.toEntity(dto);
        List<RespostaMunicipioDTO> municipioSalvo = municipioService.atualizar(municipio);
        return ResponseEntity.ok(municipioSalvo);
    }

}
