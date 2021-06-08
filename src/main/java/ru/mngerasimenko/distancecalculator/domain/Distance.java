package ru.mngerasimenko.distancecalculator.domain;

import ru.mngerasimenko.distancecalculator.calculator.Calculations;
import ru.mngerasimenko.distancecalculator.calculator.CrowFlightDistance;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.CityException;

public class Distance {

    int distance_id;
    private City fromCity;
    private City toCity;
    double distance;

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
        if(distance > 0) {
            this.distance = distance;
        } else {
            this.distance = new CrowFlightDistance(fromCity, toCity).getDistance();
        }
    }



    public City getFromCity() {
        return fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return fromCity + " --> " + toCity + " : " + (int)distance;
    }
}
