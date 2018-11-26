package dagimon.spring5course.sf5petclinic.controllers;

import dagimon.spring5course.sf5petclinic.model.Owner;
import dagimon.spring5course.sf5petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerController ownerController;

    MockMvc mockMvc;

    private Set<Owner> mockedOwners;

    @BeforeEach
    void setUp() {
        mockedOwners = new HashSet<>();
        mockedOwners.add(Owner.builder().id(1L).build());
        mockedOwners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void list() throws Exception {
        when(ownerService.findAll()).thenReturn(mockedOwners);

        mockMvc.perform(
                get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)))
        ;
    }

    @Test
    void findOwner() throws Exception {
        mockMvc.perform(
                get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notImplemented"));

        verifyZeroInteractions(ownerService);
    }
}