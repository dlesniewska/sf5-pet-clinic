package dagimon.spring5course.sf5petclinic.bootstrap;

import dagimon.spring5course.sf5petclinic.model.*;
import dagimon.spring5course.sf5petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    //default autowired by spring
    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, SpecialityService specialityService,
                      VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (petTypeService.findAll().size() == 0) {
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Sylwester");
        owner1.setLastName("Stallone");
        owner1.setAddress("Address1");
        owner1.setCity("City1");
        owner1.setTelephone("1234567");

        Pet stallonesPet = new Pet();
        stallonesPet.setOwner(owner1);
        stallonesPet.setName("Rock");
        stallonesPet.setPetType(savedDogType);
        stallonesPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(stallonesPet);

        ownerService.save(owner1);

        Visit stallonesPetVisit = new Visit();
        stallonesPetVisit.setPet(stallonesPet);
        stallonesPetVisit.setDate(LocalDate.now());
        stallonesPetVisit.setDescription("First visit");
        visitService.save(stallonesPetVisit);

        Owner owner2 = new Owner();
        owner2.setFirstName("John");
        owner2.setLastName("Rambo");
        owner2.setAddress("Address2");
        owner2.setCity("City2");
        owner2.setTelephone("1234567");

        Pet rambosPet = new Pet();
        rambosPet.setOwner(owner2);
        rambosPet.setName("RushB");
        rambosPet.setPetType(savedCatType);
        rambosPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(rambosPet);

        ownerService.save(owner2);

        Visit rambosPetVisit = new Visit();
        rambosPetVisit.setPet(rambosPet);
        rambosPetVisit.setDate(LocalDate.now());
        rambosPetVisit.setDescription("First visit");
        visitService.save(rambosPetVisit);

        System.out.println("Loaded owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Tara");
        vet1.setLastName("Lipinsky");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Sara");
        vet2.setLastName("Lipinsky");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loaded vets");
    }
}
