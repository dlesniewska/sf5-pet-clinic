package dagimon.spring5course.sf5petclinic.controllers;

import dagimon.spring5course.sf5petclinic.model.Pet;
import dagimon.spring5course.sf5petclinic.model.Visit;
import dagimon.spring5course.sf5petclinic.services.PetService;
import dagimon.spring5course.sf5petclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VisitControllerTest {

    VisitController visitController;
    MockMvc mockMvc;
    @Mock
    VisitService visitService;
    @Mock
    PetService petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        visitController = new VisitController(visitService, petService);
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());
        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"))
                .andExpect(model().attributeExists("pet"));
        verify(petService).findById(anyLong());
    }

    @Test
    void processNewForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());
        mockMvc.perform(post("/owners/1/pets/1/visits/new")
                .param("date", "2018-01-01")
                .param("description", "some description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"))
                .andExpect(model().attributeExists("pet"));
        verify(petService).findById(anyLong());
        verify(visitService).save(any(Visit.class));
    }
}