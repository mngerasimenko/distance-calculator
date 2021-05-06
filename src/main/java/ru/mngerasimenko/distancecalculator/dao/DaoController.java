package ru.mngerasimenko.distancecalculator.dao;

import ru.mngerasimenko.distancecalculator.exception.CalculateException;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;
import ru.mngerasimenko.distancecalculator.settings.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DaoController<E, K> {

    protected Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    public abstract List<E> findItem(String pattern) throws DaoException, CityException;
    public abstract E getItem(K id) throws DaoException, CityException;
    public abstract K insertItem(E item) throws DaoException, CalculateException;
    public abstract List<E> getAll() throws DaoException, CityException;

}
