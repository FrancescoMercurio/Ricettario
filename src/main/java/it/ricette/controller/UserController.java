package it.ricette.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import it.ricette.dto.RicettaDto;
import it.ricette.service.RicettaService;
import it.ricette.service.UserDetailsImpl;

@RestController
@RequestMapping("/api/utenti")
@CrossOrigin // Abilita CORS per tutti i metodi in questa classe
public class UserController {

    @Autowired
    private RicettaService ricettaService;

    private Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return userDetails.getId();
        }
        throw new IllegalStateException("Utente non autenticato");
    }

    @PostMapping("/favorites/{ricettaId}/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addRicettaToFavorites(@PathVariable Integer ricettaId) {
        Integer userId = getCurrentUserId();
        ricettaService.addRicettaToFavorites(userId, ricettaId);
        return ResponseEntity.ok("Ricetta aggiunta ai preferiti");
    }

    @DeleteMapping("/favorites/{ricettaId}/remove")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> removeRicettaFromFavorites(@PathVariable Integer ricettaId) {
        Integer userId = getCurrentUserId();
        ricettaService.removeRicettaFromFavorites(userId, ricettaId);
        return ResponseEntity.ok("Ricetta rimossa dai preferiti");
    }
    
    @GetMapping("/favorites")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<RicettaDto>> getFavoriteRicette() {
        Integer userId = getCurrentUserId();
        List<RicettaDto> favoriteRicette = ricettaService.getFavoriteRicetteByUserId(userId);
        return ResponseEntity.ok(favoriteRicette);
    }
}