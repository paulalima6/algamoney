package br.com.pcon.algamoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pcon.algamoney.model.Lancamento;
import br.com.pcon.algamoney.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
}
