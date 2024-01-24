package it.ricette.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ricette.dao.CategoriaRepository;
import it.ricette.model.Categoria;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;

    public CategoriaService(CategoriaRepository CategoriaRepository) {
        this.repo = CategoriaRepository;
    }

    public Categoria saveCategoria(Categoria categoria) {
        return repo.save(categoria);
    }

    public Optional<Categoria> getCategoriaById(Integer id) {
        return repo.findById(id);
    }

    public List<Categoria> findAllCategorie() {
        return (List<Categoria>) repo.findAll();
    }

    public void deleteCategoria(Integer id) {
    	repo.deleteById(id);
    }

}
