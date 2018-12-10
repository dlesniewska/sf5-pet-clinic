package dagimon.spring5course.sf5petclinic.services.springdatajpa;

import dagimon.spring5course.sf5petclinic.model.Owner;
import dagimon.spring5course.sf5petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService ownerService;

    private Owner mockedOwner;
    private final static String OWNER_LASTNAME = "Kowalski";
    private final static Long OWNER_ID = 1L;

    @BeforeEach
    void setUp() {
        mockedOwner = Owner.builder().id(OWNER_ID).lastName(OWNER_LASTNAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(mockedOwner);

        Owner resultOwner = ownerService.findByLastName("Kowalski");
        verify(ownerRepository).findByLastName(any());
        assertNotNull(resultOwner);
        assertEquals(mockedOwner.getLastName(), resultOwner.getLastName());
    }

    @Test
    void findAll() {
        Set<Owner> mockedOwners = new HashSet<>();
        mockedOwners.add(mockedOwner);
        mockedOwners.add(Owner.builder().id(2L).lastName("Nowak").build());
        when(ownerRepository.findAll()).thenReturn(mockedOwners);

        Set<Owner> resultOwners = ownerService.findAll();
        verify(ownerRepository).findAll();
        assertNotNull(resultOwners);
        assertEquals(2, resultOwners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(mockedOwner));

        Owner resultOwner = ownerService.findById(OWNER_ID);
        verify(ownerRepository).findById(anyLong());
        assertNotNull(resultOwner);
        assertEquals(OWNER_ID, resultOwner.getId());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner resultOwner = ownerService.findById(OWNER_ID);
        verify(ownerRepository).findById(anyLong());
        assertNull(resultOwner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any(Owner.class))).thenReturn(mockedOwner);

        Owner resultOwner = ownerService.save(mockedOwner);
        verify(ownerRepository).save(any(Owner.class));
        assertNotNull(resultOwner);
        assertEquals(OWNER_ID, resultOwner.getId());
    }

    @Test
    void delete() {
        ownerService.delete(mockedOwner);

        verify(ownerRepository).delete(any(Owner.class));
    }

    @Test
    void deleteById() {
        ownerService.deleteById(OWNER_ID);

        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findAllByLastNameLike() {
        String ownerName = "Kowalski";
        when(ownerRepository.findAllByLastNameLike(anyString())).thenReturn(
                Arrays.asList(
                        Owner.builder().id(1L).lastName(ownerName).build()));

        List<Owner> owners = ownerService.findAllByLastNameLike(ownerName.substring(5));
        assertNotNull(owners);
        assertEquals(1, owners.size());
        assertEquals(ownerName, owners.get(0).getLastName());
    }

    @Test
    void findAllByLastNameLikeNotFound() {
        String ownerName = "Nothing";
        when(ownerRepository.findAllByLastNameLike(anyString())).thenReturn(
                new ArrayList<>());

        List<Owner> owners = ownerService.findAllByLastNameLike(ownerName);
        assertEquals(0, owners.size());
    }

    @Test
    void findAllByLastNameLikeNullFindAll() {
        String ownerName = null;
        when(ownerRepository.findAllByLastNameLike(isNull())).thenReturn(
                Arrays.asList(
                        Owner.builder().id(1L).lastName(ownerName).build()));

        List<Owner> owners = ownerService.findAllByLastNameLike(null);
        assertEquals(1, owners.size());
    }
}