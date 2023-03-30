package com.komlik;


import com.komlik.repository.real_estate.RealEstateRepository;
import com.komlik.service.real_estate.RealEstateService;
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
        RealEstateRepository repositoryJdbcTemplate = applicationContext.getBean("realEstateRepositoryJdbcTemplateImpl", RealEstateRepository.class);
        RealEstateService service = applicationContext.getBean("realEstateServiceImpl", RealEstateService.class);
//        logger.info(repositoryJdbcTemplate.findAll());
//        logger.info(repositoryJdbcTemplate.findOne(1L));
//        logger.info(repositoryJdbcTemplate.findById(1L));
//        logger.info(repositoryJdbcTemplate.findByLastId());
//        logger.info(repositoryJdbcTemplate.create(RealEstate.builder()
//                .square(125)
//                .rooms(4)
//                .floors(2)
//                .gardenSquare(100)
//                .garage(true)
//                .address("Nemiga, 15")
//                .city("Minsk")
//                .ownerPersonsId(2L)
//                .type("house")
//                .typeOwner("person")
//                .build()));
//        logger.info(repositoryJdbcTemplate.create(RealEstate.builder()
//                .square(44)
//                .rooms(1)
//                .floors(12)
//                .gardenSquare(null)
//                .garage(null)
//                .address("Nemiga, 18")
//                .city("Minsk")
//                .ownerPersonsId(3L)
//                .type("flat")
//                .typeOwner("person")
//                .build()));
//        logger.info(repositoryJdbcTemplate.update(RealEstate.builder()
//                .id(7L).square(45)
//                .rooms(1)
//                .floors(12)
//                .gardenSquare(null)
//                .garage(null)
//                .address("Nemiga, 18")
//                .city("Minsk")
//                .ownerPersonsId(3L)
//                .type("flat")
//                .typeOwner("person")
//                .isDeleted(false).build()));

//        logger.info(repositoryJdbcTemplate.delete(7L));
//
//        logger.info(repositoryJdbcTemplate.searchRealEstate("min", "fl", 50));

//        logger.info(repositoryJdbcTemplate.changeOwnerPersonsId(6L, 3L));

        logger.info(repositoryJdbcTemplate.findBigestFlatSquare(3));
        logger.info(repositoryJdbcTemplate.findSmallFlatSquare(3));

        logger.info(repositoryJdbcTemplate.findBigestFlat(3));
        logger.info(repositoryJdbcTemplate.findSmallFlat(3));
    }
}
