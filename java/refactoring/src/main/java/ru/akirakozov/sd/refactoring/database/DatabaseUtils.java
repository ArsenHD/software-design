package ru.akirakozov.sd.refactoring.database;

import java.sql.*;

public class DatabaseUtils {
    private static final String DATABASE_ADDRESS = "jdbc:sqlite:test.db";

    public static void createProductsTable() {
        try (Connection connection = DriverManager.getConnection(DATABASE_ADDRESS)) {
            String query = """
                    create table if not exists products
                    (id integer primary key autoincrement not null,
                     name           text    not null,
                     price          int     not null)""";
            connection.prepareStatement(query).execute();
        } catch (SQLException e) {
            System.err.printf("Failed to create table \"products\": %s\n", e.getMessage());
        }
    }

    public static void executeQuery(String query) {
        try (Connection connection = DriverManager.getConnection(DATABASE_ADDRESS)) {
            connection.prepareStatement(query).execute();
        } catch (SQLException e) {
            System.err.printf("Failed to execute query:\n%s\n%s", query, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void executeQueryWithResult(
            String query,
            UncheckedConsumer<ResultSet> consumer
    ) {
        try (Connection c = DriverManager.getConnection(DATABASE_ADDRESS)) {
            ResultSet rs = c.prepareStatement(query).executeQuery();
            consumer.accept(rs);
            rs.close();
        } catch (Exception e) {
            System.err.printf("Failed to execute query:\n%s\n%s", query, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
