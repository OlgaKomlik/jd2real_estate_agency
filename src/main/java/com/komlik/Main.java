package com.komlik;

import com.komlik.configuration.DatabaseProperties;
import com.komlik.domain.Person;
import com.komlik.repository.person.PersonRepository;
import com.komlik.repository.person.PersonRepositoryImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        PersonRepository personRepository = new PersonRepositoryImpl(new DatabaseProperties());

        List<Person> all = personRepository.findAll();

        for (Person person : all) {
            System.out.println(person);
        }
        Person person = new Person("Max", "Kovalov", Timestamp.valueOf(LocalDateTime.of(1989, 10, 15, 10, 55)), "MP4567896", "+35489652849", Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
        System.out.println(personRepository.create(person));
        System.out.println(personRepository.findOne(2L));
        System.out.println(personRepository.update(new Person(3L, "Max", "Orlov", Timestamp.valueOf(LocalDateTime.of(1989, 10, 15, 10, 55)), "MP4567896", "+35489652849", Timestamp.valueOf(LocalDateTime.now()), false)));
        personRepository.delete(3L);
        System.out.println(personRepository.findForSurnameAndName("Orlov", "Max"));
        System.out.println(personRepository.findBirthdayPersons(LocalDateTime.of(2023, 03, 22, 11, 15)));
    }
}
