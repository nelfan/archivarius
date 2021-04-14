package com.example.tables;

import com.example.annotations.Column;

import java.lang.reflect.Field;
import java.sql.JDBCType;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class ColumnTableInfo extends TableInfo {
    private static Map<Class<?>, JDBCType> javaToSqlType = new HashMap<>();
    static {
        javaToSqlType.put(int.class, JDBCType.INTEGER);
        javaToSqlType.put(Integer.class, JDBCType.INTEGER);
        javaToSqlType.put(long.class, JDBCType.BIGINT);
        javaToSqlType.put(Long.class, JDBCType.BIGINT);
        javaToSqlType.put(String.class, JDBCType.VARCHAR);
        javaToSqlType.put(boolean.class, JDBCType.BIT);
        javaToSqlType.put(float.class, JDBCType.REAL);
        javaToSqlType.put(Float.class, JDBCType.REAL);
        javaToSqlType.put(short.class, JDBCType.SMALLINT);
        javaToSqlType.put(Short.class, JDBCType.SMALLINT);
        javaToSqlType.put(double.class, JDBCType.FLOAT);
        javaToSqlType.put(Double.class, JDBCType.FLOAT);
    }

    private Map<String, JDBCType> columns = new LinkedHashMap<>();

    public ColumnTableInfo(Class<?> clazz) {
        super(clazz);
        parse(clazz);
    }

    public Map<String, JDBCType> getColumns() {
        return Collections.unmodifiableMap(columns);
    }

    private void parse(Class<?> clazz) {
        for (var field : clazz.getDeclaredFields()) {
            parse(field);
        }
    }

    private void parse(Field field) {
        var columnAnnotation = field.getAnnotation(Column.class);
        if (columnAnnotation != null) {
            String columnName = columnAnnotation.value();
            if (columnName.isBlank()) {
                columnName = field.getName();
            }
            var sqlType = javaToSqlType.getOrDefault(
                    field.getType(),
                    JDBCType.OTHER); // TODO:
            columnName = Utility.normalizeName(columnName);
            columns.put(columnName, sqlType);
        }
    }
}
