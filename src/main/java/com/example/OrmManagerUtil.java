package com.example;

import com.example.annotations.Column;
import com.example.annotations.Entity;
import com.example.annotations.Id;
import com.example.tables.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class OrmManagerUtil {

    public static String getTableName(Class<?> clazz) {
        var entityAnnotation = clazz.getAnnotation(Entity.class);
        String tableName = entityAnnotation.value();
        if (tableName.isBlank()) {
            tableName = clazz.getSimpleName();
            //tableName = Utility.normalizeName(tableName);
        }
        return tableName;
    }

    public static List<Object> getAllIds(Class<?> clazz) {
        String id;
        List<Object> idList = new ArrayList<>();
        for (var field : clazz.getDeclaredFields()) {
            var idAnnotation = field.getAnnotation(Id.class);
            if (idAnnotation != null) {
                id = idAnnotation.value();
                if (id.isBlank()) {
                    id = field.getName();
                    //id = Utility.normalizeName(id);
                }
                idList.add(id);
            }
        }
        return idList;
    }

    public static List<Object> getAllColumns(Class<?> clazz) {
        List<Object> columns = new ArrayList<>();
        String id = null;
        for (var field : clazz.getDeclaredFields()) {
            var idAnnotation = field.getAnnotation(Id.class);
            if (idAnnotation != null) {
                id = idAnnotation.value();
                if (id.isBlank()) {
                    id = field.getName();
                }
            }

            var columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnName = columnAnnotation.value();
                if (columnName.isBlank()) {
                    columnName = field.getName();
                    //columnName = Utility.normalizeName(columnName);
                }
                if (!columnName.equals(id)) {
                columns.add(columnName);
                }
            }
        }

        return columns;
}

    public static String getUpdateSql(String tableName, List<Object> idList, List<Object> columnList){
        StringBuilder sql = new StringBuilder(String.format("UPDATE \"%s\" SET ", Utility.normalizeName(tableName)));

        ListIterator<Object> iterator = columnList.listIterator();
        while (iterator.hasNext()) {
            sql.append("\"" + Utility.normalizeName((String) iterator.next())).append("\"=?, ");
        }
        sql.deleteCharAt(sql.lastIndexOf(","));
        if (idList.size() == 1) {
            sql.append(String.format("WHERE \"%s\" =?", Utility.normalizeName((String) idList.get(0))));
        } else if (idList.size() > 1) {
            sql.append("WHERE ");
            ListIterator<Object> iteratorIds = idList.listIterator();
            while (iteratorIds.hasNext()) {
                sql.append(String.format("(\"%s\" =?) AND (\"%s\"= ?)",
                        Utility.normalizeName((String) iteratorIds.next()), Utility.normalizeName((String) iteratorIds.next())));
            }

        }
        return sql.toString();
    }
}
