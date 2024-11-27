package com.clintonbrito.squadraproject.pessoa.service;

import com.clintonbrito.squadraproject.bairro.model.Bairro;
import com.clintonbrito.squadraproject.bairro.repository.BairroRepository;
import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import com.clintonbrito.squadraproject.endereco.repository.EnderecoRepository;
import com.clintonbrito.squadraproject.pessoa.dto.RespostaPessoaDTO;
import com.clintonbrito.squadraproject.pessoa.mapper.PessoaMapper;
import com.clintonbrito.squadraproject.pessoa.model.Pessoa;
import com.clintonbrito.squadraproject.pessoa.repository.PessoaRepository;
import com.clintonbrito.squadraproject.pessoa.validator.PessoaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaValidator pessoaValidator;
    private final PessoaMapper pessoaMapper;
    private final EnderecoRepository enderecoRepository;
    private final BairroRepository bairroRepository;

    public List<RespostaPessoaDTO> salvar(Pessoa pessoa) {

        pessoaValidator.validarCadastroPessoa(pessoa);
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

//        pessoa.getEnderecos().forEach(endereco -> {
//            Bairro bairro = bairroRepository.findById(endereco.getCodigoBairro())
//                    .orElseThrow(() -> new RegistroNaoEncontradoException("Código do bairro inválido."));
//            endereco.setBairro(bairro); // Associa o bairro ao endereço.
//        });

        pessoa.getEnderecos().forEach(endereco -> {
//            if(endereco.getCodigoPessoa() == null) {
            endereco.setCodigoPessoa(pessoaSalva.getCodigoPessoa());
            endereco.setPessoa(pessoaSalva);
//            }
            enderecoRepository.save(endereco);
        });

        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoaMapper.toResponseDTOList(pessoas);
    }

//    public List<RespostaPessoaDTO> pesquisarPorStatus(Integer status) {
//        List<Pessoa> pessoas = pessoaRepository.findByStatus(status);
//        return pessoaMapper.toResponseDTOList(pessoas);
//    }
//
//    public List<RespostaPessoaDTO> pesquisarPorCodigoMunicipio(Long codigoMunicipio) {
//        List<Pessoa> pessoas = pessoaRepository.findByMunicipio_CodigoMunicipio(codigoMunicipio);
//        return pessoaMapper.toResponseDTOList(pessoas);
//    }
//
//    public List<RespostaPessoaDTO> pesquisarPorNome(String nome) {
//        Optional<Pessoa> optionalBairro = pessoaRepository.findByNome(nome);
//        List<Pessoa> pessoas = convertOptionalToList(optionalBairro);
//        return pessoaMapper.toResponseDTOList(pessoas);
//    }
//
//    public RespostaPessoaDTO obterBairro(Long codigoBairro) {
//        Pessoa pessoa = pessoaRepository.findByCodigoBairro(codigoBairro);
//        return pessoaMapper.toResponseDTO(pessoa);
//    }
//
//    public List<RespostaPessoaDTO> listarBairros() {
//        List<Pessoa> pessoas = pessoaRepository.findAll();
//        return pessoaMapper.toResponseDTOList(pessoas);
//    }
//
//    public List<RespostaPessoaDTO> atualizar(Pessoa pessoa) {
//        pessoaRepository.findById(pessoa.getCodigoBairro())
//                .orElseThrow(() -> new RegistroNaoEncontradoException("Bairro não encontrado. Favor informar um 'codigoBairro' válido."));
//
//        pessoaValidator.validar(pessoa);
//        pessoaRepository.save(pessoa);
//        List<Pessoa> pessoas = pessoaRepository.findAll();
//        return pessoaMapper.toResponseDTOList(pessoas);
//    }

//    private List<Pessoa> convertOptionalToList(Optional<Pessoa> optionalBairro) {
//        return optionalBairro.map(List::of).orElseGet(List::of);
//    }

}
