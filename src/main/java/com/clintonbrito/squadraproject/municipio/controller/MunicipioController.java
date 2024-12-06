package com.clintonbrito.squadraproject.municipio.controller;

import com.clintonbrito.squadraproject.municipio.dto.AtualizarMunicipioDTO;
import com.clintonbrito.squadraproject.municipio.dto.CadastroMunicipioDTO;
import com.clintonbrito.squadraproject.municipio.dto.RespostaMunicipioDTO;
import com.clintonbrito.squadraproject.municipio.mapper.MunicipioMapper;
import com.clintonbrito.squadraproject.municipio.model.Municipio;
import com.clintonbrito.squadraproject.municipio.service.MunicipioService;
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
            @RequestParam(value = "codigoMunicipio", required = false)
            @Min(value = 1, message = "O código do Município deve ser um número entre 1 e 999999999.")
            @Max(value = 999999999, message = "O código do Município deve ser um número entre 1 e 999999999.")
            Long codigoMunicipio,

            @RequestParam(value = "codigoUF", required = false)
            @Min(value = 1, message = "O código da Uf deve ser um número entre 1 e 999999999.")
            @Max(value = 999999999, message = "O código da Uf deve ser um número entre 1 e 999999999.")
            Long codigoUF,

            @RequestParam(value = "nome", required = false)
            @Length(min = 3, max = 60, message = "O nome do Município deve conter entre 3 e 60 caracteres.")
            @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O nome do Município deve conter apenas letras e espaços")
            String nome,

            @RequestParam(value = "status", required = false)
            @Min(value = 1, message = "O status deve ser um número inteiro positivo.")
            Integer status
    ) {

        Object municipios = municipioService.pesquisaPorFiltros(codigoMunicipio, codigoUF, nome, status);

        return ResponseEntity.ok(municipios);
    }

    @PutMapping
    public ResponseEntity<List<RespostaMunicipioDTO>> atualizar(@RequestBody @Valid AtualizarMunicipioDTO dto) {
        Municipio municipio = municipioMapper.toEntity(dto);
        List<RespostaMunicipioDTO> municipioSalvo = municipioService.atualizar(municipio);
        return ResponseEntity.ok(municipioSalvo);
    }

}
