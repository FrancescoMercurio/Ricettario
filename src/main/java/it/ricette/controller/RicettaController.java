package it.ricette.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.ricette.model.Ricetta;
import it.ricette.service.RicettaService;

import java.util.List;

@RestController
@RequestMapping("/api/ricette")
public class RicettaController {

	@Autowired
	private RicettaService ricettaService;

    public RicettaController(RicettaService ricettaService) {
        this.ricettaService = ricettaService;
    }

    @PostMapping("/crea-ricetta")
    public ResponseEntity<Ricetta> createRicetta(@RequestBody Ricetta ricetta) {
        return ResponseEntity.ok(ricettaService.saveRicetta(ricetta));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Ricetta> getRicettaById(@PathVariable Integer id) {
        return ricettaService.getRicettaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Ricetta>> getAllRicette() {
        return ResponseEntity.ok(ricettaService.findAllRicette());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Ricetta>> findRicetteByTitolo(@RequestParam String titolo){
    	return ResponseEntity.ok(ricettaService.findRicetteByTitolo(titolo));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Ricetta> updateRicetta(@PathVariable Integer id, @RequestBody Ricetta ricetta) {
        return ResponseEntity.ok(ricettaService.updateRicetta(id, ricetta));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRicetta(@PathVariable Integer id) {
        ricettaService.deleteRicetta(id);
        return ResponseEntity.ok().build();
    }
}