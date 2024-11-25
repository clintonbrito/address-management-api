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
            throw new RegistroNaoEncontradoException("UF não encontrada. Favor informar uma UF válida.");
        }

        validarCodigoUf(municipio.getUf().getCodigoUf());
        validarNomeDuplicado(municipio);
    }

    private void validarCodigoUf(Long codigoUf) {
        if(!ufRepository.existsByCodigoUf(codigoUf)) {
            throw new RegistroNaoEncontradoException("Código da UF não encontrado. Favor informar um 'codigoUf' válido.");
        }
    }

    private void validarNomeDuplicado(Municipio municipio) {
        boolean duplicado = municipioRepository.findByNome(municipio.getNome())
                .filter(m -> !m.getCodigoMunicipio().equals(municipio.getCodigoMunicipio()))
                .isPresent();

        if(duplicado) {
            throw new RegistroDuplicadoException("Nome do Municipio já cadastrado. Favor informar um novo 'nome'.");
        }
    }
}
