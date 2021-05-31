package ru.mngerasimenko.distancecalculator.domain;

import ru.mngerasimenko.distancecalculator.calculator.DegreeAndRadian;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;
import ru.mngerasimenko.distancecalculator.settings.Settings;


public class City {

    private int city_id;
    private String city_name;
    private String latitude;
    private String longitude;
    private double latitudeRadian;
    private double longitudeRadian;

    public City() {
    }


    public City(String city_name, String latitude, String longitude) throws InvalidCoordinateFormatException, CityException {
        if (city_name == null || city_name.isEmpty())
            throw new CityException(Settings.ERROR_CITY_1);
        this.city_name = city_name;
        if (latitude == null || latitude.isEmpty())
            throw new InvalidCoordinateFormatException(Settings.ERROR_COORDINATE_4);
        if (isLatitude(latitude) && isLongitude(longitude)) {
            this.latitude = latitude;
            this.longitude = longitude;
        } else throw new InvalidCoordinateFormatException(Settings.ERROR_COORDINATE_3);
        this.latitudeRadian = DegreeAndRadian.degreeToRadian(DegreeAndRadian.degreeToDouble(latitude));
        this.longitudeRadian = DegreeAndRadian.degreeToRadian(DegreeAndRadian.degreeToDouble(longitude));

    }

    public City(int city_id, String city_name, String latitude, String longitude) throws CityException {
        this(city_name,latitude, longitude);
        this.city_id = city_id;
    }


    private boolean isLatitude(String source){
        char lastChar = source.toUpperCase().charAt(source.length() - 1);
        return lastChar == 'N' || lastChar == 'S';
    }

    private boolean isLongitude(String source){
        char lastChar = source.toUpperCase().charAt(source.length() - 1);
        return lastChar == 'W' || lastChar == 'E';
    }

    @Override
    public String toString() {
        return city_name + " (" + latitude + ", " + longitude + ")";
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitudeRadian(double latitudeRadian) {
        this.latitudeRadian = latitudeRadian;
    }

    public void setLongitudeRadian(double longitudeRadian) {
        this.longitudeRadian = longitudeRadian;
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
