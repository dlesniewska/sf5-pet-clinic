package dagimon.spring5course.sf5petclinic.repositories;

import dagimon.spring5course.sf5petclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
