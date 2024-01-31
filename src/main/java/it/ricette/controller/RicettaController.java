package it.ricette.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import it.ricette.dto.RicettaDto;
import it.ricette.exception.NotFoundException;
import it.ricette.exception.TokenBlacklistedException;
import it.ricette.service.RicettaService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ricette")
public class RicettaController {

    @Autowired
    private RicettaService ricettaService;

    @GetMapping("/get/{id}")
    public ResponseEntity<RicettaDto> getRicettaById(@PathVariable Integer id) {
        RicettaDto ricettaDto = ricettaService.getRicettaById(id);                                            
        return ResponseEntity.ok(ricettaDto);
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<RicettaDto>> list(Pageable pageable) {
        Page<RicettaDto> page = ricettaService.getPaginatedRicette(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RicettaDto>> findRicetteConTitolo(@RequestParam String titolo) {
        List<RicettaDto> ricette = ricettaService.findRicetteConTitolo(titolo);
        return ResponseEntity.ok(ricette);
    }
    
    @PostMapping("/crea-ricetta")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RicettaDto> createRicetta(@RequestBody RicettaDto ricettaDto) {
        RicettaDto savedRicettaDto = ricettaService.saveRicetta(ricettaDto);
        return ResponseEntity.ok(savedRicettaDto);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RicettaDto> updateRicetta(@PathVariable Integer id, @RequestBody RicettaDto ricettaDto) {
        RicettaDto updatedRicettaDto = ricettaService.updateRicetta(id, ricettaDto);
        return ResponseEntity.ok(updatedRicettaDto);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteRicetta(@PathVariable Integer id) {
        ricettaService.deleteRicetta(id);
        return ResponseEntity.ok().build();
    }
    
//    @GetMapping("/{ricettaId}/getImage")
//    public ResponseEntity<?> getImage(@PathVariable Integer ricettaId) {
//        try {
//            RicettaDto ricettaDto = ricettaService.getImage(ricettaId);
//            return ResponseEntity.ok(ricettaDto.getImage());
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/{ricettaId}/saveImage")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> saveImage(@PathVariable Integer ricettaId, @RequestParam("image") MultipartFile file) {
//        try {
//            byte[] imageBytes = file.getBytes();
//            RicettaDto ricettaDto = ricettaService.saveImage(ricettaId, imageBytes);
//            return ResponseEntity.ok(ricettaDto);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nel salvataggio dell'immagine");
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>("Ricetta non trovata: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(TokenBlacklistedException.class)
    public ResponseEntity<?> handleTokenBlacklistedException(TokenBlacklistedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
}
