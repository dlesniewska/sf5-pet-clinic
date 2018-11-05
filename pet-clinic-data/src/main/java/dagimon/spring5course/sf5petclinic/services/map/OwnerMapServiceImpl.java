package dagimon.spring5course.sf5petclinic.services.map;

import dagimon.spring5course.sf5petclinic.model.Owner;
import dagimon.spring5course.sf5petclinic.services.CrudService;

import java.util.Set;

public class OwnerMapServiceImpl extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {

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
        return super.save(object.getId(), object);
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
