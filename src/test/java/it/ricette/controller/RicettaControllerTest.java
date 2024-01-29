package it.ricette.controller;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import it.ricette.dto.RicettaDto;
import it.ricette.service.RicettaService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.security.test.context.support.WithMockUser;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RicettaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RicettaService ricettaService;

    @InjectMocks
    private RicettaController ricettaController;

    @Test
    @WithMockUser(roles = "USER")
    public void testGetRicettaById() throws Exception {
        RicettaDto ricettaDto = new RicettaDto();
        when(ricettaService.getRicettaById(1)).thenReturn(Optional.of(ricettaDto));

        mockMvc = MockMvcBuilders.standaloneSetup(ricettaController).build();
        mockMvc.perform(get("/api/ricette/get/1"))
               .andExpect(status().isOk());
    }
    
    @Test
    @WithMockUser(roles = "USER")
    public void testCreateRicetta() throws Exception {
        String ricettaJson = "{"
            + "\"titolo\": \"pppppp\","
            + "\"preparazione\": \"adedmin\","
            + "\"quantitaPersone\": 1,"
            + "\"ingredienti\": \"pasta\","
            + "\"categoria\": {"
            + "    \"id\": 1,"
            + "    \"categoria\": \"primo\""
            + "}"
            + "}";

        mockMvc.perform(post("/api/ricette/crea-ricetta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ricettaJson))
               .andExpect(status().isUnauthorized());
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateRicettaADMIN() throws Exception {
        String ricettaJson = "{"
            + "\"titolo\": \"pppppp\","
            + "\"preparazione\": \"adedmin\","
            + "\"quantitaPersone\": 1,"
            + "\"ingredienti\": \"pasta\","
            + "\"categoria\": {"
            + "    \"id\": 1,"
            + "    \"categoria\": \"primo\""
            + "}"
            + "}";

        mockMvc.perform(post("/api/ricette/crea-ricetta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ricettaJson))
               .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "USER")
    public void testListRicette() throws Exception {
        mockMvc.perform(get("/api/ricette/lista")
                .param("page", "0")
                .param("size", "10"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content").exists());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testFindRicetteConTitolo() throws Exception {
        List<RicettaDto> ricette = Collections.singletonList(new RicettaDto());
        when(ricettaService.findRicetteConTitolo("Test")).thenReturn(ricette);

        mockMvc = MockMvcBuilders.standaloneSetup(ricettaController).build();
        mockMvc.perform(get("/api/ricette/search?titolo=Test"))
               .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testUpdateRicetta() throws Exception {
    	String ricettaJson = "{"
                + "\"titolo\": \"pppppp\","
                + "\"preparazione\": \"adedmin\","
                + "\"quantitaPersone\": 1,"
                + "\"ingredienti\": \"pasta\","
                + "\"categoria\": {"
                + "    \"id\": 1,"
                + "    \"categoria\": \"primo\""
                + "}"
                + "}";
        RicettaDto ricettaDto = new RicettaDto();
        when(ricettaService.updateRicetta(eq(1), any(RicettaDto.class))).thenReturn(ricettaDto);

        mockMvc = MockMvcBuilders.standaloneSetup(ricettaController).build();
        mockMvc.perform(put("/api/ricette/update/1")
                .contentType("application/json")
                .content(ricettaJson))
               .andExpect(status().isUnauthorized());
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateRicettaADMIN() throws Exception {
    	String ricettaJson = "{"
                + "\"titolo\": \"pppppp\","
                + "\"preparazione\": \"adedmin\","
                + "\"quantitaPersone\": 1,"
                + "\"ingredienti\": \"pasta\","
                + "\"categoria\": {"
                + "    \"id\": 1,"
                + "    \"categoria\": \"primo\""
                + "}"
                + "}";
        RicettaDto ricettaDto = new RicettaDto();
        when(ricettaService.updateRicetta(eq(1), any(RicettaDto.class))).thenReturn(ricettaDto);

        mockMvc = MockMvcBuilders.standaloneSetup(ricettaController).build();
        mockMvc.perform(put("/api/ricette/update/1")
                .contentType("application/json")
                .content(ricettaJson))
               .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testDeleteRicetta() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ricettaController).build();
        mockMvc.perform(delete("/api/ricette/delete/1"))
               .andExpect(status().isUnauthorized());

        verify(ricettaService).deleteRicetta(1);
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteRicettaADMIN() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ricettaController).build();
        mockMvc.perform(delete("/api/ricette/delete/1"))
               .andExpect(status().isOk());

        verify(ricettaService).deleteRicetta(1);
    }
}
