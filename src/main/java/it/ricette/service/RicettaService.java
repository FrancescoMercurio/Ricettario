package it.ricette.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.ricette.dao.RicettaRepository;
import it.ricette.model.Ricetta;

import java.util.List;
import java.util.Optional;

@Service
public class RicettaService {

    @Autowired
	private RicettaRepository repo;

    public RicettaService(RicettaRepository ricettaRepository) {
        this.repo = ricettaRepository;
    }

    public Ricetta saveRicetta(Ricetta ricetta) {
        return repo.save(ricetta);
    }

    public Optional<Ricetta> getRicettaById(Integer id) {
        return repo.findById(id);
    }

    public List<Ricetta> findAllRicette() {
        return (List<Ricetta>) repo.findAll();
    }

    public List<Ricetta> findRicetteByTitolo(String titolo) {
        return repo.findByTitoloContaining(titolo);
    }

    public void deleteRicetta(Integer id) {
    	repo.deleteById(id);
    }

    public Ricetta updateRicetta(Integer id, Ricetta ricetta) {
        return repo.findById(id).map(existingRicetta -> {
            existingRicetta.setTitolo(ricetta.getTitolo());
            existingRicetta.setQuantitaPersone(ricetta.getQuantitaPersone());
            existingRicetta.setCategoria(ricetta.getCategoria());
            existingRicetta.setIngredienti(ricetta.getIngredienti());
            existingRicetta.setPreparazione(ricetta.getPreparazione());
            return repo.save(existingRicetta);
        }).orElseGet(() -> {
            ricetta.setId(id);
            return repo.save(ricetta);
        });
    }
}