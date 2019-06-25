package com.example.jsfdemo.repositories;

import com.example.jsfdemo.data.Weapon;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class WeaponRepositoryImpl extends CoreCrudRepositoryImpl<Weapon> implements WeaponRepository {
    @Override
    public Class getManagedClass() {
        return Weapon.class;
    }

    @Override
    public Weapon findByName(String name) throws Exception {
        Query query = em.createQuery("select n from " + getManagedClass().getSimpleName() + " n where n.name=:name");
        query.setParameter("name", name);
        List<Weapon> list = query.getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
