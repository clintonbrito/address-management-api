package com.clintonbrito.squadraproject.uf.repository;

import com.clintonbrito.squadraproject.uf.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UfRepository extends JpaRepository<Uf, Long>, JpaSpecificationExecutor<Uf> {
    Optional<Uf> findByNome(String nome);

    Optional<Uf> findBySigla(String sigla);

//    List<Uf> findByStatus(Integer status);

//    Uf findByCodigoUFOrSiglaOrNome(Long codigoUF, String sigla, String nome);

    boolean existsByCodigoUF(Long codigoUF);

//    Optional<Uf> findByCodigoUF(Long codigoUF);

//    Optional<Uf> findByCodigoUFAndSiglaAndNomeAndStatus(Long codigoUF, String sigla, String nome, Integer status);
}
