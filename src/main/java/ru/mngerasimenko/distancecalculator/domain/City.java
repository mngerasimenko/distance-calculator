package ru.mngerasimenko.distancecalculator.domain;

import ru.mngerasimenko.distancecalculator.calculator.DegreeAndRadian;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;

public class City {

    private String name;
    private String latitude;
    private String longitude;
    private double latitudeRadian;
    private double longitudeRadian;


    public City(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        try {
            this.latitudeRadian = DegreeAndRadian.degreeToRadian(DegreeAndRadian.degreeToDouble(latitude));
            this.longitudeRadian = DegreeAndRadian.degreeToRadian(DegreeAndRadian.degreeToDouble(longitude));
        } catch (InvalidCoordinateFormatException ex) {
            System.out.println("InvalidCoordinateFormatException");
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return name + " (" + latitude + ", " + longitude + ")";
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public double getLatitudeRadian() {
        return latitudeRadian;
    }

    public double getLongitudeRadian() {
        return longitudeRadian;
    }
}
