package com.clintonbrito.squadraproject.pessoa.controller;

import com.clintonbrito.squadraproject.pessoa.dto.CadastroPessoaDTO;
import com.clintonbrito.squadraproject.pessoa.dto.RespostaDetalhadaPessoaDTO;
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

    @GetMapping
    public ResponseEntity<?> pesquisar(
            @RequestParam(value = "codigoPessoa", required = false) Long codigoPessoa,
            @RequestParam(value = "login", required = false) String login,
            @RequestParam(value = "status", required = false) Integer status
    ) {

        if(codigoPessoa == null && login == null && status != null) {
            List<RespostaPessoaDTO> pessoas = pessoaService.pesquisarPorStatus(status);
            return ResponseEntity.ok(pessoas);
        }

        if(codigoPessoa == null && login != null && status == null) {
            List<RespostaPessoaDTO> pessoas = pessoaService.pesquisarPorLogin(login);
            return ResponseEntity.ok(pessoas);
        }

        if(codigoPessoa != null) {
            RespostaDetalhadaPessoaDTO pessoa = pessoaService.obterPessoa(codigoPessoa);
            return ResponseEntity.ok(pessoa);
        }

        List<RespostaPessoaDTO> pessoas = pessoaService.listarPessoas();

        return ResponseEntity.ok(pessoas);
    }

//    @PutMapping
//    public ResponseEntity<List<RespostaPessoaDTO>> atualizar(@RequestBody @Valid AtualizarPessoaDTO dto) {
//        Pessoa pessoa = pessoaMapper.toEntity(dto);
//        List<RespostaPessoaDTO> bairroSalvo = pessoaService.atualizar(pessoa);
//        return ResponseEntity.ok(bairroSalvo);
//    }

}
