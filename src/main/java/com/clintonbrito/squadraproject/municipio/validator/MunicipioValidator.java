package com.clintonbrito.squadraproject.municipio.validator;

import com.clintonbrito.squadraproject.common.exception.RegistroDuplicadoException;
import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import com.clintonbrito.squadraproject.municipio.model.Municipio;
import com.clintonbrito.squadraproject.municipio.repository.MunicipioRepository;
import com.clintonbrito.squadraproject.uf.repository.UfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MunicipioValidator {

    private final MunicipioRepository municipioRepository;
    private final UfRepository ufRepository;

    public void validar(Municipio municipio) {
        if (municipio.getUf() == null) {
            throw new RegistroNaoEncontradoException("Uf não encontrada. Favor informar uma Uf válida.");
        }

        validarCodigoUf(municipio.getUf().getCodigoUF());
        validarNomeDuplicado(municipio);
    }

    private void validarCodigoUf(Long codigoUF) {
        if(!ufRepository.existsByCodigoUF(codigoUF)) {
            throw new RegistroNaoEncontradoException("Código da Uf não encontrado. Favor informar um 'codigoUf' válido.");
        }
    }

    private void validarNomeDuplicado(Municipio municipio) {
        boolean duplicado = municipioRepository.findByNome(municipio.getNome())
                .filter(m -> !m.getCodigoMunicipio().equals(municipio.getCodigoMunicipio()))
                .isPresent();

        if(duplicado) {
            throw new RegistroDuplicadoException("Nome do Município já cadastrado. Favor informar um novo 'nome'.");
        }
    }
}
