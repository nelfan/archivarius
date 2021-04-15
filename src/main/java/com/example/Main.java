package com.example;

import com.example.entity.Animal;
import com.example.entity.Bird;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {


        Animal myAnimal = new Animal("id1", "animalName");
        Bird myBird = new Bird("123", "Alex", 13, "parrot");
        myBird.setAge(99);

        OrmManager ormManager = new OrmManager();
        ormManager.update(myBird);

        /*OrmManager ormManager2 = new OrmManager();
        ormManager2.update(myAnimal);*/

    }
}
