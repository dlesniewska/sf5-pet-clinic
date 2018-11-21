package dagimon.spring5course.sf5petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "VETS")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "VET_SPECIALITIES", joinColumns = @JoinColumn(name = "VET_ID"), inverseJoinColumns = @JoinColumn(name = "SPECIALITY_ID"))
    private Set<Speciality> specialities = new HashSet<>();
}
