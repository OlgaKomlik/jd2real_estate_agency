package com.komlik.repository.rowmapper;

import com.komlik.domain.RealEstate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.komlik.repository.columns.RealEstateColumns.ADDRESS;
import static com.komlik.repository.columns.RealEstateColumns.CHANGED;
import static com.komlik.repository.columns.RealEstateColumns.CITY;
import static com.komlik.repository.columns.RealEstateColumns.CREATED;
import static com.komlik.repository.columns.RealEstateColumns.FLOORS;
import static com.komlik.repository.columns.RealEstateColumns.GARAGE;
import static com.komlik.repository.columns.RealEstateColumns.GARDEN_SQUARE;
import static com.komlik.repository.columns.RealEstateColumns.ID;
import static com.komlik.repository.columns.RealEstateColumns.IS_DELETED;
import static com.komlik.repository.columns.RealEstateColumns.OWNER_COMPANIES_ID;
import static com.komlik.repository.columns.RealEstateColumns.OWNER_PERSONS_ID;
import static com.komlik.repository.columns.RealEstateColumns.ROOMS;
import static com.komlik.repository.columns.RealEstateColumns.SQUARE;
import static com.komlik.repository.columns.RealEstateColumns.TYPE;
import static com.komlik.repository.columns.RealEstateColumns.TYPE_OWNER;

@Component
public class RealEstateRowMapper implements RowMapper<RealEstate> {

    @Override
    public RealEstate mapRow(ResultSet rs, int rowNum) throws SQLException {
        RealEstate realEstate;

        try {
            realEstate = RealEstate.builder()
                    .id(rs.getLong(ID))
                    .square(rs.getInt(SQUARE))
                    .rooms(rs.getInt(ROOMS))
                    .floors(rs.getInt(FLOORS))
                    .gardenSquare(rs.getInt(GARDEN_SQUARE))
                    .garage(rs.getBoolean(GARAGE))
                    .address(rs.getString(ADDRESS))
                    .city(rs.getString(CITY))
                    .ownerPersonsId(rs.getLong(OWNER_PERSONS_ID))
                    .type(rs.getString(TYPE))
                    .ownerCompaniesId(rs.getLong(OWNER_COMPANIES_ID))
                    .typeOwner(rs.getString(TYPE_OWNER))
                    .created(rs.getTimestamp(CREATED))
                    .changed(rs.getTimestamp(CHANGED))
                    .isDeleted(rs.getBoolean(IS_DELETED))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return realEstate;
    }
}
