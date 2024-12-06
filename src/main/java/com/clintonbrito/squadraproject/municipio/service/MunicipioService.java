package com.clintonbrito.squadraproject.municipio.service;

import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import com.clintonbrito.squadraproject.municipio.dto.RespostaMunicipioDTO;
import com.clintonbrito.squadraproject.municipio.mapper.MunicipioMapper;
import com.clintonbrito.squadraproject.municipio.model.Municipio;
import com.clintonbrito.squadraproject.municipio.repository.MunicipioRepository;
import com.clintonbrito.squadraproject.municipio.repository.specs.MunicipioSpecs;
import com.clintonbrito.squadraproject.municipio.validator.MunicipioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MunicipioService {

    private final MunicipioRepository municipioRepository;
    private final MunicipioValidator municipioValidator;
    private final MunicipioMapper municipioMapper;

    public List<RespostaMunicipioDTO> salvar(Municipio municipio) {
        municipioValidator.validar(municipio);
        municipioRepository.save(municipio);
        List<Municipio> municipios = municipioRepository.findAll();
        return municipioMapper.toResponseDTOList(municipios);
    }

    public Object pesquisaPorFiltros(Long codigoMunicipio, Long codigoUF, String nome, Integer status) {

        Specification<Municipio> specs = Specification
                .where((root, query, cb) -> cb.conjunction());

        if (codigoMunicipio != null) {
            specs = specs.and(MunicipioSpecs.codigoMunicipioEqual(codigoMunicipio));
        }

        if (codigoUF != null) {
            specs = specs.and(MunicipioSpecs.codigoUFEqual(codigoUF));
        }

        if (nome != null) {
            specs = specs.and(MunicipioSpecs.nomeLike(nome));
        }

        if (status != null) {
            specs = specs.and(MunicipioSpecs.statusEqual(status));
        }

        List<Municipio> rawResult = municipioRepository.findAll(specs);
        List<RespostaMunicipioDTO> result = municipioMapper.toResponseDTOList(rawResult);

        if (result.isEmpty()) {
            return new ArrayList<>();
        }

        if (codigoMunicipio != null) {
            return result.getFirst();
        }

        return result;
    }

    public List<RespostaMunicipioDTO> atualizar(Municipio municipio) {
        municipioRepository.findById(municipio.getCodigoMunicipio())
                .orElseThrow(() ->
                        new RegistroNaoEncontradoException("Município não encontrado. Favor informar um 'codigoMunicipio' válido."));

        municipioValidator.validar(municipio);
        municipioRepository.save(municipio);
        List<Municipio> municipios = municipioRepository.findAll();
        return municipioMapper.toResponseDTOList(municipios);
    }

}
