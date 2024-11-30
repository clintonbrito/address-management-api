package com.clintonbrito.squadraproject.endereco.mapper;

import com.clintonbrito.squadraproject.bairro.mapper.BairroMapper;
import com.clintonbrito.squadraproject.bairro.model.Bairro;
import com.clintonbrito.squadraproject.bairro.repository.BairroRepository;
import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import com.clintonbrito.squadraproject.endereco.dto.AtualizarEnderecoDTO;
import com.clintonbrito.squadraproject.endereco.model.Endereco;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = BairroMapper.class)
public abstract class EnderecoMapper {

    @Autowired
    BairroRepository bairroRepository;

    public Endereco toEntity(AtualizarEnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setCodigoEndereco(enderecoDTO.codigoEndereco());
        endereco.setNomeRua(enderecoDTO.nomeRua());
        endereco.setNumero(enderecoDTO.numero());
        endereco.setComplemento(enderecoDTO.complemento());
        endereco.setCep(enderecoDTO.cep());

        Bairro bairro = bairroRepository.findById(enderecoDTO.codigoBairro())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Código do bairro informado ('" + enderecoDTO.codigoBairro() + "') é inválido."));
        endereco.setBairro(bairro);

        return endereco;
    }
}
