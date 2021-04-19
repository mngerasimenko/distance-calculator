package ru.mngerasimenko.distancecalculator.domain;

import ru.mngerasimenko.distancecalculator.calculator.Calculations;
import ru.mngerasimenko.distancecalculator.domain.City;

public class Distance {

    private City fromCity;
    private City toCity;
    double distance;

    public Distance(City fromCity, City toCity, double distance) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
    }

    public Distance(Calculations calc) {
        this.distance = calc.getDistance();
    }


}
