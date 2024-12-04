package com.clintonbrito.squadraproject.municipio.repository;

import com.clintonbrito.squadraproject.municipio.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    Optional<Municipio> findByNome(String nome);

    List<Municipio> findByStatus(Integer status);

    Municipio findByCodigoMunicipio(Long codigoMunicipio);

    List<Municipio> findByUf_CodigoUF(Long ufCodigoUf);

    boolean existsByCodigoMunicipio(Long codigoMunicipio);
}
