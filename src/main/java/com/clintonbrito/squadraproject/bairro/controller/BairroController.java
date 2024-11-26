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

    @GetMapping
    public ResponseEntity<?> pesquisar(
            @RequestParam(value = "codigoBairro", required = false) Long codigoBairro,
            @RequestParam(value = "codigoMunicipio", required = false) Long codigoMunicipio,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "status", required = false) Integer status
    ) {

        if(codigoBairro == null && codigoMunicipio == null && nome == null && status != null) {
            List<RespostaBairroDTO> bairros = bairroService.pesquisarPorStatus(status);
            return ResponseEntity.ok(bairros);
        }

        if(codigoBairro == null && codigoMunicipio != null && nome == null && status == null) {
            List<RespostaBairroDTO> bairros = bairroService.pesquisarPorCodigoMunicipio(codigoMunicipio);
            return ResponseEntity.ok(bairros);
        }

        if(codigoBairro == null && codigoMunicipio == null && nome != null && status == null) {
            List<RespostaBairroDTO> bairros = bairroService.pesquisarPorNome(nome);
            return ResponseEntity.ok(bairros);
        }

        if(codigoBairro != null) {
            RespostaBairroDTO bairro = bairroService.obterBairro(codigoBairro);
            return ResponseEntity.ok(bairro);
        }

        List<RespostaBairroDTO> bairros = bairroService.listarBairros();

        return ResponseEntity.ok(bairros);
    }

    @PutMapping
    public ResponseEntity<List<RespostaBairroDTO>> atualizar(@RequestBody @Valid AtualizarBairroDTO dto) {
        Bairro bairro = bairroMapper.toEntity(dto);
        List<RespostaBairroDTO> bairroSalvo = bairroService.atualizar(bairro);
        return ResponseEntity.ok(bairroSalvo);
    }

}
