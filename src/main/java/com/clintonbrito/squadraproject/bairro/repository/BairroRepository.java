package com.clintonbrito.squadraproject.bairro.repository;

import com.clintonbrito.squadraproject.bairro.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BairroRepository extends JpaRepository<Bairro, Long>, JpaSpecificationExecutor<Bairro> {
    Optional<Bairro> findByNome(String nome);

}
