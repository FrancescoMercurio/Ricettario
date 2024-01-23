package it.ricette.dao;

import org.springframework.data.repository.CrudRepository;

import it.ricette.model.Ricetta;

import java.util.List;

public interface RicettaRepository extends CrudRepository<Ricetta, Integer> {
    List<Ricetta> findByTitoloContaining(String titolo);
}