package ru.mngerasimenko.distancecalculator.domain;

import ru.mngerasimenko.distancecalculator.calculator.DegreeAndRadian;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;
import ru.mngerasimenko.distancecalculator.settings.Settings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.Locale;
import java.util.Objects;


@XmlRootElement(name = "city")
@XmlType(propOrder = {"cityName", "latitude", "longitude"})
public class City {


    private int cityId;
    private String cityName;
    private String latitude;
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
            this.latitude = latitude.toUpperCase(Locale.ROOT).replace(',', '.');
            this.longitude = longitude.toUpperCase(Locale.ROOT).replace(',', '.');
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

    @XmlTransient
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @XmlElement(name = "cityName")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @XmlElement(name = "latitude")
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @XmlElement(name = "longitude")
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @XmlTransient
    public double getLatitudeRadian() {
        return latitudeRadian;
    }

    public void setLatitudeRadian(double latitudeRadian) {
        this.latitudeRadian = latitudeRadian;
    }

    @XmlTransient
    public double getLongitudeRadian() {
        return longitudeRadian;
    }

    public void setLongitudeRadian(double longitudeRadian) {
        this.longitudeRadian = longitudeRadian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return cityName.equals(city.cityName) && latitude.equals(city.latitude) && longitude.equals(city.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, latitude, longitude);
    }
}
