package com.komlik.repository;

import com.komlik.domain.Person;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class PersonSecondRepositoryImpl implements PersonRepository {

    @Override
    public Person findOne(Long id) {
        return null;
    }

    @Override
    public List<Person> findAll() {
        return null;
    }

    @Override
    public Person create(Person object) {
        return null;
    }

    @Override
    public Person update(Person object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void searchPerson() {

    }
}
