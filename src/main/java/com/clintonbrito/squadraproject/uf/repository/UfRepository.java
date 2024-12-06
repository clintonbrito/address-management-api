package com.clintonbrito.squadraproject.uf.repository;

import com.clintonbrito.squadraproject.uf.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UfRepository extends JpaRepository<Uf, Long>, JpaSpecificationExecutor<Uf> {
    Optional<Uf> findByNome(String nome);

//    Optional<Uf> findByCodigoUF(Long codigoUF);

    Optional<Uf> findBySigla(String sigla);

    boolean existsByCodigoUF(Long codigoUF);
}
