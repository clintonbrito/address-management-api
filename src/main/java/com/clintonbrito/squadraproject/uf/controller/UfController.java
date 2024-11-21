package com.clintonbrito.squadraproject.uf.controller;

import com.clintonbrito.squadraproject.uf.dto.CadastroUfDTO;
import com.clintonbrito.squadraproject.uf.model.Uf;
import com.clintonbrito.squadraproject.uf.service.UfService;
import com.clintonbrito.squadraproject.uf.mapper.UfMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/uf")
@RequiredArgsConstructor
public class UfController {

    private final UfService ufService;
    private final UfMapper ufMapper;

    @PostMapping
    public ResponseEntity<List<Uf>> salvar(@RequestBody CadastroUfDTO dto) {
        Uf uf = ufMapper.toEntity(dto);
        List<Uf> ufSalva = ufService.salvar(uf);
        return ResponseEntity.ok(ufSalva);
    }
}
