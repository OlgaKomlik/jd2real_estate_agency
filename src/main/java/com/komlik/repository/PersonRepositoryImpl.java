package com.komlik.repository;

import com.komlik.domain.Person;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
//bean id=userRepositoryImpl   class=UserRepositoryImpl
//@Component
public class PersonRepositoryImpl implements PersonRepository {

    public static final String POSTRGES_DRIVER_NAME = "org.postgresql.Driver";
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:";
    public static final int DATABASE_PORT = 5432;
    public static final String DATABASE_NAME = "/real_estate_agency";
    public static final String DATABASE_LOGIN = "postgres";
    public static final String DATABASE_PASSWORD = "root";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String BIRTH_DATE = "birth_date";
    private static final String PASSPORT_NUM = "passport_num";
    private static final String PHONE_NUM = "phone_num";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";

    private static final String IS_DELETED = "is_deleted";

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
            throw new RuntimeException(e);
        }

        return person;
    }

    private void registerDriver() {
        try {
            Class.forName(POSTRGES_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }
    }

    private Connection getConnection() {
        String jdbcURL = StringUtils.join(DATABASE_URL, DATABASE_PORT, DATABASE_NAME);
        try {
            return DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person findOne(Long id) {
        final String findOne = "SELECT * FROM persons WHERE id = ?";
        registerDriver();
        List<Person> result = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(findOne)
           ) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(parseResultSet(rs));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result.size() == 1 ? result.get(0) : null;
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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public void searchPerson() {

    }
}
