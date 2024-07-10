package ru.clevertec.check.dao.db;

import ru.clevertec.check.entity.DbCredentials;
import ru.clevertec.check.exception.InternalServerException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ru.clevertec.check.dao.DaoConstantRepository.POSTGRES_DRIVER_CLASSNAME;

public class PostgresConnection {
    private static Connection connection;

    private PostgresConnection() {
    }

    public static void init(DbCredentials dbCredentials) throws InternalServerException {
        try {
            Class.forName(POSTGRES_DRIVER_CLASSNAME);
            connection = DriverManager.getConnection(dbCredentials.getUrl(),
                    dbCredentials.getUsername(),
                    dbCredentials.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            throw new InternalServerException();
        }
    }

    public static Connection get() {
        return connection;
    }

    public static void close() throws InternalServerException {
        if (connection != null) {
            try {
                connection.close();
                DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
                    try {
                        DriverManager.deregisterDriver(driver);
                    } catch (SQLException e) {
                        throw new RuntimeException();
                    }
                });
            } catch (SQLException e) {
                throw new InternalServerException();
            }
        }
    }
}
