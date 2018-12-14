package dagimon.spring5course.sf5petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PETS")
public class Pet extends BaseEntity {

    private String name;
    private LocalDate birthDate;

    @ManyToOne
    private PetType petType;

    @ManyToOne
    private Owner owner;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private Set<Visit> visits = new HashSet<>();

    @Builder
    public Pet(Long id, String name, LocalDate birthDate, PetType petType, Owner owner, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.birthDate = birthDate;
        this.petType = petType;
        this.owner = owner;
        if (visits != null) {
            this.visits = visits;
        }
    }

    public void addVisit(Visit visit) {
        this.visits.add(visit);
        visit.setPet(this);
    }
}
