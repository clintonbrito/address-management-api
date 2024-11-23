package com.clintonbrito.squadraproject.municipio.mapper;

import com.clintonbrito.squadraproject.municipio.dto.AtualizarMunicipioDTO;
import com.clintonbrito.squadraproject.municipio.dto.CadastroMunicipioDTO;
import com.clintonbrito.squadraproject.municipio.model.Municipio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MunicipioMapper {

    @Mapping(target = "codigoMunicipio", ignore = true)
    Municipio toEntity(CadastroMunicipioDTO dto);

    Municipio toEntity(AtualizarMunicipioDTO dto);

}
