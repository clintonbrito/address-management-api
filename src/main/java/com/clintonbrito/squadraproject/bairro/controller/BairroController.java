package com.clintonbrito.squadraproject.bairro.controller;

import com.clintonbrito.squadraproject.bairro.dto.AtualizarBairroDTO;
import com.clintonbrito.squadraproject.bairro.dto.CadastroBairroDTO;
import com.clintonbrito.squadraproject.bairro.dto.RespostaBairroDTO;
import com.clintonbrito.squadraproject.bairro.mapper.BairroMapper;
import com.clintonbrito.squadraproject.bairro.model.Bairro;
import com.clintonbrito.squadraproject.bairro.service.BairroService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
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
            @RequestParam(value = "codigoBairro", required = false)
            @Min(value = 1, message = "O código do Bairro deve ser um número entre 1 e 999999999.")
            @Max(value = 999999999, message = "O código do Bairro deve ser um número entre 1 e 999999999.")
            Long codigoBairro,

            @RequestParam(value = "codigoMunicipio", required = false)
            @Min(value = 1, message = "O código do Município deve ser um número entre 1 e 999999999.")
            @Max(value = 999999999, message = "O código do Município deve ser um número entre 1 e 999999999.")
            Long codigoMunicipio,

            @RequestParam(value = "nome", required = false)
            @Length(min = 3, max = 60, message = "O nome do Bairro deve conter entre 3 e 60 caracteres.")
            @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O nome do Bairro deve conter apenas letras e espaços")
            String nome,

            @RequestParam(value = "status", required = false)
            @Min(value = 1, message = "O status deve ser um número inteiro positivo.")
            Integer status
    ) {

        Object bairros = bairroService.pesquisaPorFiltros(codigoBairro, codigoMunicipio, nome, status);

        return ResponseEntity.ok(bairros);
    }

    @PutMapping
    public ResponseEntity<List<RespostaBairroDTO>> atualizar(@RequestBody @Valid AtualizarBairroDTO dto) {
        Bairro bairro = bairroMapper.toEntity(dto);
        List<RespostaBairroDTO> bairroSalvo = bairroService.atualizar(bairro);
        return ResponseEntity.ok(bairroSalvo);
    }

}
