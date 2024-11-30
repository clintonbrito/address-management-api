package com.clintonbrito.squadraproject.endereco.repository;

import com.clintonbrito.squadraproject.endereco.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByPessoaCodigoPessoa(Long codigoPessoa);
}
