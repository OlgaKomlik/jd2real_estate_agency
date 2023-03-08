package com.komlik.service;

import com.komlik.domain.Person;

import java.util.List;

public interface PersonService {
    Person findOne(Long id);

    List<Person> findAll();

    Person create(Person object);

    Person update(Person object);

    void delete(Long id);
}
