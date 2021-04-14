package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrmManager extends DbConnection {

    Connection connection = getConnection();

    public void update(Object obj) throws SQLException {
        PreparedStatement preparedStatement = null;

        Class<?> clazz = obj.getClass();
        String tableName = OrmManagerUtil.getTableName(clazz);
        List<Object> idList = OrmManagerUtil.getAllIds(clazz);
        List<Object> columns = OrmManagerUtil.getAllColumns(clazz);
        String sql = OrmManagerUtil.getUpdateSql(tableName, idList, columns);
        ReflectionMapper rf = new ReflectionMapper();
        Map<String, Object> map = rf.toMap(obj);
        System.out.println("sql: " + sql);

        try {
            preparedStatement = connection.prepareStatement(sql);
            int counter = 1;
            for (var column : columns) {
                //System.out.println("Type: " + map.get(column) + " = " + map.get(column).getClass());
                //TODO: переделать if-else
                if (map.get(column).getClass() == String.class) {
                    preparedStatement.setString(counter, String.valueOf(map.get(column)));
                    counter++;
                } else if (map.get(column).getClass() == Integer.class) {
                    preparedStatement.setInt(counter, (Integer) map.get(column));
                    counter++;
                }
            }

            for (var id : idList) {
                preparedStatement.setString(counter, String.valueOf(map.get(id)));
                counter++;
            }

            System.out.println("ps: " + preparedStatement);

            //preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

