package com.clintonbrito.squadraproject.bairro.service;

import com.clintonbrito.squadraproject.bairro.dto.RespostaBairroDTO;
import com.clintonbrito.squadraproject.bairro.mapper.BairroMapper;
import com.clintonbrito.squadraproject.bairro.model.Bairro;
import com.clintonbrito.squadraproject.bairro.repository.BairroRepository;
import com.clintonbrito.squadraproject.bairro.validator.BairroValidator;
import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

//    public List<RespostaBairroDTO> pesquisarPorStatus(Integer status) {
//        List<Bairro> bairros = bairroRepository.findByStatus(status);
//        return bairroMapper.toResponseDTOList(bairros);
//    }
//
//    public List<RespostaBairroDTO> pesquisarPorCodigoUf(Long codigoUf) {
//        List<Bairro> bairros = bairroRepository.findByUf_CodigoUf(codigoUf);
//        return bairroMapper.toResponseDTOList(bairros);
//    }
//
//    public List<RespostaBairroDTO> pesquisarPorNome(String nome) {
//        Optional<Bairro> optionalMunicipio = bairroRepository.findByNome(nome);
//        List<Bairro> bairros = convertOptionalToList(optionalMunicipio);
//        return bairroMapper.toResponseDTOList(bairros);
//    }
//
//    public RespostaBairroDTO obterMunicipio(Long codigoMunicipio) {
//        Bairro bairro = bairroRepository.findByCodigoMunicipio(codigoMunicipio);
//        return bairroMapper.toResponseDTO(bairro);
//    }
//
//    public List<RespostaBairroDTO> listarMunicipios() {
//        List<Bairro> bairros = bairroRepository.findAll();
//        return bairroMapper.toResponseDTOList(bairros);
//    }
//
//    public List<RespostaBairroDTO> atualizar(Bairro bairro) {
//        bairroRepository.findById(bairro.getCodigoMunicipio())
//                .orElseThrow(() -> new RegistroNaoEncontradoException("Município não encontrado. Favor informar um 'codigoMunicipio' válido."));
//
//        bairroValidator.validar(bairro);
//        bairroRepository.save(bairro);
//        List<Bairro> bairros = bairroRepository.findAll();
//        return bairroMapper.toResponseDTOList(bairros);
//    }

//    private List<Bairro> convertOptionalToList(Optional<Bairro> optionalMunicipio) {
//        return optionalMunicipio.map(List::of).orElseGet(List::of);
//    }

}
