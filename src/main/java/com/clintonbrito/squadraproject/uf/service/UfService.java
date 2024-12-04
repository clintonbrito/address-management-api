package com.clintonbrito.squadraproject.uf.service;

import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import com.clintonbrito.squadraproject.uf.model.Uf;
import com.clintonbrito.squadraproject.uf.repository.UfRepository;
import com.clintonbrito.squadraproject.uf.repository.specs.UfSpecs;
import com.clintonbrito.squadraproject.uf.validator.UfValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Object pesquisaPorFiltros(Long codigoUF, String sigla, String nome, Integer status) {

        // select * tb_uf where codigo_uf = :codigo_uf and sigla = ...
//        Specification<Uf> specs = Specification
//                .where(UfSpecs.codigoUFEqual(codigoUF))
//                .and(UfSpecs.siglaEqual(sigla))
//                .and(UfSpecs.nomeLike(nome))
//                .and(UfSpecs.statusEqual(status));

        // select * from tb_uf where 0 = 0
        Specification<Uf> specs = Specification
                .where((root, query, cb) -> cb.conjunction());

        if(codigoUF != null) {
            specs = specs.and(UfSpecs.codigoUFEqual(codigoUF));
        }

        if(sigla != null) {
            specs = specs.and(UfSpecs.siglaEqual(sigla));
        }

        if(nome != null) {
            specs = specs.and(UfSpecs.nomeLike(nome));
        }

        if(status != null) {
            specs = specs.and(UfSpecs.statusEqual(status));
        }

        List<Uf> result = ufRepository.findAll(specs);

        if (codigoUF == null && sigla == null && nome == null && status != null) {
            return result;
        }

        if(result.isEmpty()) {
            return new ArrayList<>();
        }

        if(result.size() == 1) {
            return result.getFirst();
        }

        return result;
    }

    public List<Uf> atualizar(Uf uf) {
        ufRepository.findById(uf.getCodigoUF())
                .orElseThrow(() -> new RegistroNaoEncontradoException("UF não encontrada. Favor informar um 'codigoUf' válido."));

        ufValidator.validar(uf);
        ufRepository.save(uf);
        return ufRepository.findAll();
    }

}
