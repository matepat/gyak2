package com.example.jsfdemo.data;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "prime_weapon")
@Entity
public class Weapon extends AbstractEntity {
    @Column(unique = true)
    @NotEmpty(message = "{name.not.null}")
    private String name;

    public Weapon() {
    }

    public Weapon(Long id, Date createdDate, Date lastModifiedDate, String name) {
        super(id, createdDate, lastModifiedDate);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return name.equals(((Weapon) obj).getName());
    }
}
