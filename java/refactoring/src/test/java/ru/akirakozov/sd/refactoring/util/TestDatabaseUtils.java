package ru.akirakozov.sd.refactoring.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabaseUtils {
    public static void clearDatabase() {
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            String sql = "drop table if exists product";
            c.prepareStatement(sql).execute();
        } catch (SQLException e) {
            System.err.printf("Failed to clear table \"products\": %s\n", e.getMessage());
            throw new RuntimeException(e);
        }

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            String sql = """
                    create table if not exists product(
                        id integer primary key autoincrement not null,
                        name text not null,
                        price int not null
                    )
                    """;
            c.prepareStatement(sql).execute();
        } catch (SQLException e) {
            System.err.printf("Failed to clear table \"products\": %s\n", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
