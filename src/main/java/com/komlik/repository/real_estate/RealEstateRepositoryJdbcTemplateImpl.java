package com.komlik.repository.real_estate;

import com.komlik.domain.RealEstate;
import com.komlik.exceptions.EntityNotFoundException;
import com.komlik.exceptions.OwnerTypeException;
import com.komlik.repository.rowmapper.RealEstateRowMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
//@Order(1)
@RequiredArgsConstructor
public class RealEstateRepositoryJdbcTemplateImpl implements RealEstateRepository{

    private static final Logger logger = Logger.getLogger(RealEstateRepositoryJdbcTemplateImpl.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RealEstateRowMapper realEstateRowMapper;

    @Override
    public RealEstate findById(Long id) {
        RealEstate realEstate;
        try {
            realEstate = jdbcTemplate.queryForObject("select * from real_estates where id = " + id, realEstateRowMapper);
        } catch (RuntimeException e) {
            logger.warn(e.getMessage());
            throw new EntityNotFoundException("There is no id " + id);
        }
        return realEstate;
    }

    @Override
    public Optional<RealEstate> findOne(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from real_estates where id =  " + id,
                realEstateRowMapper));
    }

    @Override
    public RealEstate findByLastId() {
        return jdbcTemplate.queryForObject(
                "select * from real_estates where id = (select MAX(id) from real_estates)", realEstateRowMapper);
    }

    @Override
    public List<RealEstate> findAll() {
        return jdbcTemplate.query("select * from real_estates order by id desc", realEstateRowMapper);
    }

    @Override
    public RealEstate create(RealEstate object) {
        if (object.getTypeOwner().equals("person")) {
            final String createQuery = "insert into real_estates (square, rooms, floors, garden_square, garage, address, " +
                    "city, owner_persons_id, created, changed, type, type_owner) values (?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(createQuery,
                    object.getSquare(),
                    object.getRooms(),
                    object.getFloors(),
                    object.getGardenSquare(),
                    object.getGarage(),
                    object.getAddress(),
                    object.getCity(),
                    object.getOwnerPersonsId(),
                    Timestamp.valueOf(LocalDateTime.now()),
                    Timestamp.valueOf(LocalDateTime.now()),
                    object.getType(),
                    object.getTypeOwner());
        } else if (object.getTypeOwner().equals("company")) {
            final String createQuery = "insert into real_estates (square, rooms, floors, garden_square, garage, address, " +
                    "city, owner_companies_id, created, changed, type, type_owner) values (?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(createQuery,
                    object.getSquare(),
                    object.getRooms(),
                    object.getFloors(),
                    object.getGardenSquare(),
                    object.getGarage(),
                    object.getAddress(),
                    object.getCity(),
                    object.getOwnerCompaniesId(),
                    Timestamp.valueOf(LocalDateTime.now()),
                    Timestamp.valueOf(LocalDateTime.now()),
                    object.getType(),
                    object.getTypeOwner());
        } else {
            throw new OwnerTypeException("Please, select type of owner");
        }

        return findByLastId();
    }

    @Override
    public RealEstate update(RealEstate object) {
        final String updateQuery = "update real_estates set square = ?, rooms = ?, floors = ?, " +
                "garden_square = ?, garage = ?, address = ?, city = ?, owner_persons_id = ?, changed = ?, type = ?, " +
                "type_owner = ?, owner_companies_id = ?, is_deleted = ? where id = ?";
        jdbcTemplate.update(updateQuery,
                object.getSquare(),
                object.getRooms(),
                object.getFloors(),
                object.getGardenSquare(),
                object.getGarage(),
                object.getAddress(),
                object.getCity(),
                object.getOwnerPersonsId(),
                Timestamp.valueOf(LocalDateTime.now()),
                object.getType(),
                object.getTypeOwner(),
                object.getOwnerCompaniesId(),
                object.getIsDeleted(),
                object.getId());

        return findById(object.getId());
    }

    @Override
    public RealEstate delete(Long id) {
        final String deleteQuery = "update real_estates set is_deleted = ?, changed = ? where id = ?";
        jdbcTemplate.update(deleteQuery, true, Timestamp.valueOf(LocalDateTime.now()), id);
        return findById(id);
    }

    @Override
    public Optional<RealEstate> hardDelete(Long id) {
        final String hardDeleteQuery = "delete from real_estates where id = ?";
        jdbcTemplate.update(hardDeleteQuery, id);
        return findOne(id);
    }

    @Override
    public List<RealEstate> searchRealEstate(String city, String type, Integer square) {
        final String sqlQuery =
                "select * from real_estates " +
                        "where lower(city) like '%" + city + "%' and " +
                        "type like '%" + type + "%' and square >= " + square +
                        "order by id desc";

        return jdbcTemplate.query(sqlQuery, realEstateRowMapper);
    }

    @Override
    public RealEstate changeOwnerPersonsId(Long realEstateId, Long newOwnerPersonsId) {
       jdbcTemplate.update("call change_owner_person (?, ?, ?)", realEstateId, newOwnerPersonsId, Timestamp.valueOf(LocalDateTime.now()));

        return findById(realEstateId);
    }

    @Override
    public Integer findBigestFlatSquare(int rooms) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withFunctionName("find_big_flat_square");
        SqlParameterSource in = new MapSqlParameterSource().addValue("rooms_criteria", rooms);

        return simpleJdbcCall.executeFunction(Integer.class, in);
    }

    @Override
    public Integer findSmallFlatSquare(int rooms) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withFunctionName("find_small_flat_square");
        SqlParameterSource in = new MapSqlParameterSource().addValue("rooms_criteria", rooms);

        return simpleJdbcCall.executeFunction(Integer.class, in);
    }

    @Override
    public List<RealEstate> findBigestFlat(int rooms) {
        String findQuery = "select * from real_estates where square = find_big_flat_square(" + rooms + ") and type = 'flat' and rooms = " + rooms;
        return jdbcTemplate.query(findQuery, realEstateRowMapper);
    }

    @Override
    public List<RealEstate> findSmallFlat(int rooms) {
        String findQuery = "select * from real_estates where square = find_small_flat_square(" + rooms + ") and type = 'flat'and rooms = " + rooms;
        return jdbcTemplate.query(findQuery, realEstateRowMapper);
    }
}
