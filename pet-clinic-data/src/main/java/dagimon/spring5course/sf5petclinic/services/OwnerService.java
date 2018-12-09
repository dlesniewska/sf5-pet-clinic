package dagimon.spring5course.sf5petclinic.services;

import dagimon.spring5course.sf5petclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);
}
