package com.komlik.repository;

import java.util.List;
import java.util.Optional;

//K - key, datatype of PK
//T - type of object
public interface CRUDRepository <K, T> {
    //    CRUD - operations
//    Create - Insert
//    Read - Select (by id, all, filtered)
//    Update
//    Delete

    Optional<T> findOne(K id);

    List<T> findAll();

    T create(T object);

    T update(T object);

    void delete(K id);

    void hardDelete(K id);
}
