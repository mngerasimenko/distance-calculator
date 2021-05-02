package ru.mngerasimenko.distancecalculator.domain;

import ru.mngerasimenko.distancecalculator.calculator.DegreeAndRadian;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;
import ru.mngerasimenko.distancecalculator.settings.Settings;

public class City {

    private int city_id;
    private String city_name;
    private String latitude;
    private String longitude;
    private double latitudeRadian;
    private double longitudeRadian;


    public City(String city_name, String latitude, String longitude) throws InvalidCoordinateFormatException{
        this.city_name = city_name;
        if (isLatitude(latitude) && isLongitude(longitude)) {
            this.latitude = latitude;
            this.longitude = longitude;
        } else throw new InvalidCoordinateFormatException(Settings.ERROR_COORDINATE_2);
        this.latitudeRadian = DegreeAndRadian.degreeToRadian(DegreeAndRadian.degreeToDouble(latitude));
        this.longitudeRadian = DegreeAndRadian.degreeToRadian(DegreeAndRadian.degreeToDouble(longitude));

    }

    public City(int city_id, String city_name, String latitude, String longitude) throws InvalidCoordinateFormatException{
        this(city_name,latitude, longitude);
        this.city_id = city_id;
    }


    private boolean isLatitude(String source){
        char lastChar = source.toUpperCase().charAt(source.length() - 1);
        if (lastChar == 'N' || lastChar == 'S') return true;
        return false;
    }

    private boolean isLongitude(String source){
        char lastChar = source.toUpperCase().charAt(source.length() - 1);
        if (lastChar == 'W' || lastChar == 'E') return true;
        return false;
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

    public String getCity_name() {
        return city_name;
    }

    public int getCity_id() {
        return city_id;
    }
}
