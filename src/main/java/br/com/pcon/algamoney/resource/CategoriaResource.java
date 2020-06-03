package br.com.pcon.algamoney.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pcon.algamoney.model.Categoria;
import br.com.pcon.algamoney.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscar(@PathVariable Long codigo, HttpServletResponse response) {
		Optional<Categoria> categoria = categoriaRepository.findById(codigo);
		Categoria retorno = new Categoria();
		
		if(categoria.isPresent()) {
			retorno = (Categoria) categoria.get();
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(categoria).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return categoria.isPresent() ? ResponseEntity.ok(retorno) : ResponseEntity.notFound().build();
	}

	/*
	 * @GetMapping 
	 public ResponseEntity<?> listar() { 
	 	List<Categoria> categorias = categoriaRepository.findAll(); 
	 	return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : 
	 		ResponseEntity.noContent().build(); 
	 }
	 */

	@PostMapping
	public ResponseEntity<Categoria> criar(@RequestBody Categoria entity, HttpServletResponse response) {
		Categoria categoria = categoriaRepository.save(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(categoria.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(categoria);
	}

}
