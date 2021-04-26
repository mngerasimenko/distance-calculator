package ru.mngerasimenko.distancecalculator.domain;

import ru.mngerasimenko.distancecalculator.calculator.DegreeAndRadian;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;

public class City {

    private long city_id;
    private String city_name;
    private String latitude;
    private String longitude;
    private double latitudeRadian;
    private double longitudeRadian;


    public City(String city_name, String latitude, String longitude) {
        this.city_name = city_name;
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

    public City(long city_id, String city_name, String latitude, String longitude) {
        this(city_name,latitude, longitude);
        this.city_id = city_id;

    }

    @Override
    public String toString() {
        return city_name + " (" + latitude + ", " + longitude + ")";
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
