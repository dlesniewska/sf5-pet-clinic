package dagimon.spring5course.sf5petclinic.services;

import dagimon.spring5course.sf5petclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
