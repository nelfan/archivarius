package com.example;

import java.util.Map;

public interface Mapper {
    abstract <T> T toEntity(Map<String, Object> map, Class<T> type);

    <T> Map<String, Object> toMap(T entity);
}
