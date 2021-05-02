package ru.mngerasimenko.distancecalculator.dao;

import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;
import ru.mngerasimenko.distancecalculator.settings.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DaoController<E, K> {

    protected Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(Settings.getProperty(Settings.DB_URL),
                Settings.getProperty(Settings.DB_LOGIN), Settings.getProperty(Settings.DB_PASSWORD));
        return connection;
    }

    public abstract List<E> findItem(String pattern) throws DaoException, InvalidCoordinateFormatException;
    public abstract E getItem(K id) throws DaoException, InvalidCoordinateFormatException;
    public abstract K insertItem(E item) throws DaoException;
}
