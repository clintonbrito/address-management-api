package com.clintonbrito.squadraproject.pessoa.repository;

import com.clintonbrito.squadraproject.pessoa.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {
    boolean existsByLogin(String login);

    Pessoa findByLogin(String login);

    Pessoa findByCodigoPessoa(Long codigoPessoa);
}
