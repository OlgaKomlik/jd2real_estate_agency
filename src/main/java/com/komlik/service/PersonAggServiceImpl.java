package com.komlik.service;

import com.komlik.domain.Person;
import com.komlik.repository.PersonRepository;
import com.komlik.repository.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PersonAggServiceImpl implements PersonAggregationService {

    @Autowired
    private PersonRepository personRepository;

    public PersonAggServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Map<String, String> getUsersPhones() {
        List<Person> persons = personRepository.findAll();
        Map<String, String> resultMap = new HashMap<>();
        for (Person person : persons) {
            String fullName = person.getSurname() + " " + person.getName();
            String phoneNum = person.getPhoneNum();
            resultMap.put(phoneNum, fullName);
        }
        return resultMap;
    }
}
