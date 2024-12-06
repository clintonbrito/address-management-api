package com.clintonbrito.squadraproject.bairro.service;

import com.clintonbrito.squadraproject.bairro.dto.RespostaBairroDTO;
import com.clintonbrito.squadraproject.bairro.mapper.BairroMapper;
import com.clintonbrito.squadraproject.bairro.model.Bairro;
import com.clintonbrito.squadraproject.bairro.repository.BairroRepository;
import com.clintonbrito.squadraproject.bairro.repository.specs.BairroSpecs;
import com.clintonbrito.squadraproject.bairro.validator.BairroValidator;
import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BairroService {

    private final BairroRepository bairroRepository;
    private final BairroValidator bairroValidator;
    private final BairroMapper bairroMapper;

    public List<RespostaBairroDTO> salvar(Bairro bairro) {
        bairroValidator.validar(bairro);
        bairroRepository.save(bairro);
        List<Bairro> bairros = bairroRepository.findAll();
        return bairroMapper.toResponseDTOList(bairros);
    }

    public Object pesquisaPorFiltros(Long codigoBairro, Long codigoMunicipio, String nome, Integer status) {

        Specification<Bairro> specs = Specification
                .where((root, query, cb) -> cb.conjunction());

        if(codigoBairro != null) {
            specs = specs.and(BairroSpecs.codigoBairroEqual(codigoBairro));
        }

        if(codigoMunicipio != null) {
            specs = specs.and(BairroSpecs.codigoMunicipioEqual(codigoMunicipio));
        }

        if(nome != null) {
            specs = specs.and(BairroSpecs.nomeLike(nome));
        }

        if(status != null) {
            specs = specs.and(BairroSpecs.statusEqual(status));
        }

        List<Bairro> rawResult = bairroRepository.findAll(specs);
        List<RespostaBairroDTO> result = bairroMapper.toResponseDTOList(rawResult);

        if(result.isEmpty()) {
            return new ArrayList<>();
        }

        if(codigoBairro != null) {
            return result.getFirst();
        }

        return result;
    }

    public List<RespostaBairroDTO> atualizar(Bairro bairro) {
        bairroRepository.findById(bairro.getCodigoBairro())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Bairro não encontrado. Favor informar um 'codigoBairro' válido."));

        bairroValidator.validar(bairro);
        bairroRepository.save(bairro);
        List<Bairro> bairros = bairroRepository.findAll();
        return bairroMapper.toResponseDTOList(bairros);
    }

}
