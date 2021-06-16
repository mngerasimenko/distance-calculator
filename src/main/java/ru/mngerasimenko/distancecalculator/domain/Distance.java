package ru.mngerasimenko.distancecalculator.domain;

import ru.mngerasimenko.distancecalculator.calculator.CrowFlightDistance;
import ru.mngerasimenko.distancecalculator.exception.CityException;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "distance")
@XmlType(propOrder = {"fromCity", "toCity", "distance"})
public class Distance {

    int distance_id;
    private City fromCity;
    private City toCity;
    double distance;

    public Distance() {
    }

    public Distance(City fromCity, City toCity) throws CityException {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = new CrowFlightDistance(fromCity, toCity).getDistance();
    }

    public Distance(int distance_id, City fromCity, City toCity) throws CityException {
        this(fromCity, toCity);
        this.distance_id = distance_id;
    }

    public Distance(int distance_id, City fromCity, City toCity, double distance) throws CityException {
        this.distance_id = distance_id;
        this.fromCity = fromCity;
        this.toCity = toCity;
        if (distance > 0) {
            this.distance = distance;
        } else {
            this.distance = new CrowFlightDistance(fromCity, toCity).getDistance();
        }
    }

    @XmlTransient
    public int getDistance_id() {
        return distance_id;
    }

    public void setDistance_id(int distance_id) {
        this.distance_id = distance_id;
    }

    @XmlElement(name = "fromCity")
    public City getFromCity() {
        return fromCity;
    }

    @XmlElement(name = "toCity")
    public City getToCity() {
        return toCity;
    }

    @XmlElement(name = "dist")
    public double getDistance() {
        return distance;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return fromCity + " --> " + toCity + " : " + (int) distance;
    }
}
