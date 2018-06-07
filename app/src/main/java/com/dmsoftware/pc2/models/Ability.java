package com.dmsoftware.pc2.models;

public class Ability {
    private String name;
    private String experience;

    public Ability() {
    }

    public Ability(String name, String experience) {
        this.name = name;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
