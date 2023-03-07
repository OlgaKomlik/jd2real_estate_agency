package com.komlik.repository;

import com.komlik.configuration.DatabaseProperties;
import com.komlik.domain.Person;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
//bean id=userRepositoryImpl   class=UserRepositoryImpl
//@Component
public class PersonRepositoryImpl implements PersonRepository {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String BIRTH_DATE = "birth_date";
    private static final String PASSPORT_NUM = "passport_num";
    private static final String PHONE_NUM = "phone_num";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String IS_DELETED = "is_deleted";

    private final DatabaseProperties properties;

    @Override
    public List<Person> findAll() {

        /*
         * 1) Driver Manager - getting connection from DB
         * */

        final String findAllQuery = "select * from persons order by id desc";

        List<Person> result = new ArrayList<>();

        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findAllQuery)
        ) {

            while (rs.next()) {
                result.add(parseResultSet(rs));
            }
            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    private Person parseResultSet(ResultSet rs) {

        Person person;

        try {
            person = new Person();
            person.setId(rs.getLong(ID)); //1 or id
            person.setName(rs.getString(NAME));
            person.setSurname(rs.getString(SURNAME));
            person.setBirthDate(rs.getTimestamp(BIRTH_DATE));
            person.setPassportNum(rs.getString(PASSPORT_NUM));
            person.setPhoneNum(rs.getString(PHONE_NUM));
            person.setCreated(rs.getTimestamp(CREATED));
            person.setChanged(rs.getTimestamp(CHANGED));
            person.setIs_Deleted(rs.getBoolean(IS_DELETED));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }

        return person;
    }

    private void registerDriver() {
        try {
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }
    }

    private Connection getConnection() {
        String jdbcURL = StringUtils.join(properties.getUrl(), properties.getPort(), properties.getName());
        try {
            return DriverManager.getConnection(jdbcURL, properties.getLogin(), properties.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Person> findOne(Long id) {
        final String findOne = "SELECT * FROM persons WHERE id = ?";
        registerDriver();
        String name = "";
        String surname = "";
        Timestamp birthDate = null;
        String passportNum = "";
        String phoneNum = "";
        Timestamp created = null;
        Timestamp changed = null;
        boolean isDeleted = false;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(findOne)
           ) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                 name = rs.getString("name");
                 surname = rs.getString("surname");
                 birthDate = rs.getTimestamp("birth_date");
                 passportNum = rs.getString("passport_num");
                 phoneNum = rs.getString("phone_num");
                 created = rs.getTimestamp("created");
                 changed =rs.getTimestamp("changed");
                 isDeleted = rs.getBoolean("is_deleted");
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return Optional.of(new Person(id, name,surname, birthDate, passportNum, phoneNum, created, changed, isDeleted));
    }

    @Override
    public Person create(Person person) {
        String createQuery = "INSERT into persons (name, surname, birth_date, passport_num, phone_num, created, changed) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        registerDriver();
        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setTimestamp(3, person.getBirthDate());
            preparedStatement.setString(4, person.getPassportNum());
            preparedStatement.setString(5, person.getPhoneNum());
            preparedStatement.setTimestamp(6, person.getCreated());
            preparedStatement.setTimestamp(7, person.getChanged());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return person;
    }

    @Override
    public Person update(Person person) {
        String updateQuery = "UPDATE persons SET name = ?, surname = ?, birth_date = ?, passport_num = ?, phone_num = ?, changed = ?, is_Deleted = ? WHERE id = ?";
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setTimestamp(3, person.getBirthDate());
            preparedStatement.setString(4, person.getPassportNum());
            preparedStatement.setString(5, person.getPhoneNum());
            preparedStatement.setTimestamp(6, person.getChanged());
            preparedStatement.setBoolean(7, person.getIs_Deleted());
            preparedStatement.setLong(8, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return person;
    }

        @Override
        public void delete(Long id) {
        String deleteQuery = "UPDATE persons SET  is_Deleted = true, changed = ? WHERE id = ?";
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }
    @Override
    public void hardDelete(Long id) {
        final String hardDeleteQuery = "delete from users where id = ?";
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(hardDeleteQuery)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<Person> findForSurnameAndName(String surname, String name) {
        final String findOne = "SELECT * FROM persons WHERE surname = ? AND name = ?";
        registerDriver();
        List<Person> result = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(findOne)
        ) {
            preparedStatement.setString(1, surname);
            preparedStatement.setString(2, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(parseResultSet(rs));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return result;
    }

@Override
    public List<Person> findBirthdayPersons(LocalDateTime localDateTime) {
        final String findOne = "SELECT * FROM persons WHERE EXTRACT(day from birth_date) = ? AND EXTRACT(month from birth_date) = ?";
        registerDriver();
        List<Person> result = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(findOne)
        ) {
            preparedStatement.setInt(1, localDateTime.getDayOfMonth());
            preparedStatement.setInt(2, localDateTime.getMonthValue());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(parseResultSet(rs));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return result;
    }

    @Override
    public void searchPerson() {

    }
}
