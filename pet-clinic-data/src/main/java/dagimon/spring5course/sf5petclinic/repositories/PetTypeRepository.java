package dagimon.spring5course.sf5petclinic.repositories;

import dagimon.spring5course.sf5petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
