package com.example.jsfdemo.repositories;

import com.example.jsfdemo.data.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

public abstract class CoreCrudRepositoryImpl<T extends AbstractEntity> implements CoreCrudRepository<T> {
    public abstract Class getManagedClass();

    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<T> findAll() throws Exception {
        return em.createQuery("select n from " + getManagedClass().getSimpleName() + " n").getResultList();
    }

    @Override
    public T findById(Long id) throws Exception {
        return (T) em.find(getManagedClass(), id);
    }

    @Override
    public void save(T entity) throws Exception {
        entity.setCreatedDate(new Date());
        entity.setLastModifiedDate(new Date());
        em.persist(entity);
    }

    @Override
    public void delete(Long id) throws Exception {
        em.remove(findById(id));
    }

    @Override
    public void update(T entity) throws Exception {
        entity.setLastModifiedDate(new Date());
        em.merge(entity);
    }
}
