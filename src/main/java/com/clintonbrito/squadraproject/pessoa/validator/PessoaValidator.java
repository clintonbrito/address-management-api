package com.clintonbrito.squadraproject.pessoa.validator;

import com.clintonbrito.squadraproject.common.exception.OperacaoNaoPermitidaException;
import com.clintonbrito.squadraproject.common.exception.RegistroDuplicadoException;
import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import com.clintonbrito.squadraproject.endereco.model.Endereco;
import com.clintonbrito.squadraproject.endereco.repository.EnderecoRepository;
import com.clintonbrito.squadraproject.pessoa.model.Pessoa;
import com.clintonbrito.squadraproject.pessoa.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PessoaValidator {

    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;

    public void validarCadastroPessoa(Pessoa pessoa) {
        if (pessoa.getEnderecos() == null) {
            throw new RegistroNaoEncontradoException("Nenhum Endereço foi incluso. Favor informar pelo menos um Endereço válido.");
        }

        validarLoginUnico(pessoa);
        validarArrayDeEnderecos(pessoa);
    }

    public void validarAtualizacaoPessoa(Pessoa pessoa) {
        Pessoa pessoaAtual = pessoaRepository.findById(pessoa.getCodigoPessoa())
                        .orElseThrow(() -> new RegistroNaoEncontradoException("Pessoa não encontrada. Favor informar um 'codigoPessoa' válido."));

        if(!pessoaAtual.getLogin().equals(pessoa.getLogin())) {
            validarLoginUnico(pessoa);
        }

        validarArrayDeEnderecos(pessoa);
    }

    public Endereco validarEndereco(Long codigoEndereco, Pessoa pessoa) {
        Endereco endereco = enderecoRepository.findById(codigoEndereco)
                .orElseThrow(() ->
                        new RegistroNaoEncontradoException("O endereço informado não consta no banco de dados."));

        if(!endereco.getPessoa().getCodigoPessoa().equals(pessoa.getCodigoPessoa())) {
            throw new OperacaoNaoPermitidaException("O endereço informado não pertence à pessoa informada.");
        }

        return endereco;
    }

    private void validarLoginUnico(Pessoa pessoa) {
        if(pessoaRepository.existsByLogin(pessoa.getLogin())) {
            throw new RegistroDuplicadoException("Login já cadastrado. Favor informar um novo 'login'.");
        }
    }

    private void validarArrayDeEnderecos(Pessoa pessoa) {
        if (pessoa.getEnderecos() == null || pessoa.getEnderecos().isEmpty()) {
            throw new RegistroNaoEncontradoException("Você deve cadastrar pelo menos um endereço.");
        }
    }

}
