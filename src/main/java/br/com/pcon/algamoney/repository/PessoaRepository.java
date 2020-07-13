package br.com.pcon.algamoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pcon.algamoney.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
