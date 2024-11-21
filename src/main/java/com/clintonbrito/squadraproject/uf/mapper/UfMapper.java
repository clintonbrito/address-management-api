package com.clintonbrito.squadraproject.uf.mapper;

import com.clintonbrito.squadraproject.uf.dto.CadastroUfDTO;
import com.clintonbrito.squadraproject.uf.dto.RespostaUfDTO;
import com.clintonbrito.squadraproject.uf.model.Uf;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UfMapper {

    public abstract Uf toEntity(CadastroUfDTO dto);

    public abstract RespostaUfDTO toDTO(Uf uf);
}
