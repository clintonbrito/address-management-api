package com.clintonbrito.squadraproject.pessoa.repository;

import com.clintonbrito.squadraproject.pessoa.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByLogin(String login);

    List<Pessoa> findByStatus(Integer status);

    Optional<Pessoa> findByLogin(String login);

    Pessoa findByCodigoPessoa(Long codigoPessoa);
}
