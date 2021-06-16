package ru.mngerasimenko.distancecalculator.calculator;

import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.domain.Crawler;
import ru.mngerasimenko.distancecalculator.storage.DistanceStorage;

import java.util.*;

public class MatrixDistance implements Calculations {

    DistanceStorage distanceStorage;
    Map<City, Crawler> calcPath;
    List<City> listExistPath;
    City fromCity;
    City toCity;
    double toCityDist;
    boolean arrive;

    public MatrixDistance(City fromCity, City toCity, DistanceStorage distanceStorage) {
        this.distanceStorage = distanceStorage;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.toCityDist = Double.MAX_VALUE;
        listExistPath = new ArrayList<>();
        initCalcPath();
    }

    /**
     *  The calcPath map is initialized.
     *  Fill in all the cities available for crawling.
     */
    private void initCalcPath() {
        if (calcPath == null) calcPath = new HashMap<City, Crawler>();

        distanceStorage.getCityMap().forEach((city, citySet) -> {
            Crawler crawler = new Crawler( Double.MAX_VALUE);
            calcPath.put((City) city, crawler);
        });
        calcPath.get(fromCity).setDistance(0);
        calcPath.remove(toCity);
    }

    /**
     *
     * @param crawler
     * @param fromCity
     */
    private void calculateDist(Crawler crawler, City fromCity) {

        City toCity = crawler.getCity();
        if (toCity.getCityId() == this.toCity.getCityId()) {
            double newDist = crawler.getDistance() + calcPath.get(fromCity).getDistance();
            toCityDist = newDist < toCityDist ? newDist : toCityDist;
            arrive = true;
        } else if (calcPath.containsKey(toCity)) {
            double dist = crawler.getDistance();
            listExistPath.add(toCity);
            if (calcPath.get(toCity).getDistance() > dist) {
                calcPath.get(toCity).setDistance(dist + calcPath.get(fromCity).getDistance());

            }
        }
    }

    private void searchPath() {
        ((HashSet) distanceStorage.getCityMap().get(fromCity)).forEach(item -> {
            calculateDist((Crawler) item, fromCity);
        });
        calcPath.remove(fromCity);
        while (!listExistPath.isEmpty()) {
            City fCity = listExistPath.get(0);
            Set<City> citySet = (Set<City>)distanceStorage.getCityMap().get(fCity);
            ((HashSet) citySet).forEach(item -> {
                calculateDist((Crawler) item, (City) fCity);
            });
            calcPath.remove(fCity);
            listExistPath.remove(fCity);
        }
    }

    public void print() {
        for (Object obj : calcPath.entrySet()) {
            Map.Entry mapItem = (Map.Entry) obj;
            System.out.print(mapItem.getKey() + " -> ");
            Crawler crawler = (Crawler) mapItem.getValue();
            System.out.print(crawler.getCity() + ":");
            System.out.printf("%.0f ", crawler.getDistance());
        }
        System.out.println();
    }

    @Override
    public double getDistance() {
            searchPath();
        if (arrive) {
            double result = Math.round(toCityDist * 100);
            return result / 100;
        }
        return -1;
    }
}
