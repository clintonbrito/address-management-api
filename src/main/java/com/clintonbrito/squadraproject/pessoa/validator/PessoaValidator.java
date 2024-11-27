package com.clintonbrito.squadraproject.pessoa.validator;

import com.clintonbrito.squadraproject.bairro.model.Bairro;
import com.clintonbrito.squadraproject.bairro.repository.BairroRepository;
import com.clintonbrito.squadraproject.common.exception.RegistroDuplicadoException;
import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import com.clintonbrito.squadraproject.pessoa.model.Pessoa;
import com.clintonbrito.squadraproject.pessoa.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PessoaValidator {

    private final PessoaRepository pessoaRepository;
    private final BairroRepository bairroRepository;

    public void validarCadastroPessoa(Pessoa pessoa) {
        if (pessoa.getEnderecos() == null) {
            throw new RegistroNaoEncontradoException("Nenhum Endereço foi incluso. Favor informar pelo menos um Endereço válido.");
        }

        validarLoginUnico(pessoa);
        validarEnderecos(pessoa);
    }

    private void validarLoginUnico(Pessoa pessoa) {
        if(pessoaRepository.existsByLogin(pessoa.getLogin())) {
            throw new RegistroDuplicadoException("Login já cadastrado. Favor informar um novo 'login'.");
        }
    }

    private void validarEnderecos(Pessoa pessoa) {
        if (pessoa.getEnderecos() == null || pessoa.getEnderecos().isEmpty()) {
            throw new RegistroNaoEncontradoException("Você deve cadastrar pelo menos um endereço.");
        }

//        pessoa.getEnderecos().forEach(endereco -> {
//            Bairro bairro = endereco.getBairro();
//            if (!bairroRepository.existsById(bairro.getCodigoBairro())) {
//                throw new RegistroNaoEncontradoException("O código do bairro é inválido.");
//            }
//        });
    }

//    private void validarLoginDuplicado(Pessoa pessoa) {
//        boolean duplicado = pessoaRepository.findByLogin(pessoa.getLogin())
//                .filter(p -> !p.getLogin().equals(pessoa.getLogin()))
//                .isPresent();
//
//        if(duplicado) {
//            throw new RegistroDuplicadoException("Login já cadastrado. Favor informar um novo 'login'.");
//        }
//    }

}
