package it.ricette.repository;

import org.springframework.data.repository.CrudRepository;

import it.ricette.model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {

}