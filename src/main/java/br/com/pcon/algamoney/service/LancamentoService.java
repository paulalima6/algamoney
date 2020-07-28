package br.com.pcon.algamoney.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcon.algamoney.model.Lancamento;
import br.com.pcon.algamoney.model.Pessoa;
import br.com.pcon.algamoney.repository.LancamentoRepository;
import br.com.pcon.algamoney.repository.PessoaRepository;
import br.com.pcon.algamoney.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public Lancamento save(Lancamento entity) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(entity.getPessoa().getCodigo());
		Pessoa newPessoa = null;
		
		if(pessoa.isPresent()) {
			newPessoa = (Pessoa) pessoa.get();
			if(newPessoa.isInativo()) {
				throw new PessoaInexistenteOuInativaException();
			}
		}
		return lancamentoRepository.save(entity);
	}
}
