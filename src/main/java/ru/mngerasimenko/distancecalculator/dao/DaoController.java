package ru.mngerasimenko.distancecalculator.dao;

import ru.mngerasimenko.distancecalculator.exception.CalculateException;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class DaoController<E, K> {

    protected ConnectionBuilder connectionBuilder;

    public DaoController() {
        connectionBuilder = new PoolConnectionBuilder();
    }

    public DaoController(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    protected Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }

    public abstract List<E> findItem(String pattern) throws DaoException, CityException;
    public abstract E getItem(K id) throws DaoException, CityException;
    public abstract K insertItem(E item) throws DaoException, CalculateException, CityException;
    public abstract List<E> getAll() throws DaoException, CityException;

}
