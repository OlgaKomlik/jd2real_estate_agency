package com.komlik.repository;

import com.komlik.domain.Person;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonRepository extends CRUDRepository<Long, Person> {


    List<Person> findForSurnameAndName(String surname, String name);

    List<Person> findBirthdayPersons(LocalDateTime localDateTime);

    void searchPerson();
}
