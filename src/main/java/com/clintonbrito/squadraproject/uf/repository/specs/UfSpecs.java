package com.clintonbrito.squadraproject.uf.repository.specs;

import com.clintonbrito.squadraproject.uf.model.Uf;
import org.springframework.data.jpa.domain.Specification;

public class UfSpecs {
    public static Specification<Uf> codigoUFEqual(Long codigoUF) {
        return (root, query, cb) ->
                cb.equal(root.get("codigoUF"), codigoUF);
    }

    public static Specification<Uf> siglaEqual(String sigla) {
        return (root, query, cb) ->
                cb.equal(root.get("sigla"), sigla);
    }

    public static Specification<Uf> nomeLike(String nome) {
        return (root, query, cb) ->
                // % no início e no final da string de busca para buscar em qualquer parte do nome
                // cb.like( cb.upper(root.get("nome")), "%" + nome.toUpperCase() + "%")
                cb.like( cb.upper(root.get("nome")), nome.toUpperCase());
    }

    public static Specification<Uf> statusEqual(Integer status) {
        return (root, query, cb) ->
                cb.equal(root.get("status"), status);
    }
}
