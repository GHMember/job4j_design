package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, IOException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException, IOException {
            Class.forName(properties.getProperty("driver_class"));
            String url = properties.getProperty("url");
            String login = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) {
        String sqlStatement = String.format(
                "CREATE TABLE IF NOT EXISTS %s(%s, %s);",
                tableName,
                "id serial",
                "name text"
        );
        useStatement(sqlStatement);
    }

    public void dropTable(String tableName) {
        String sqlStatement = String.format(
                "DROP TABLE IF EXISTS %s;",
                tableName
        );
        useStatement(sqlStatement);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sqlStatement = String.format(
                "ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s",
                tableName,
                columnName,
                type
        );
        useStatement(sqlStatement);
    }

    public void dropColumn(String tableName, String columnName) {
        String sqlStatement = String.format(
                "ALTER TABLE %s DROP COLUMN IF EXISTS %s",
                tableName,
                columnName
        );
        useStatement(sqlStatement);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sqlStatement = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName,
                columnName,
                newColumnName
        );
        useStatement(sqlStatement);
    }

    public void useStatement(String sqlStatement) {
        try (Statement statement = connection.createStatement()) {
            String sql = sqlStatement;
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("my_test");
            System.out.println(tableEditor.getTableScheme("my_test"));
            tableEditor.dropColumn("my_test", "name");
            System.out.println(tableEditor.getTableScheme("my_test"));
            tableEditor.addColumn("my_test", "age", "integer");
            System.out.println(tableEditor.getTableScheme("my_test"));
            tableEditor.renameColumn("my_test", "age", "new_age");
            System.out.println(tableEditor.getTableScheme("my_test"));
            tableEditor.dropTable("my_test");
        }
    }
}
