package com.komlik.repository.person;

import com.komlik.domain.Person;
import com.komlik.repository.CRUDRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonRepository extends CRUDRepository<Long, Person> {


    List<Person> findForSurnameAndName(String surname, String name);

    List<Person> findBirthdayPersons(LocalDateTime localDateTime);
}
