package com.clintonbrito.squadraproject.uf.repository;

import com.clintonbrito.squadraproject.uf.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UfRepository extends JpaRepository<Uf, Long> {
    Optional<Uf> findByNome(String nome);

    Optional<Uf> findBySigla(String sigla);

    List<Uf> findByStatus(Integer status);

    Uf findByCodigoUfOrSiglaOrNome(Long codigoUf, String sigla, String nome);

    boolean existsByCodigoUf(Long codigoUf);
}
