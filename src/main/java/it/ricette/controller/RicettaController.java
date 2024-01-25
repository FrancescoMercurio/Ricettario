package it.ricette.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.ricette.NotFoundException;
import it.ricette.dto.RicettaDto;
import it.ricette.model.Ricetta;
import it.ricette.service.RicettaService;

import java.util.List;

@RestController
@RequestMapping("/api/ricette")
public class RicettaController {

    @Autowired
    private RicettaService ricettaService;

    @PostMapping("/crea-ricetta")
    public ResponseEntity<RicettaDto> createRicetta(@RequestBody RicettaDto ricettaDto) {
        RicettaDto savedRicettaDto = ricettaService.saveRicetta(ricettaDto);
        return ResponseEntity.ok(savedRicettaDto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RicettaDto> getRicettaById(@PathVariable Integer id) {
        RicettaDto ricettaDto = ricettaService.getRicettaById(id)
                                              .orElseThrow(() -> new NotFoundException("Ricetta con ID " + id + " non trovata"));
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

    @PutMapping("/update/{id}")
    public ResponseEntity<RicettaDto> updateRicetta(@PathVariable Integer id, @RequestBody RicettaDto ricettaDto) {
        RicettaDto updatedRicettaDto = ricettaService.updateRicetta(id, ricettaDto);
        return ResponseEntity.ok(updatedRicettaDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRicetta(@PathVariable Integer id) {
        ricettaService.deleteRicetta(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>("Ricetta non trovata: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}