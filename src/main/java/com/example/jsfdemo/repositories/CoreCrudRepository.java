package com.example.jsfdemo.repositories;

import com.example.jsfdemo.data.AbstractEntity;

import java.util.List;

public interface CoreCrudRepository<T extends AbstractEntity> {
    List<T> findAll() throws Exception;

    T findById(Long id) throws Exception;

    void save(T entity) throws Exception;

    void delete(Long id) throws Exception;

    void update(T entity) throws Exception;
}
