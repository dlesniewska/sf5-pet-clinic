package dagimon.spring5course.sf5petclinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "VISITS")
public class Visit extends BaseEntity {

    private LocalDate date;
    private String description;

    @ManyToOne
    private Pet pet;
}
