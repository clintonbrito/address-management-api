package com.clintonbrito.squadraproject.endereco.repository;

import com.clintonbrito.squadraproject.endereco.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
