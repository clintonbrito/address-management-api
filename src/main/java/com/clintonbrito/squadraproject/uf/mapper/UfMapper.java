package com.clintonbrito.squadraproject.uf.mapper;

import com.clintonbrito.squadraproject.uf.dto.AtualizarUfDTO;
import com.clintonbrito.squadraproject.uf.dto.CadastroUfDTO;
import com.clintonbrito.squadraproject.uf.model.Uf;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UfMapper {

    @Mapping(target = "codigoUf", ignore = true)
    Uf toEntity(CadastroUfDTO dto);

    Uf toEntity(AtualizarUfDTO dto);

}
