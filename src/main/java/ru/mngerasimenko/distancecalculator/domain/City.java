package ru.mngerasimenko.distancecalculator.domain;

import ru.mngerasimenko.distancecalculator.calculator.DegreeAndRadian;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;
import ru.mngerasimenko.distancecalculator.settings.Settings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class City {

    @Id
    @GeneratedValue
    @Column(name = "city_id")
    private int cityId;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;

    private double latitudeRadian;
    private double longitudeRadian;

    public City() {
    }

    public City(String city_name, String latitude, String longitude) throws CityException {
        if (city_name == null || city_name.isEmpty())
            throw new CityException(Settings.ERROR_CITY_1);
        this.cityName = city_name;
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
        this.cityId = city_id;
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
        return cityName + " (" + latitude + ", " + longitude + ")";
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public double getLatitudeRadian() {
        return latitudeRadian;
    }

    public void setLatitudeRadian(double latitudeRadian) {
        this.latitudeRadian = latitudeRadian;
    }

    public double getLongitudeRadian() {
        return longitudeRadian;
    }

    public void setLongitudeRadian(double longitudeRadian) {
        this.longitudeRadian = longitudeRadian;
    }
}
