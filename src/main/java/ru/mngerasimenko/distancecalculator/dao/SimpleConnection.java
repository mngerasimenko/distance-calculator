package ru.mngerasimenko.distancecalculator.dao;

import ru.mngerasimenko.distancecalculator.settings.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnection implements ConnectionBuilder {
    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                Settings.getProperty(Settings.DB_URL),
                Settings.getProperty(Settings.DB_LOGIN),
                Settings.getProperty(Settings.DB_PASSWORD));
        return connection;
    }
}
