package com.komlik.repository;

import com.komlik.domain.Person;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
//@Primary
public class PersonSecondRepositoryImpl implements PersonRepository {

    @Override
    public Optional<Person> findOne(Long id) {
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
    public void hardDelete(Long id) {

    }

    @Override
    public List<Person> findForSurnameAndName(String surname, String name) {
        return null;
    }

    @Override
    public List<Person> findBirthdayPersons(LocalDateTime localDateTime) {
        return null;
    }

    @Override
    public void searchPerson() {

    }
}
