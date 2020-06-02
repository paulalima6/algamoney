package br.com.pcon.algamoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pcon.algamoney.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
}
