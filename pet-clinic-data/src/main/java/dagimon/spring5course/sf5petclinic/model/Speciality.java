package dagimon.spring5course.sf5petclinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "SPECIALITIES")
public class Speciality extends BaseEntity {

    private String description;
}
