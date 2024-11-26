package com.clintonbrito.squadraproject.bairro.validator;

import com.clintonbrito.squadraproject.bairro.model.Bairro;
import com.clintonbrito.squadraproject.bairro.repository.BairroRepository;
import com.clintonbrito.squadraproject.common.exception.RegistroDuplicadoException;
import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import com.clintonbrito.squadraproject.municipio.repository.MunicipioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BairroValidator {

    private final BairroRepository bairroRepository;
    private final MunicipioRepository municipioRepository;

    public void validar(Bairro bairro) {
        if (bairro.getMunicipio() == null) {
            throw new RegistroNaoEncontradoException("Município não encontrado. Favor informar um 'codigoMunicipio' válido.");
        }

        validarCodigoMunicipio(bairro.getMunicipio().getCodigoMunicipio());
        validarNomeDuplicado(bairro);
    }

    private void validarCodigoMunicipio(Long codigoMunicipio) {
        if(!municipioRepository.existsByCodigoMunicipio(codigoMunicipio)) {
            throw new RegistroNaoEncontradoException("Código do Município não encontrado. Favor informar um 'codigoMunicipio' válido.");
        }
    }

    private void validarNomeDuplicado(Bairro bairro) {
        boolean duplicado = bairroRepository.findByNome(bairro.getNome())
                .filter(b -> !b.getCodigoBairro().equals(bairro.getCodigoBairro()))
                .isPresent();

        if(duplicado) {
            throw new RegistroDuplicadoException("Nome do Bairro já cadastrado. Favor informar um novo 'nome'.");
        }
    }
}
