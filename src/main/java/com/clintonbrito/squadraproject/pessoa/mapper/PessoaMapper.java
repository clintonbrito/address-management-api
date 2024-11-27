package com.clintonbrito.squadraproject.pessoa.mapper;

import com.clintonbrito.squadraproject.bairro.mapper.BairroMapper;
import com.clintonbrito.squadraproject.bairro.model.Bairro;
import com.clintonbrito.squadraproject.bairro.repository.BairroRepository;
import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import com.clintonbrito.squadraproject.endereco.model.Endereco;
import com.clintonbrito.squadraproject.pessoa.dto.AtualizarPessoaDTO;
import com.clintonbrito.squadraproject.pessoa.dto.CadastroPessoaDTO;
import com.clintonbrito.squadraproject.pessoa.dto.RespostaPessoaDTO;
import com.clintonbrito.squadraproject.pessoa.model.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = BairroMapper.class)
public abstract class PessoaMapper {

    @Autowired
    BairroRepository bairroRepository;

//    @Mapping(target = "enderecos", expression = "java( bairroRepository.findById(dto.codigoBairro()).orElse(null) )")
    public Pessoa toEntity(CadastroPessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.nome());
        pessoa.setSobrenome(dto.sobrenome());
        pessoa.setIdade(dto.idade());
        pessoa.setLogin(dto.login());
        pessoa.setSenha(dto.senha());
        pessoa.setStatus(dto.status());

        List<Endereco> enderecos = dto.enderecos().stream().map(enderecoDTO -> {
            Endereco endereco = new Endereco();
            endereco.setNomeRua(enderecoDTO.nomeRua());
            endereco.setNumero(enderecoDTO.numero());
            endereco.setComplemento(enderecoDTO.complemento());
            endereco.setCep(enderecoDTO.cep());

            Bairro bairro = bairroRepository.findById(enderecoDTO.codigoBairro())
                    .orElseThrow(() -> new RegistroNaoEncontradoException("Código do bairro informado ('" + enderecoDTO.codigoBairro() + "') é inválido."));
            endereco.setBairro(bairro);
            return endereco;
            }).collect(Collectors.toList());

        pessoa.setEnderecos(enderecos);
        return pessoa;
    }

    @Mapping(target = "enderecos", expression = "java( new ArrayList<>() )")
    public abstract RespostaPessoaDTO toResponseDTO(Pessoa pessoa);

    public abstract List<RespostaPessoaDTO> toResponseDTOList(List<Pessoa> pessoas);

//    @Mapping(target = "bairro", expression = "java( bairroRepository.findById(dto.codigoBairro()).orElse(null) )")
//    public abstract Pessoa toEntity(AtualizarPessoaDTO dto);

}
