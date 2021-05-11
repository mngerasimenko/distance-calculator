package ru.mngerasimenko.distancecalculator.exception;

public class DaoException extends Exception{

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
