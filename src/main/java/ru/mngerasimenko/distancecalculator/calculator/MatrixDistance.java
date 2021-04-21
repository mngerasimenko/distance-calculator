package ru.mngerasimenko.distancecalculator.calculator;

import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.domain.Сrawler;
import ru.mngerasimenko.distancecalculator.storage.DistanceStorage;

import java.util.*;

public class MatrixDistance implements Calculations {

    Map<City, Сrawler> calcPath;
    City fromCity;
    City toCity;
    double toCityDist;
    public int i;

    public MatrixDistance(City fromCity, City toCity) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.toCityDist = Double.MAX_VALUE;
        initCalcPath();
    }

    private void initCalcPath() {
        if (calcPath == null) calcPath = new HashMap<City, Сrawler>();
        DistanceStorage.cityMap.forEach((city, citySet) -> {
            Сrawler crawler = new Сrawler(false, Double.MAX_VALUE);
            calcPath.put((City) city, crawler);
        });
        calcPath.get(fromCity).setPassed(true);
        calcPath.get(fromCity).setDistance(0);
        calcPath.remove(toCity);
    }

    private void calculateDist(Сrawler crawler, City fromCity) {

        City toCity = crawler.getCity();
        if (toCity == this.toCity) {
            double newDist = crawler.getDistance() + calcPath.get(fromCity).getDistance();
            toCityDist = newDist < toCityDist ? newDist : toCityDist;
        } else if (calcPath.containsKey(toCity)) {
            double dist = crawler.getDistance();
            if (calcPath.containsKey(fromCity)) {
                if (calcPath.get(toCity).getDistance() > dist) {
                    calcPath.get(toCity).setDistance(dist + calcPath.get(fromCity).getDistance());
                    calcPath.get(toCity).setPassed(true);
                }
            }
        }
    }

    private void searchPath() {
        ((HashSet) DistanceStorage.cityMap.get(fromCity)).forEach(item -> {
            calculateDist((Сrawler) item, fromCity);
        });
        calcPath.remove(fromCity);
        while (!calcPath.isEmpty()) {
            DistanceStorage.cityMap.forEach((fromCity, citySet) -> {
                if (calcPath.containsKey(fromCity) && calcPath.get(fromCity).isPassed()) {
                    ((HashSet) citySet).forEach(item -> {
                        calculateDist((Сrawler) item, (City) fromCity);
                    });
                }
                calcPath.remove(fromCity);
            });
        }
    }

    public void print() {
        for (Object obj : calcPath.entrySet()) {
            Map.Entry mapItem = (Map.Entry) obj;
            System.out.print(mapItem.getKey() + " -> ");
            Сrawler сrawler = (Сrawler) mapItem.getValue();
            System.out.print(сrawler.getCity() + ":");
            System.out.printf("%.0f ", сrawler.getDistance());
        }
        System.out.println();
    }

    @Override
    public double getDistance() {
        searchPath();
        return toCityDist;
    }
}
