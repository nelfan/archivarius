package com.example.tables;

import com.example.annotations.Entity;

abstract class TableInfo {
    protected String tableName;

    public TableInfo(Class<?> clazz) {
        var entityAnnotation = clazz.getAnnotation(Entity.class);
        tableName = entityAnnotation.value();
        if (tableName.isBlank()) {
            tableName = clazz.getSimpleName();
        }
    }

    public String getTableName() {
        return tableName;
    }
}
