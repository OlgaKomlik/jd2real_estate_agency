package com.komlik.service;

import com.komlik.domain.Person;
import com.komlik.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
//id = userServiceImpl
public class PersonServiceImpl implements PersonService {

   @Autowired
    private PersonRepository personRepository;

   /* public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }*/

    @Override
    public Person findOne(Long id) {
        return null;
    }

    @Override
    public List<Person> findAll() {
        /*Validation layer*/
        return personRepository.findAll();
    }

    @Override
    public Person create(Person object) {
        /*Validation layer*/

        if (object.getBirthDate().after(Timestamp.valueOf("2005-01-01"))) {
            throw new RuntimeException("Something wrong!");
        }

        return personRepository.create(object);
    }

    @Override
    public Person update(Person object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
