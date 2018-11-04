package dagimon.spring5course.sf5petclinic.services;

import dagimon.spring5course.sf5petclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
