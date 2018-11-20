package dagimon.spring5course.sf5petclinic.repositories;

import dagimon.spring5course.sf5petclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
