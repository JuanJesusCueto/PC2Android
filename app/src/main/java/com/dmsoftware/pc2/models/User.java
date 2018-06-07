package com.dmsoftware.pc2.models;

import java.util.List;

public class User {

    private String name;
    private String lastName;
    private String country;
    private String biography;
    private String email;
    private List<Ability> abilities;

    public User() {
    }

    public User(String name, String lastName, String country, String biography, String email) {
        this.name = name;
        this.lastName = lastName;
        this.country = country;
        this.biography = biography;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }
}
