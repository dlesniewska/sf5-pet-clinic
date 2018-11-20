package dagimon.spring5course.sf5petclinic.repositories;

import dagimon.spring5course.sf5petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
