package it.ricette.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.ricette.NotFoundException;
import it.ricette.dao.RicettaRepository;
import it.ricette.dto.RicettaDto;
import it.ricette.mapper.CategoriaMapper;
import it.ricette.mapper.RicettaMapper;
import it.ricette.model.Categoria;
import it.ricette.model.Ricetta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RicettaService {

    @Autowired
	private RicettaRepository repo;
    
    @Autowired
    private RicettaMapper ricettaMapper;
    
    @Autowired
    private CategoriaMapper categoriaMapper;
    
    @Autowired
    private EntityManager entityManager;

    public RicettaService(RicettaRepository ricettaRepository) {
        this.repo = ricettaRepository;
    }

    public RicettaDto saveRicetta(RicettaDto ricettaDto) {
        Ricetta ricetta = ricettaMapper.toEntity(ricettaDto);
        Ricetta savedRicetta = repo.save(ricetta);
        return ricettaMapper.toDto(savedRicetta);
    }

    public Optional<RicettaDto> getRicettaById(Integer id) {
        return repo.findById(id)
                   .map(ricettaMapper::toDto);
    }

    public Page<RicettaDto> getPaginatedRicette(Pageable pageable) {
        return repo.findAll(pageable).map(ricettaMapper::toDto);
    }

    public List<RicettaDto> findRicetteConTitolo(String titolo) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ricetta> cq = cb.createQuery(Ricetta.class);
        Root<Ricetta> ricetta = cq.from(Ricetta.class);
        Predicate titoloPredicate = cb.like(cb.lower(ricetta.get("titolo")), "%" + titolo.toLowerCase() + "%");
        cq.where(titoloPredicate);

        List<Ricetta> ricette = entityManager.createQuery(cq).getResultList();
        return ricette.stream()
                      .map(ricettaMapper::toDto)
                      .collect(Collectors.toList());
    }

    public void deleteRicetta(Integer id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Ricetta con ID " + id + " non trovata");
        }
        repo.deleteById(id);
    }

    public RicettaDto updateRicetta(Integer id, RicettaDto ricettaDto) {
        return repo.findById(id).map(existingRicetta -> {
            existingRicetta.setTitolo(ricettaDto.getTitolo());
            existingRicetta.setQuantitaPersone(ricettaDto.getQuantitaPersone());
            if (ricettaDto.getCategoria() != null) {
                Categoria categoria = categoriaMapper.toEntity(ricettaDto.getCategoria());
                existingRicetta.setCategoria(categoria);
            }
            existingRicetta.setIngredienti(ricettaDto.getIngredienti());
            existingRicetta.setPreparazione(ricettaDto.getPreparazione());
            
            Ricetta updatedRicetta = repo.save(existingRicetta);
            return ricettaMapper.toDto(updatedRicetta);
        }).orElseThrow(() -> new NotFoundException("Ricetta con ID " + id + " non trovata per l'aggiornamento"));
    }

}