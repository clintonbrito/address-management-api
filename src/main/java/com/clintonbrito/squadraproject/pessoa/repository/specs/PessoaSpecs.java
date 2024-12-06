package com.clintonbrito.squadraproject.pessoa.repository.specs;

import com.clintonbrito.squadraproject.pessoa.model.Pessoa;
import org.springframework.data.jpa.domain.Specification;

public class PessoaSpecs {

    public static Specification<Pessoa> codigoPessoaEqual(Long codigoPessoa) {
        return (root, query, cb)
                -> cb.equal(root.get("codigoPessoa"), codigoPessoa);
    }

    public static Specification<Pessoa> loginEqual(String login) {
        return (root, query, cb) ->
                cb.equal(root.get("login"), login);
    }

    public static Specification<Pessoa> statusEqual(Integer status) {
        return (root, query, cb) ->
                cb.equal(root.get("status"), status);
    }

}
