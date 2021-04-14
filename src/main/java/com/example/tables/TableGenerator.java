package com.example.tables;

import com.example.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class TableGenerator {
    PriorityQueue<DdlExpression> ddlPq = new PriorityQueue<DdlExpression>(
            Comparator.comparing(DdlExpression::getPriority)
    );

    TableGenerator(Stream<TableInfo> tableInfoStream) {
        tableInfoStream
                .map(DdlExpression::from)
                .forEach(ddlPq::add);
    }

    void executeDdl() throws SQLException {
        Connection conn = null;
        var st = conn.createStatement();
        while (!ddlPq.isEmpty()) {
            st.execute(ddlPq.poll().ddl);
        }
    }

    static abstract class DdlExpression {
        String ddl;
        int priority;

        public int getPriority() {
            return priority;
        }

        static DdlExpression from(TableInfo tableInfo) {
            if (tableInfo instanceof ColumnTableInfo) {
                return new DdlExpressionTable((ColumnTableInfo) tableInfo);
            }
            if (tableInfo instanceof PkTableInfo) {
                return new DdlExpressionPk((PkTableInfo) tableInfo);
            }
            if (tableInfo instanceof FkTableInfo) {
                return new DdlExpressionFk((FkTableInfo) tableInfo);
            }
            throw new IllegalArgumentException("Unknown TableInfo subtype");
        }
    }

    static class DdlExpressionTable extends DdlExpression {
        public DdlExpressionTable(ColumnTableInfo columnTableInfo) {
            priority = 1;
            // TODO:
            ddl = "CREATE TABLE logs (\n" +
                    "log_id serial PRIMARY KEY, \n" +
                    "user_name varchar(50), \n" +
                    "description text,\n" +
                    ");";
        }
    }

    static class DdlExpressionPk extends DdlExpression {
        public DdlExpressionPk(PkTableInfo pkTableInfo) {
            priority = 2;
            //TODO:
            String tableName = pkTableInfo.getTableName();
            List<String> idColumnNames = pkTableInfo.getIdColumns();
            ddl = String.format("ALTER TABLE %s\n" +
                            "ADD CONSTRAINT PK_%s PRIMARY KEY (%s);",
                    tableName,
                    tableName,
                    String.join(",", idColumnNames));
        }
    }

    static class DdlExpressionFk extends DdlExpression {
        public DdlExpressionFk(FkTableInfo fkTableInfo) {
            priority = 3;
        }
    }
}
