package com.clintonbrito.squadraproject.municipio.service;

import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import com.clintonbrito.squadraproject.municipio.dto.RespostaMunicipioDTO;
import com.clintonbrito.squadraproject.municipio.mapper.MunicipioMapper;
import com.clintonbrito.squadraproject.municipio.model.Municipio;
import com.clintonbrito.squadraproject.municipio.repository.MunicipioRepository;
import com.clintonbrito.squadraproject.municipio.validator.MunicipioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<RespostaMunicipioDTO> pesquisarPorStatus(Integer status) {
        List<Municipio> municipios = municipioRepository.findByStatus(status);
        return municipioMapper.toResponseDTOList(municipios);
    }

    public List<RespostaMunicipioDTO> pesquisarPorCodigoUf(Long codigoUf) {
        List<Municipio> municipios = municipioRepository.findByUf_CodigoUf(codigoUf);
        return municipioMapper.toResponseDTOList(municipios);
    }

    public List<RespostaMunicipioDTO> pesquisarPorNome(String nome) {
        Optional<Municipio> optionalMunicipio = municipioRepository.findByNome(nome);
        List<Municipio> municipios = convertOptionalToList(optionalMunicipio);
        return municipioMapper.toResponseDTOList(municipios);
    }

    public RespostaMunicipioDTO obterMunicipio(Long codigoMunicipio) {
        Municipio municipio = municipioRepository.findByCodigoMunicipio(codigoMunicipio);
        return municipioMapper.toResponseDTO(municipio);
    }

    public List<RespostaMunicipioDTO> listarMunicipios() {
        List<Municipio> municipios = municipioRepository.findAll();
        return municipioMapper.toResponseDTOList(municipios);
    }

    public List<RespostaMunicipioDTO> atualizar(Municipio municipio) {
        municipioRepository.findById(municipio.getCodigoMunicipio())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Município não encontrado. Favor informar um 'codigoMunicipio' válido."));

        municipioValidator.validar(municipio);
        municipioRepository.save(municipio);
        List<Municipio> municipios = municipioRepository.findAll();
        return municipioMapper.toResponseDTOList(municipios);
    }

    private List<Municipio> convertOptionalToList(Optional<Municipio> optionalMunicipio) {
        return optionalMunicipio.map(List::of).orElseGet(List::of);
    }

}
