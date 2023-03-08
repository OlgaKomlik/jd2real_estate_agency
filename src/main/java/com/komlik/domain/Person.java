package com.komlik.domain;

import lombok.AllArgsConstructor;
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
public class Person {

    private Long id;
    private String name;
    private String surname;
    private Timestamp birthDate;
    private String passportNum;
    private String phoneNum;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;

    public Person(String name, String surname, Timestamp birthDate, String passportNum, String phoneNum, Timestamp created, Timestamp changed) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.passportNum = passportNum;
        this.phoneNum = phoneNum;
        this.created = created;
        this.changed = changed;
    }

    public Person(Long id, String name, String surname, Timestamp birthDate, String passportNum, String phoneNum, Timestamp changed, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.passportNum = passportNum;
        this.phoneNum = phoneNum;
        this.changed = changed;
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
