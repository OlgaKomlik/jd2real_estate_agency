package com.komlik.repository.real_estate;

import com.komlik.domain.RealEstate;
import com.komlik.repository.rowmapper.RealEstateRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
//@Order(1)
@RequiredArgsConstructor
public class RealEstateRepositoryJdbcTemplateImpl implements RealEstateRepository{

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RealEstateRowMapper realEstateRowMapper;

    @Override
    public RealEstate findById(Long id) {
        return jdbcTemplate.queryForObject("select * from real_estates where id = " + id, realEstateRowMapper);
    }

    @Override
    public Optional<RealEstate> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public RealEstate findByLastId() {
        return null;
    }

    @Override
    public List<RealEstate> findAll() {
        return jdbcTemplate.query("select * from real_estates order by id desc", realEstateRowMapper);
    }

    @Override
    public RealEstate create(RealEstate object) {
        return null;
    }

    @Override
    public RealEstate update(RealEstate object) {
        return null;
    }

    @Override
    public RealEstate delete(Long id) {
        return null;
    }

    @Override
    public void hardDelete(Long id) {

    }

    @Override
    public List<RealEstate> searchRealEstate(String city, String type, Integer square) {
        final String sqlQuery =
                "select * from real_estates " +
                        "where (lower) city like '%" + city + "%' and " +
                        "type like '%" + type + "%' and square >= " + square +
                        "order by id desc";

        return jdbcTemplate.query(sqlQuery, realEstateRowMapper);
    }
}
