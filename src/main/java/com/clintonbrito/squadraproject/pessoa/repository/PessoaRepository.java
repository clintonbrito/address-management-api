package com.clintonbrito.squadraproject.pessoa.repository;

import com.clintonbrito.squadraproject.pessoa.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByLogin(String login);
}
