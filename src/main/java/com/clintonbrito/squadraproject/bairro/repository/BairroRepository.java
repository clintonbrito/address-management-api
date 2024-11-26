package com.clintonbrito.squadraproject.bairro.repository;

import com.clintonbrito.squadraproject.bairro.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
    Optional<Bairro> findByNome(String nome);

    List<Bairro> findByStatus(Integer status);

    Bairro findByCodigoBairro(Long codigoBairro);

    List<Bairro> findByMunicipio_CodigoMunicipio(Long municipioCodigoMunicipio);

//    List<Bairro> findByStatus(Integer status);

//    Bairro findByCodigoMunicipio(Long codigoMunicipio);
}
