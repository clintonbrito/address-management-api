package com.clintonbrito.squadraproject.bairro.mapper;

import com.clintonbrito.squadraproject.bairro.dto.CadastroBairroDTO;
import com.clintonbrito.squadraproject.bairro.dto.RespostaBairroDTO;
import com.clintonbrito.squadraproject.bairro.model.Bairro;
import com.clintonbrito.squadraproject.municipio.mapper.MunicipioMapper;
import com.clintonbrito.squadraproject.municipio.repository.MunicipioRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = MunicipioMapper.class)
public abstract class BairroMapper {

    @Autowired
    MunicipioRepository municipioRepository;

    @Mapping(target = "municipio", expression = "java( municipioRepository.findById(dto.codigoMunicipio()).orElse(null) )")
    public abstract Bairro toEntity(CadastroBairroDTO dto);

    @Mapping(target = "codigoMunicipio", source = "municipio.codigoMunicipio")
    public abstract RespostaBairroDTO toResponseDTO(Bairro bairro);

    public abstract List<RespostaBairroDTO> toResponseDTOList(List<Bairro> bairros);

//    @Mapping(target = "uf", expression = "java( ufRepository.findById(dto.codigoMunicipio()).orElse(null) )")
//    public abstract Bairro toEntity(AtualizarBairroDTO dto);

}
