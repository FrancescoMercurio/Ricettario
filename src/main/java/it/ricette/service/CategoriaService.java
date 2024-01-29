package it.ricette.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.ricette.mapper.CategoriaMapper;
import it.ricette.model.Categoria;
import it.ricette.dto.CategoriaDto;
import it.ricette.exception.NotFoundException;
import it.ricette.dao.CategoriaRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    @Autowired
    private CategoriaMapper categoriaMapper;

    public CategoriaDto saveCategoria(CategoriaDto categoriaDto) {
        Categoria categoria = categoriaMapper.toEntity(categoriaDto);
        Categoria savedCategoria = repo.save(categoria);
        return categoriaMapper.toDto(savedCategoria);
    }

    public CategoriaDto getCategoriaById(Integer id) {
        return repo.findById(id)
                   .map(categoriaMapper::toDto)
                   .orElseThrow(() -> new NotFoundException("Categoria con ID " + id + " non trovata"));
    }

    public List<CategoriaDto> findAllCategorie() {
        Iterable<Categoria> categorie = repo.findAll();
        return StreamSupport.stream(categorie.spliterator(), false)
                            .map(categoriaMapper::toDto)
                            .collect(Collectors.toList());
    }

    public void deleteCategoria(Integer id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Categoria con ID " + id + " non trovata");
        }
        repo.deleteById(id);
    }
}