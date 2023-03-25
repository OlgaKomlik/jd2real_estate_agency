package com.komlik.repository.person;

import com.komlik.configuration.DatabaseProperties;
import com.komlik.domain.Person;
import com.komlik.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
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
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return result;
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
            person.setIsDeleted(rs.getBoolean(IS_DELETED));
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
        Optional<Person> person = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findOne)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                person = Optional.of(parseResultSet(rs));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return person;
    }

    @Override
    public Person findById(Long id) {
        final String findById = "SELECT * FROM persons WHERE id = ?";
        registerDriver();
        Person person = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findById)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                person = parseResultSet(rs);
            }
            rs.close();
        } catch (SQLException e ) {
            throw new RuntimeException("SQL Issues!");
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Not found users");
        }
        return person;
    }

    @Override
    public Person findByLastId() {
        final String lastIdQuery = "SELECT * FROM persons  WHERE Id = (SELECT MAX(Id) FROM persons)";
        Person person = new Person();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(lastIdQuery)) {
            if (rs.next()) {
                person = parseResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return person;
    }

    @Override
    public Person create(Person person) {
        final String createQuery = "INSERT into persons (name, surname, birth_date, passport_num, phone_num, created, changed) " +
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
        return findByLastId();
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
            preparedStatement.setBoolean(7, person.getIsDeleted());
            preparedStatement.setLong(8, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return findById(person.getId());
    }

    @Override
    public Person delete(Long id) {
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
        return findById(id);
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
        try (Connection connection = getConnection();
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
        try (Connection connection = getConnection();
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
}
