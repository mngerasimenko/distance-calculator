package ru.mngerasimenko.distancecalculator.exception;

public class InvalidCoordinateFormatException extends CityException{

    public InvalidCoordinateFormatException() {
    }

    public InvalidCoordinateFormatException(String message) {
        super(message);
    }

//    public InvalidCoordinateFormatException(String message, Throwable cause) {
//        super(message, cause);
//    }
}
