package dagimon.spring5course.sf5petclinic.services.map;

import dagimon.spring5course.sf5petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    private final static Long OWNER_ID = 1L;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService()); //dont need mock cause its very simple implementation of a map
        ownerMapService.save(
                Owner.builder()
                        .id(OWNER_ID)
                        .lastName("Kowalski")
                        .build());
    }

    @Test
    void findByLastName() {
        String ownerName = "Kowalski";
        Owner owner = ownerMapService.findByLastName(ownerName);
        assertNotNull(owner);
        assertEquals(ownerName, owner.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        String ownerName = "Nothing";
        Owner owner = ownerMapService.findByLastName(ownerName);
        assertNull(owner);
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(OWNER_ID);
        assertEquals(OWNER_ID, owner.getId());

    }

    @Test
    void saveExistingId() {
        Long ownerId = 2L;
        Owner owner = Owner.builder().id(ownerId).build();
        Owner savedOwner2 = ownerMapService.save(owner);
        assertEquals(ownerId, savedOwner2.getId());
    }

    @Test
    void saveEmptyId() {
        Owner owner = Owner.builder().build();
        Owner savedOwner = ownerMapService.save(owner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(OWNER_ID));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(OWNER_ID);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findAllByLastNameLike() {
        String ownerName = "Kowalski";
        List<Owner> owners = ownerMapService.findAllByLastNameLike(ownerName.substring(5));
        assertNotNull(owners);
        assertEquals(1, owners.size());
        assertEquals(ownerName, owners.get(0).getLastName());
    }

    @Test
    void findAllByLastNameLikeNotFound() {
        String ownerName = "Nothing";
        List<Owner> owners = ownerMapService.findAllByLastNameLike(ownerName);
        assertEquals(0, owners.size());
    }

    @Test
    void findAllByLastNameLikeNullFindAll() {
        String ownerName = null;
        List<Owner> owners = ownerMapService.findAllByLastNameLike(ownerName);
        assertEquals(1, owners.size());
    }
}