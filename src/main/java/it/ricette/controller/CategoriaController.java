package it.ricette.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import it.ricette.dto.CategoriaDto;
import it.ricette.exception.TokenBlacklistedException;
import it.ricette.service.CategoriaService;
import java.util.List;

@RestController
@RequestMapping("/api/categorie")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/crea-categoria")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoriaDto> createCategoria(@RequestBody CategoriaDto categoriaDto) {
        CategoriaDto savedCategoriaDto = categoriaService.saveCategoria(categoriaDto);
        return ResponseEntity.ok(savedCategoriaDto);
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<CategoriaDto> getCategoriaById(@PathVariable Integer id) {
        CategoriaDto categoriaDto = categoriaService.getCategoriaById(id);
        return ResponseEntity.ok(categoriaDto);
    }
    
    @GetMapping("/lista")
    public ResponseEntity<List<CategoriaDto>> getAllCategorie() {
        List<CategoriaDto> categorie = categoriaService.findAllCategorie();
        return ResponseEntity.ok(categorie);
    }
    
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteCategoria(@PathVariable Integer id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.ok().build();
    }
    
    @ExceptionHandler(TokenBlacklistedException.class)
    public ResponseEntity<?> handleTokenBlacklistedException(TokenBlacklistedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
}
