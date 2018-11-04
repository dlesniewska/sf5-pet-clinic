package dagimon.spring5course.sf5petclinic.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
