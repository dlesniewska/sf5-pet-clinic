package dagimon.spring5course.sf5petclinic.bootstrap;

import dagimon.spring5course.sf5petclinic.model.Owner;
import dagimon.spring5course.sf5petclinic.model.Pet;
import dagimon.spring5course.sf5petclinic.model.PetType;
import dagimon.spring5course.sf5petclinic.model.Vet;
import dagimon.spring5course.sf5petclinic.services.OwnerService;
import dagimon.spring5course.sf5petclinic.services.PetTypeService;
import dagimon.spring5course.sf5petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("cat");
        PetType savedCatType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Sylwester");
        owner1.setLastName("Stallone");
        owner1.setAddress("Address1");
        owner1.setCity("City1");
        owner1.setTelephone("1234567");

        Pet stallonesPet = new Pet();
        stallonesPet.setName("Rock");
        stallonesPet.setPetType(savedDogType);
        stallonesPet.setBirthDate(LocalDate.now());
        stallonesPet.setOwner(owner1);
        owner1.getPets().add(stallonesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("John");
        owner2.setLastName("Rambo");
        owner2.setAddress("Address2");
        owner2.setCity("City2");
        owner2.setTelephone("1234567");

        Pet rambosPet = new Pet();
        rambosPet.setName("RushB");
        rambosPet.setPetType(savedCatType);
        rambosPet.setBirthDate(LocalDate.now());
        rambosPet.setOwner(owner2);
        owner2.getPets().add(rambosPet);

        ownerService.save(owner2);

        System.out.println("Loaded owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Tara");
        vet1.setLastName("Lipinsky");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Sara");
        vet2.setLastName("Lipinsky");
        vetService.save(vet2);

        System.out.println("Loaded vets");
    }
}
