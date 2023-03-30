package com.komlik.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class RealEstate {
    private Long id;
    private Integer square;
    private Integer rooms;
    private Integer floors;
    private Integer gardenSquare;
    private Boolean garage;
    private String address;
    private String city;
    private Long ownerPersonsId;
    private String type;
    private Long ownerCompaniesId;
    private String typeOwner;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
