package it.ricette.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ricette.model.Categoria;
import it.ricette.service.CategoriaService;

@RestController
@RequestMapping("/api/categorie")
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;

    public CategoriaController(CategoriaService CategoriaService) {
        this.categoriaService = CategoriaService;
    }
    
    @PostMapping("/crea-categoria")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.saveCategoria(categoria));
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id) {
        return categoriaService.getCategoriaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/lista")
    public ResponseEntity<List<Categoria>> getAllRicette() {
        return ResponseEntity.ok(categoriaService.findAllCategorie());
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Integer id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.ok().build();
    }

}
