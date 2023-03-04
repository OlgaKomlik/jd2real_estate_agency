package com.komlik.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Person {

    private Long id;
    private String name;
    private String surname;
    private Timestamp birthDate;
    private String passportNum;
    private String phoneNum;
    private Timestamp created;
    private Timestamp changed;
    private Boolean is_Deleted;

    public Person() {
    }

    public Person(Long id, String name, String surname, Timestamp birthDate, String passportNum, String phoneNum, Timestamp created, Timestamp changed, Boolean is_Deleted) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.passportNum = passportNum;
        this.phoneNum = phoneNum;
        this.created = created;
        this.changed = changed;
        this.is_Deleted = is_Deleted;
    }

    public Person(String name, String surname, Timestamp birthDate, String passportNum, String phoneNum, Timestamp created, Timestamp changed) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.passportNum = passportNum;
        this.phoneNum = phoneNum;
        this.created = created;
        this.changed = changed;
    }

    public Person(Long id, String name, String surname, Timestamp birthDate, String passportNum, String phoneNum, Timestamp changed, Boolean is_Deleted) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.passportNum = passportNum;
        this.phoneNum = phoneNum;
        this.changed = changed;
        this.is_Deleted = is_Deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public void setChanged(Timestamp changed) {
        this.changed = changed;
    }

    public Boolean getIs_Deleted() {
        return is_Deleted;
    }

    public void setIs_Deleted(Boolean is_Deleted) {
        this.is_Deleted = is_Deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(birthDate, person.birthDate) && Objects.equals(passportNum, person.passportNum) && Objects.equals(phoneNum, person.phoneNum) && Objects.equals(created, person.created) && Objects.equals(changed, person.changed) && Objects.equals(is_Deleted, person.is_Deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthDate, passportNum, phoneNum, created, changed, is_Deleted);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", passportNum='" + passportNum + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", created=" + created +
                ", changed=" + changed +
                ", is_Deleted=" + is_Deleted +
                '}';
    }
}
