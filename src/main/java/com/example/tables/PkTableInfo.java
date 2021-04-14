package com.example.tables;

import com.example.annotations.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PkTableInfo extends TableInfo {

    public PkTableInfo(Class<?> clazz) {
        super(clazz);
        parse(clazz);
    }

    private List<String> idColumns = new ArrayList<>();

    public List<String> getIdColumns() {
        return Collections.unmodifiableList(idColumns);
    }

    private void parse(Class<?> clazz) {
        for (var field : clazz.getDeclaredFields()) {
            parse(field);
        }
    }

    private void parse(Field field) {
        var idAnnotation = field.getAnnotation(Id.class);
        if (idAnnotation != null) {
            String idName = idAnnotation.value();
            if (idName.isBlank()) {
                idName = field.getName();
            }
            idName = Utility.normalizeName(idName);
            idColumns.add(idName);
        }
    }
}
