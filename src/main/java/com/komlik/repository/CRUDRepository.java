package com.komlik.repository;

import java.util.List;
import java.util.Optional;


public interface CRUDRepository<K, T> {

    T findById(K id);

    Optional<T> findOne(K id);

    T findByLastId();

    List<T> findAll();

    T create(T object);

    T update(T object);

    T delete(K id);

}
