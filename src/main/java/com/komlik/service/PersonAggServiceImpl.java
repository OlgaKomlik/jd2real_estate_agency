package com.komlik.service;

import com.komlik.domain.Person;
import com.komlik.repository.PersonRepository;
import com.komlik.repository.PersonRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonAggServiceImpl implements PersonAggregationService {
    private final PersonRepository personRepository = new PersonRepositoryImpl();

    @Override
    public Map<String, Object> getStats() {

        List<Person> persons = personRepository.findAll();
        Optional<Person> one = personRepository.findOne(2L);
        personRepository.searchPerson();

        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("allPersons", persons);
        resultMap.put("onePerson", one);

        return resultMap;
    }
}
