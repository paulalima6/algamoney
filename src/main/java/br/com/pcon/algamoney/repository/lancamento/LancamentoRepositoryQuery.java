package br.com.pcon.algamoney.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.pcon.algamoney.model.Lancamento;
import br.com.pcon.algamoney.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable);
}
