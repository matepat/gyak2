package com.example.jsfdemo.data;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "prime_robot")
public class Robot extends AbstractEntity{
    @Column
    @NotEmpty(message = "{name.not.null}")
    private String name;

    @Column
    @NotEmpty(message = "{type.not.null}")
    private String type;

    @NotNull
    @ManyToOne
    @JoinTable(name = "w",
            joinColumns = {@JoinColumn(name = "robot_id")},
            inverseJoinColumns = {@JoinColumn(name = "weapon_id")}
    )
    private Weapon weapon;

    public Robot() {
    }

    public Robot(Long id, Date createdDate, Date lastModifiedDate, String name, String type, Weapon weapon) {
        super(id, createdDate, lastModifiedDate);
        this.name = name;
        this.type = type;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
