package br.com.pcon.algamoney.resource;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pcon.algamoney.event.ResourceEvent;
import br.com.pcon.algamoney.exceptionhandler.Erro;
import br.com.pcon.algamoney.model.Lancamento;
import br.com.pcon.algamoney.repository.LancamentoRepository;
import br.com.pcon.algamoney.repository.filter.LancamentoFilter;
import br.com.pcon.algamoney.repository.projection.ResumoLancamento;
import br.com.pcon.algamoney.service.LancamentoService;
import br.com.pcon.algamoney.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	ApplicationEventPublisher publisher;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public Page<Lancamento> pesquisar(LancamentoFilter filter, Pageable pageable) {
		return lancamentoRepository.filtrar(filter, pageable);
	}
	
	@GetMapping(params="resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	public Page<ResumoLancamento> resumir(LancamentoFilter filter, Pageable pageable) {
		return lancamentoRepository.resumir(filter, pageable);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscar(@PathVariable Long codigo, HttpServletResponse response) {
		Optional<Lancamento> lancamento = lancamentoRepository.findById(codigo);
		Lancamento retorno = new Lancamento();

		if (lancamento.isPresent()) {
			retorno = (Lancamento) lancamento.get();
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(lancamento)
				.toUri();
		response.setHeader("Location", uri.toASCIIString());

		return lancamento.isPresent() ? ResponseEntity.ok(retorno) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> inserirLancament(@Valid @RequestBody Lancamento entity, HttpServletResponse response) {
		Lancamento lancamento = lancamentoService.save(entity);

		publisher.publishEvent(new ResourceEvent(this, response, lancamento.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		lancamentoRepository.deleteById(codigo);
	}
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(
			PessoaInexistenteOuInativaException ex) {
		
		String mensagemUsuario = messageSource.getMessage("pessoa.inativa.inexistente", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario,mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
}
