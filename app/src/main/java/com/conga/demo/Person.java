package com.conga.demo;

public class Person {
    private Integer id;
    private String name;
    private String gender;
    private String address;
    private String nationality;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Person() {
    }

    public Person(Integer id, String name, String gender, String address, String nationality) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.nationality = nationality;
    }
}
