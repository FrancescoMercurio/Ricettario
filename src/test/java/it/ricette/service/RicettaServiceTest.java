package it.ricette.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import it.ricette.dto.RicettaDto;
import it.ricette.mapper.RicettaMapper;
import it.ricette.model.Ricetta;
import it.ricette.repository.RicettaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RicettaServiceTest {

    @Mock
    private RicettaRepository ricettaRepository;

    private RicettaMapper ricettaMapper = Mappers.getMapper(RicettaMapper.class);

    @InjectMocks
    private RicettaService ricettaService;

    @Test
    void testSaveRicetta() {
        RicettaDto ricettaDto = new RicettaDto();
        Ricetta ricetta = new Ricetta();
        when(ricettaMapper.toEntity(any(RicettaDto.class))).thenReturn(ricetta);
        when(ricettaRepository.save(any(Ricetta.class))).thenReturn(ricetta);
        when(ricettaMapper.toDto(any(Ricetta.class))).thenReturn(ricettaDto);

        RicettaDto savedRicetta = ricettaService.saveRicetta(ricettaDto);

        assertNotNull(savedRicetta);
        verify(ricettaRepository).save(ricetta);
    }
    
    @Test
    void testGetRicettaById() {
        int ricettaId = 1;
        Ricetta ricetta = new Ricetta();
        ricetta.setId(ricettaId);
        RicettaDto ricettaDto = new RicettaDto();

        when(ricettaRepository.findById(ricettaId)).thenReturn(Optional.of(ricetta));
        when(ricettaMapper.toDto(ricetta)).thenReturn(ricettaDto);

        Optional<RicettaDto> result = ricettaService.getRicettaById(ricettaId);

        assertTrue(result.isPresent());
        assertEquals(ricettaDto, result.get());
    }
    
    @Test
    void testGetPaginatedRicette() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Ricetta> page = new PageImpl<>(Collections.singletonList(new Ricetta()));

        when(ricettaRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(ricettaMapper.toDto(any(Ricetta.class))).thenReturn(new RicettaDto());

        Page<RicettaDto> result = ricettaService.getPaginatedRicette(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }
    
    @Test
    void testFindRicetteConTitolo() {
        String titolo = "Test";
        Ricetta ricetta = new Ricetta();
        ricetta.setTitolo(titolo);
        List<Ricetta> ricette = Collections.singletonList(ricetta);

        when(ricettaRepository.findByTitoloContaining(titolo)).thenReturn(ricette);
        when(ricettaMapper.toDto(any(Ricetta.class))).thenReturn(new RicettaDto());

        List<RicettaDto> result = ricettaService.findRicetteConTitolo(titolo);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testDeleteRicetta() {
        int ricettaId = 1;
        when(ricettaRepository.existsById(ricettaId)).thenReturn(true);

        ricettaService.deleteRicetta(ricettaId);

        verify(ricettaRepository).deleteById(ricettaId);
    }

    @Test
    void testUpdateRicetta() {
        int ricettaId = 1;
        RicettaDto ricettaDto = new RicettaDto();
        Ricetta existingRicetta = new Ricetta();
        existingRicetta.setId(ricettaId);

        when(ricettaRepository.findById(ricettaId)).thenReturn(Optional.of(existingRicetta));
        when(ricettaRepository.save(any(Ricetta.class))).thenReturn(existingRicetta);
        when(ricettaMapper.toDto(any(Ricetta.class))).thenReturn(ricettaDto);

        RicettaDto result = ricettaService.updateRicetta(ricettaId, ricettaDto);

        assertNotNull(result);
        verify(ricettaRepository).save(any(Ricetta.class));
    }
}