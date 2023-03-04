package com.komlik.repository;

import com.komlik.domain.Person;

public interface PersonRepository extends CRUDRepository <Long, Person> {

    void searchPerson();
}
