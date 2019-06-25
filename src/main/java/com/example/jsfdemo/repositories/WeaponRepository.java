package com.example.jsfdemo.repositories;

import com.example.jsfdemo.data.Weapon;

public interface WeaponRepository extends CoreCrudRepository<Weapon> {
    Weapon findByName(String name) throws Exception;
}
