package dagimon.spring5course.sf5petclinic.services.map;

import dagimon.spring5course.sf5petclinic.model.Owner;
import dagimon.spring5course.sf5petclinic.model.Pet;
import dagimon.spring5course.sf5petclinic.services.OwnerService;
import dagimon.spring5course.sf5petclinic.services.PetService;
import dagimon.spring5course.sf5petclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerMapServiceImpl extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerMapServiceImpl(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return map.entrySet().stream().filter(entry -> entry.getValue().getLastName().equals(lastName)).findFirst().get().getValue();
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null) {
                        if(pet.getPetType().getId() == null) {
                            petTypeService.save(pet.getPetType());
                        }
                    } else {
                        throw new RuntimeException("PetTYpe must be specified");
                    }

                    if(pet.getId() != null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
