package com.komlik;


import com.komlik.repository.PersonRepository;
import com.komlik.service.PersonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
    }
}
