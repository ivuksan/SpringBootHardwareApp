package hr.tvz.vuksan.hardwareapp.security.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "AUTHORITY_NAME", length = 50, nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
