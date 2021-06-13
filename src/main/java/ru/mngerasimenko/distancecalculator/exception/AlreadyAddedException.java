package ru.mngerasimenko.distancecalculator.exception;

public class AlreadyAddedException extends DaoException{

    public AlreadyAddedException() {
    }

    public AlreadyAddedException(String message) {
        super(message);
    }
}
