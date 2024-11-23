package com.clintonbrito.squadraproject.uf.validator;

import com.clintonbrito.squadraproject.common.exception.RegistroDuplicadoException;
import com.clintonbrito.squadraproject.uf.model.Uf;
import com.clintonbrito.squadraproject.uf.repository.UfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UfValidator {

    private final UfRepository ufRepository;

    public void validar(Uf uf) {
        if(existeUfNoBanco(uf)) {
            throw new RegistroDuplicadoException("UF já cadastrada. Favor informar uma nova 'Sigla'.");
        }

        if(existeEstadoNoBanco(uf)) {
            throw new RegistroDuplicadoException("Nome da UF já cadastrada. Favor informar um novo 'Nome'.");
        }
    }

    private boolean existeUfNoBanco(Uf uf) {
        return ufRepository.findBySigla(uf.getSigla())
                .filter(ufEncontrada -> !ufEncontrada.getCodigoUf().equals(uf.getCodigoUf()))
                .isPresent();
    }

    private boolean existeEstadoNoBanco(Uf uf) {
        return ufRepository.findByNome(uf.getNome())
                .filter(estadoEncontrado -> !estadoEncontrado.getCodigoUf().equals(uf.getCodigoUf()))
                .isPresent();
    }
}
