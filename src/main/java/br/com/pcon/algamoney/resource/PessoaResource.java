package br.com.pcon.algamoney.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pcon.algamoney.event.ResourceEvent;
import br.com.pcon.algamoney.model.Pessoa;
import br.com.pcon.algamoney.repository.PessoaRepository;
import br.com.pcon.algamoney.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return pessoas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pessoas);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
	public ResponseEntity<Optional<Pessoa>> buscarPeloId(@PathVariable Long codigo, HttpServletResponse response) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{/codigo}").buildAndExpand(pessoa).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return pessoa.isPresent() ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa entity, HttpServletResponse response) {
		Pessoa pessoa = pessoaRepository.save(entity);
		
		publisher.publishEvent(new ResourceEvent(this, response, pessoa.getCodigo()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
	}
	
//	@DeleteMapping("/{codigo}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
//	public void remover(@PathVariable Long codigo) {
//		pessoaRepository.deleteById(codigo);
//	}
	
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
	public ResponseEntity<List<Pessoa>> remover(@PathVariable Long codigo) {
		pessoaRepository.deleteById(codigo);
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return pessoas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pessoas);
	}

	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_PESSOA') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
	}
}
