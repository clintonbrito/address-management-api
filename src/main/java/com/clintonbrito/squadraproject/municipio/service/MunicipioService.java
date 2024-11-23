package com.clintonbrito.squadraproject.municipio.service;

import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import com.clintonbrito.squadraproject.municipio.model.Municipio;
import com.clintonbrito.squadraproject.municipio.repository.MunicipioRepository;
import com.clintonbrito.squadraproject.municipio.validator.MunicipioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MunicipioService {

    private final MunicipioRepository municipioRepository;
    private final MunicipioValidator municipioValidator;

    public List<Municipio> salvar(Municipio municipio) {
        municipioValidator.validar(municipio);
        municipioRepository.save(municipio);
        return municipioRepository.findAll();
    }

//    public List<Municipio> pesquisarPorStatus(Integer status) {
//        return municipioRepository.findByStatus(status);
//    }
//
//    public Municipio obterUf(Long codigoUf, String sigla, String nome) {
//        return municipioRepository.findByCodigoUfOrSiglaOrNome(codigoUf, sigla, nome);
//    }
//
//    public List<Municipio> listarUfs() {
//        return municipioRepository.findAll();
//    }
//
//    public List<Municipio> atualizar(Municipio municipio) {
//        municipioRepository.findById(municipio.getCodigoMunicipio())
//                .orElseThrow(() -> new RegistroNaoEncontradoException("UF não encontrada. Favor informar um 'codigoUf' válido."));
//
//        municipioValidator.validar(municipio);
//        municipioRepository.save(municipio);
//        return municipioRepository.findAll();
//    }

}
