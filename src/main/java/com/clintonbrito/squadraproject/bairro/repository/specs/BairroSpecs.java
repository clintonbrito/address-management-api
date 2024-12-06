package com.clintonbrito.squadraproject.bairro.repository.specs;

import com.clintonbrito.squadraproject.bairro.model.Bairro;
import org.springframework.data.jpa.domain.Specification;

public class BairroSpecs {

    public static Specification<Bairro> codigoBairroEqual(Long codigoBairro) {
        return (root, query, cb) ->
                cb.equal(root.get("codigoBairro"), codigoBairro);
    }

    public static Specification<Bairro> codigoMunicipioEqual(Long codigoMunicipio) {
        return (root, query, cb) ->
                cb.equal(root.join("municipio").get("codigoMunicipio"), codigoMunicipio);
    }

    public static Specification<Bairro> nomeLike(String nome) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("nome")), nome.toUpperCase());
    }

    public static Specification<Bairro> statusEqual(Integer status) {
        return (root, query, cb) ->
                cb.equal(root.get("status"), status);
    }

}
