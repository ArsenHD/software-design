package ru.akirakozov.sd.refactoring.util;

import ru.akirakozov.sd.refactoring.database.DatabaseUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabaseUtils {
    public static void clearDatabase() {
        DatabaseUtils.executeQuery("drop table if exists product");
        DatabaseUtils.executeQuery("""
                    create table if not exists product(
                        id integer primary key autoincrement not null,
                        name text not null,
                        price int not null
                    )
                    """);
    }
}
