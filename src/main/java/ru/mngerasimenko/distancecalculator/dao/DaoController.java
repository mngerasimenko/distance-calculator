package ru.mngerasimenko.distancecalculator.dao;

import ru.mngerasimenko.distancecalculator.exception.DaoException;

import java.util.List;

public abstract class DaoController<E, K> {

    public abstract List<E> findItem(String pattern) throws DaoException;
    public abstract E getItem(K id) throws DaoException;
}
