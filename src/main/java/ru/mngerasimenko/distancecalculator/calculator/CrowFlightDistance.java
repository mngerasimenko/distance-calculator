package ru.mngerasimenko.distancecalculator.calculator;

import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.settings.Settings;

public class CrowFlightDistance implements Calculations{

    private City fromCity;
    private City toCity;

    public CrowFlightDistance(City fromCity, City toCity) {
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    @Override
    public double getDistance() {
        //cos(d) = sin(φА)·sin(φB) + cos(φА)·cos(φB)·cos(λА − λB)
        double yA = fromCity.getLatitudeRadian();
        double yB = toCity.getLatitudeRadian();
        double lA = fromCity.getLongitudeRadian();
        double lB = toCity.getLongitudeRadian();
        double d = Math.sin(yA) * Math.sin(yB) + Math.cos(yA) * Math.cos(yB) * Math.cos(lA - lB);
        //System.out.println(d);
        return Math.acos(d) * Settings.EARTH_RADIUS;
    }
}
