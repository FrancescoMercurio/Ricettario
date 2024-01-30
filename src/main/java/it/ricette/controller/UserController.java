package it.ricette.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ricette.service.RicettaService;

@RestController
@RequestMapping("/api/utenti")
public class UserController {

    @Autowired
    private RicettaService ricettaService;

    @PostMapping("/{userId}/favorites/{ricettaId}/add")
    public ResponseEntity<?> addRicettaToFavorites(@PathVariable Integer userId, @PathVariable Integer ricettaId) {
        ricettaService.addRicettaToFavorites(userId, ricettaId);
        return ResponseEntity.ok("Ricetta aggiunta ai preferiti");
    }

    @DeleteMapping("/{userId}/favorites/{ricettaId}/remove")
    public ResponseEntity<?> removeRicettaFromFavorites(@PathVariable Integer userId, @PathVariable Integer ricettaId) {
        ricettaService.removeRicettaFromFavorites(userId, ricettaId);
        return ResponseEntity.ok("Ricetta rimossa dai preferiti");
    }
}