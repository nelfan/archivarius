package com.example.entity;

import com.example.annotations.Column;
import com.example.annotations.Entity;
import com.example.annotations.Id;

import java.util.Objects;

@Entity("")
public class Animal {
    @Id("")
    @Column("")
    private String id;
    @Column("")
    private String name;

    public Animal(){
    }

    public Animal(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return Objects.equals(getId(), animal.getId()) && Objects.equals(getName(), animal.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
