package ru.mngerasimenko.distancecalculator.settings;

public interface Settings {
    double EARTH_RADIUS = 6371.0;
    double PI_180 = 3.1415926535897932384626433832795 / 180;

    String ERROR_COORDINATE_2 = "There is no side of the world in the coordinate";
    String ERROR_COORDINATE_1 = "Coordinate format error";
}
