package com.clintonbrito.squadraproject.municipio.repository;

import com.clintonbrito.squadraproject.municipio.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface MunicipioRepository extends JpaRepository<Municipio, Long>, JpaSpecificationExecutor<Municipio> {
    Optional<Municipio> findByNome(String nome);

//    List<Municipio> findByUf_CodigoUF(Long ufCodigoUF);

    boolean existsByCodigoMunicipio(Long codigoMunicipio);

}
