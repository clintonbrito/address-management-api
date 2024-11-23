package com.clintonbrito.squadraproject.uf.service;

import com.clintonbrito.squadraproject.uf.model.Uf;
import com.clintonbrito.squadraproject.uf.repository.UfRepository;
import com.clintonbrito.squadraproject.uf.validator.UfValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UfService {

    private final UfRepository ufRepository;
    private final UfValidator ufValidator;

    public List<Uf> salvar(Uf uf) {
        ufValidator.validar(uf);
        ufRepository.save(uf);
        return ufRepository.findAll();
    }

    public List<Uf> pesquisarPorStatus(Integer status) {
        return ufRepository.findByStatus(status);
    }

    public Uf obterUf(Long codigoUf, String sigla, String nome) {
        return ufRepository.findByCodigoUfOrSiglaOrNome(codigoUf, sigla, nome);
    }

    public List<Uf> listarUfs() {
        return ufRepository.findAll();
    }
}
