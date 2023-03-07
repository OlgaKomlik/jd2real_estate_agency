package com.komlik;


import com.komlik.repository.PersonRepository;
import com.komlik.service.PersonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

public class SpringTest {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.komlik");

        //Object bean = applicationContext.getBean();
//        UserRepository repository = applicationContext.getBean("userRepository", UserRepository.class);
        PersonRepository personRepository = applicationContext.getBean("personRepositoryImpl", PersonRepository.class);
        PersonService personService = applicationContext.getBean("personServiceImpl", PersonService.class);

        System.out.println(personRepository.findAll());
        System.out.println(personService.findAll());
        System.out.println(personRepository.findOne(2L));
        System.out.println(personRepository.findForSurnameAndName("Orlov", "Max"));
        System.out.println(personRepository.findBirthdayPersons(LocalDateTime.of(2023, 03, 22, 11, 15)));
    }
}
