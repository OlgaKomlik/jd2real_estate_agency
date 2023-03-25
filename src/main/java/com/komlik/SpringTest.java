package com.komlik;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {
    private static final Logger logger = Logger.getLogger(SpringTest.class);
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.komlik");

        //Object bean = applicationContext.getBean();
//        UserRepository repository = applicationContext.getBean("userRepository", UserRepository.class);
//        PersonRepository personRepository = applicationContext.getBean("personRepositoryImpl", PersonRepository.class);
//        PersonService personService = applicationContext.getBean("personServiceImpl", PersonService.class);
//
//        Person person = new Person("Lexa", "Kovalov", Timestamp.valueOf(LocalDateTime.of(1989, 10, 15, 10, 55)), "MP4567896", "+35489652849", Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
//        System.out.println(personRepository.create(person));
//        System.out.println(personRepository.update(new Person(4L, "Lexa", "Losev", Timestamp.valueOf(LocalDateTime.of(1989, 10, 15, 10, 55)), "MP4567896", "+35489652849", Timestamp.valueOf(LocalDateTime.now()), false)));
//        System.out.println(personRepository.findAll());
//        System.out.println(personRepository.delete(4L));
//        System.out.println(personRepository.findByLastId());
//        System.out.println(personRepository.findOne(2L));
//        System.out.println(personRepository.findById(1L));
//        System.out.println(personRepository.findForSurnameAndName("Orlov", "Max"));
//        System.out.println(personRepository.findBirthdayPersons(LocalDateTime.of(2023, 03, 22, 11, 15)));

    }
}
