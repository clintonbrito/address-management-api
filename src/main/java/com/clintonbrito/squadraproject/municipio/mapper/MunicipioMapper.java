package com.clintonbrito.squadraproject.municipio.mapper;

import com.clintonbrito.squadraproject.municipio.dto.AtualizarMunicipioDTO;
import com.clintonbrito.squadraproject.municipio.dto.CadastroMunicipioDTO;
import com.clintonbrito.squadraproject.municipio.dto.RespostaMunicipioDTO;
import com.clintonbrito.squadraproject.municipio.model.Municipio;
import com.clintonbrito.squadraproject.uf.mapper.UfMapper;
import com.clintonbrito.squadraproject.uf.repository.UfRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = UfMapper.class)
public abstract class MunicipioMapper {

    @Autowired
    UfRepository ufRepository;

    @Mapping(target = "uf", expression = "java( ufRepository.findById(dto.codigoUF()).orElse(null) )")
    public abstract Municipio toEntity(CadastroMunicipioDTO dto);

    @Mapping(target = "codigoUF", source = "uf.codigoUF")
    public abstract RespostaMunicipioDTO toResponseDTO(Municipio municipio);

    public abstract List<RespostaMunicipioDTO> toResponseDTOList(List<Municipio> municipios);

    @Mapping(target = "uf", expression = "java( ufRepository.findById(dto.codigoUF()).orElse(null) )")
    public abstract Municipio toEntity(AtualizarMunicipioDTO dto);

}
