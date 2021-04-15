package com.example.entity;

import com.example.annotations.Column;
import com.example.annotations.Entity;
import com.example.annotations.Id;

@Entity
public class Bird{
    @Id
    @Column
    private String id;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private String species;

    public Bird() {
    }

    public Bird(String id, String name, int age, String species) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.species = species;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSpecies() {
        return species;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", species='" + species + '\'' +
                '}';
    }
}
