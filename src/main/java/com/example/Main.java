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


        //ReflectionMapper rf = new ReflectionMapper();
        //printMap(rf.toMap(myBird));



    }

    public static <K, V> void printMap(Map<K, V> myMap) {
        for (Map.Entry<K, V> item : myMap.entrySet()) {

            System.out.println(item.getKey() + " = " + item.getValue());
        }
    }

    /*public static String[] generator(Stream<? extends Class<?>> streamClasses){
        List<Class<?>> classesList = streamClasses.collect(Collectors.toList());
        String[] queries = new String[classesList.size()];
        int counter = 0;

        ListIterator<? extends Class<?>> listIterator = classesList.listIterator();
        ListIterator<? extends Class<?>> listIterator2 = classesList.listIterator();
        ListIterator<? extends Class<?>> listIterator3 = classesList.listIterator();

        while (listIterator.hasNext()){
            queries[counter] = "CREATE TABLE " + TableInfoOld.getTableName(listIterator.next()) + " (\n";
            TableInfoOld.getFields(listIterator2.next());
            Map<String, Type> fields = TableInfoOld.getFields(listIterator3.next());
            for (Map.Entry<String, Type> field: fields.entrySet()) {
                queries[counter]+=field.getKey() + " " + field.getValue() + ",\n";
            }
            queries[counter] += ");";
            counter++;
        }
        return queries;
    }*/


}
