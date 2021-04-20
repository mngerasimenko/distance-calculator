package ru.mngerasimenko.distancecalculator.domain;

import ru.mngerasimenko.distancecalculator.calculator.Calculations;
import ru.mngerasimenko.distancecalculator.calculator.CrowFlightDistance;
import ru.mngerasimenko.distancecalculator.domain.City;

public class Distance {

    private City fromCity;
    private City toCity;
    double distance;

    public Distance(City fromCity, City toCity) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = new CrowFlightDistance(fromCity, toCity).getDistance();
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
