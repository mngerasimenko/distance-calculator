package ru.mngerasimenko.distancecalculator.exception;

public class InvalidCoordinateFormatException extends Exception{

    public InvalidCoordinateFormatException() {
    }

    public InvalidCoordinateFormatException(String message) {
        super(message);
    }

    public InvalidCoordinateFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
