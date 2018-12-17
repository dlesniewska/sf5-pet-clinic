package dagimon.spring5course.sf5petclinic.services.map;

import dagimon.spring5course.sf5petclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetMapServiceTest {

    PetMapService petMapService;
    private static final Long PET_ID = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(
                Pet.builder()
                        .id(PET_ID)
                        .build());
    }

    @Test
    void findAll() {
        Set<Pet> pets = petMapService.findAll();
        assertEquals(1, pets.size());
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(PET_ID);
        assertEquals(PET_ID, pet.getId());
    }

    @Test
    void saveExistingId() {
        Long ownerId = 2L;
        Pet pet = Pet.builder().id(ownerId).build();
        Pet savedPet2 = petMapService.save(pet);
        assertEquals(ownerId, savedPet2.getId());
    }

    @Test
    void saveEmptyId() {
        Pet pet = Pet.builder().build();
        Pet savedPet = petMapService.save(pet);
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(PET_ID));
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(PET_ID);
        assertEquals(0, petMapService.findAll().size());
    }
}