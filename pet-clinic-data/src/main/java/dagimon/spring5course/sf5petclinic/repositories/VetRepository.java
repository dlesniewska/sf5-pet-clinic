package dagimon.spring5course.sf5petclinic.repositories;

import dagimon.spring5course.sf5petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
