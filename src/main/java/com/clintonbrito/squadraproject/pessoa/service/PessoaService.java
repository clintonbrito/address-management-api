package com.clintonbrito.squadraproject.pessoa.service;

import com.clintonbrito.squadraproject.bairro.repository.BairroRepository;
import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import com.clintonbrito.squadraproject.endereco.model.Endereco;
import com.clintonbrito.squadraproject.endereco.repository.EnderecoRepository;
import com.clintonbrito.squadraproject.pessoa.dto.RespostaDetalhadaPessoaDTO;
import com.clintonbrito.squadraproject.pessoa.dto.RespostaPessoaDTO;
import com.clintonbrito.squadraproject.pessoa.mapper.PessoaMapper;
import com.clintonbrito.squadraproject.pessoa.model.Pessoa;
import com.clintonbrito.squadraproject.pessoa.repository.PessoaRepository;
import com.clintonbrito.squadraproject.pessoa.validator.PessoaValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

        pessoa.getEnderecos().forEach(endereco -> {
            endereco.setCodigoPessoa(pessoaSalva.getCodigoPessoa());
            endereco.setPessoa(pessoaSalva);
            enderecoRepository.save(endereco);
        });

        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoaMapper.toResponseDTOList(pessoas);
    }

    public List<RespostaPessoaDTO> pesquisarPorStatus(Integer status) {
        List<Pessoa> pessoas = pessoaRepository.findByStatus(status);
        return pessoaMapper.toResponseDTOList(pessoas);
    }

    public List<RespostaPessoaDTO> pesquisarPorLogin(String login) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findByLogin(login);
        List<Pessoa> pessoaList = converterOptionalParaLista(optionalPessoa);
        return pessoaMapper.toResponseDTOList(pessoaList);
    }

    public RespostaDetalhadaPessoaDTO obterPessoa(Long codigoPessoa) {
        Pessoa pessoa = pessoaRepository.findByCodigoPessoa(codigoPessoa);
        return pessoaMapper.toDetailedResponseDTO(pessoa);
    }

    public List<RespostaPessoaDTO> listarPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoaMapper.toResponseDTOList(pessoas);
    }

    @Transactional
    public List<RespostaPessoaDTO> atualizar(Pessoa pessoa) {
        // 1. Buscar a pessoa no banco
        Pessoa pessoaAtual = pessoaRepository.findById(pessoa.getCodigoPessoa())
                .orElseThrow(() ->
                        new RegistroNaoEncontradoException("Pessoa não encontrada. Favor informar um 'codigoPessoa' válido."));

        pessoaValidator.validarAtualizacaoPessoa(pessoa);

        // 2. Mapear endereços enviados na requisição
        Map<Long, Endereco> enderecosNoBanco = enderecoRepository.findByPessoaCodigoPessoa(pessoa.getCodigoPessoa())
                .stream()
                .collect(Collectors
                        .toMap(Endereco::getCodigoEndereco, endereco -> endereco));

        List<Endereco> enderecosAtualizados = new ArrayList<>();

        for(Endereco enderecoRequest : pessoa.getEnderecos()) {
            if(enderecoRequest.getCodigoEndereco() != null) {
                // 3. Validar e atualizar endereço existente
                Endereco endereco = pessoaValidator.validarEndereco(enderecoRequest.getCodigoEndereco(), pessoaAtual);

                endereco.setNomeRua(enderecoRequest.getNomeRua());
                endereco.setNumero(enderecoRequest.getNumero());
                endereco.setComplemento(enderecoRequest.getComplemento());
                endereco.setCep(enderecoRequest.getCep());
                endereco.setBairro(bairroRepository.findById(enderecoRequest.getBairro().getCodigoBairro())
                        .orElseThrow(() ->
                                new RegistroNaoEncontradoException("O bairro informado não consta no banco de dados.")));

                enderecosAtualizados.add(endereco);
                enderecosNoBanco.remove(enderecoRequest.getCodigoEndereco());
            } else {
                // 4. Criar novo endereço
                Endereco enderecoNovo = new Endereco();

                enderecoNovo.setNomeRua(enderecoRequest.getNomeRua());
                enderecoNovo.setNumero(enderecoRequest.getNumero());
                enderecoNovo.setComplemento(enderecoRequest.getComplemento());
                enderecoNovo.setCep(enderecoRequest.getCep());
                enderecoNovo.setBairro(bairroRepository.findById(enderecoRequest.getBairro().getCodigoBairro())
                        .orElseThrow(() -> new RegistroNaoEncontradoException("O bairro informado não consta no banco de dados.")));

                enderecoNovo.setPessoa(pessoa);
                enderecosAtualizados.add(enderecoNovo);
            }
        }

        // 5. Remover endereços que não estão mais no body da requisição
        enderecoRepository.deleteAll(enderecosNoBanco.values());

        // 6. Atualizar a lista de endereços da pessoa e salvar
        pessoa.getEnderecos().clear();
        pessoa.getEnderecos().addAll(enderecosAtualizados);
        pessoa.setNome(pessoa.getNome());
        pessoa.setSobrenome(pessoa.getSobrenome());
        pessoa.setLogin(pessoa.getLogin());
        pessoa.setSenha(pessoa.getSenha());
        pessoa.setStatus(pessoa.getStatus());
        pessoa.setEnderecos(pessoa.getEnderecos());
        pessoaRepository.save(pessoa);

        // 7. Retornar a lista de pessoas atualizada
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoaMapper.toResponseDTOList(pessoas);

    }

    private List<Pessoa> converterOptionalParaLista(Optional<Pessoa> optionalPessoa) {
        return optionalPessoa.map(List::of).orElseGet(List::of);
    }

}
