package com.clintonbrito.squadraproject.pessoa.controller;

import com.clintonbrito.squadraproject.pessoa.dto.AtualizarPessoaDTO;
import com.clintonbrito.squadraproject.pessoa.dto.CadastroPessoaDTO;
import com.clintonbrito.squadraproject.pessoa.dto.RespostaPessoaDTO;
import com.clintonbrito.squadraproject.pessoa.mapper.PessoaMapper;
import com.clintonbrito.squadraproject.pessoa.model.Pessoa;
import com.clintonbrito.squadraproject.pessoa.service.PessoaService;
import jakarta.validation.Valid;
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

//    @GetMapping
//    public ResponseEntity<?> pesquisar(
//            @RequestParam(value = "codigoBairro", required = false) Long codigoBairro,
//            @RequestParam(value = "codigoMunicipio", required = false) Long codigoMunicipio,
//            @RequestParam(value = "nome", required = false) String nome,
//            @RequestParam(value = "status", required = false) Integer status
//    ) {
//
//        if(codigoBairro == null && codigoMunicipio == null && nome == null && status != null) {
//            List<RespostaPessoaDTO> bairros = pessoaService.pesquisarPorStatus(status);
//            return ResponseEntity.ok(bairros);
//        }
//
//        if(codigoBairro == null && codigoMunicipio != null && nome == null && status == null) {
//            List<RespostaPessoaDTO> bairros = pessoaService.pesquisarPorCodigoMunicipio(codigoMunicipio);
//            return ResponseEntity.ok(bairros);
//        }
//
//        if(codigoBairro == null && codigoMunicipio == null && nome != null && status == null) {
//            List<RespostaPessoaDTO> bairros = pessoaService.pesquisarPorNome(nome);
//            return ResponseEntity.ok(bairros);
//        }
//
//        if(codigoBairro != null) {
//            RespostaPessoaDTO bairro = pessoaService.obterBairro(codigoBairro);
//            return ResponseEntity.ok(bairro);
//        }
//
//        List<RespostaPessoaDTO> bairros = pessoaService.listarBairros();
//
//        return ResponseEntity.ok(bairros);
//    }

//    @PutMapping
//    public ResponseEntity<List<RespostaPessoaDTO>> atualizar(@RequestBody @Valid AtualizarPessoaDTO dto) {
//        Pessoa pessoa = pessoaMapper.toEntity(dto);
//        List<RespostaPessoaDTO> bairroSalvo = pessoaService.atualizar(pessoa);
//        return ResponseEntity.ok(bairroSalvo);
//    }

}
