package com.clintonbrito.squadraproject.uf.validator;

import com.clintonbrito.squadraproject.uf.exception.RegistroDuplicadoException;
import com.clintonbrito.squadraproject.uf.model.Uf;
import com.clintonbrito.squadraproject.uf.repository.UfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    private boolean existeUfNoBanco(Uf uf){
        Optional<Uf> ufEncontrada = ufRepository.findByCodigoUf(uf.getCodigoUf());

        if(uf.getCodigoUf() == null) {
            return ufEncontrada.isPresent();
        }

        return ufEncontrada
                .map(Uf::getCodigoUf)
                .stream()
                .anyMatch(id -> !id.equals(uf.getCodigoUf()));
    }

    private boolean existeEstadoNoBanco(Uf uf) {
        Optional<Uf> estadoEncontrado = ufRepository.findByNome(uf.getNome());

        if(uf.getNome() == null) {
            return estadoEncontrado.isPresent();
        }

        return estadoEncontrado
                .map(Uf::getNome)
                .stream()
                .anyMatch(nome -> !nome.equals(uf.getNome()));
    }
}
