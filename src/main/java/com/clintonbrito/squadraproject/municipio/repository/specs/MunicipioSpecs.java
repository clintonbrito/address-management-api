package com.clintonbrito.squadraproject.municipio.repository.specs;

import com.clintonbrito.squadraproject.municipio.model.Municipio;
import org.springframework.data.jpa.domain.Specification;

public class MunicipioSpecs {
    public static Specification<Municipio> codigoMunicipioEqual(Long codigoMunicipio) {
        return (root, query, cb) ->
                cb.equal(root.get("codigoMunicipio"), codigoMunicipio);
    }

    public static Specification<Municipio> codigoUFEqual(Long codigoUF) {
        return (root, query, cb) ->
                cb.equal(root.join("uf").get("codigoUF"), codigoUF);
//                cb.equal(root.get("codigoUF"), codigoUF);
    }

    // % no início e no final da string de busca para buscar em qualquer parte do nome
    // cb.like( cb.upper(root.get("nome")), "%" + nome.toUpperCase() + "%")
    public static Specification<Municipio> nomeLike(String nome) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("nome")), nome.toUpperCase());
    }

    public static Specification<Municipio> statusEqual(Integer status) {
        return (root, query, cb) ->
                cb.equal(root.get("status"), status);
    }

}
