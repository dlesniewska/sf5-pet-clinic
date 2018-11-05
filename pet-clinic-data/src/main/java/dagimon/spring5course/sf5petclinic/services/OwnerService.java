package dagimon.spring5course.sf5petclinic.services;

import dagimon.spring5course.sf5petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
