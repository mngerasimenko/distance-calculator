package ru.mngerasimenko.distancecalculator.storage;


import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.domain.Distance;
import ru.mngerasimenko.distancecalculator.domain.Crawler;

import javax.inject.Singleton;
import java.util.*;

public class DistanceStorage extends Storage{

    @Singleton
    private HashMap cityMap;
    private Set<City> citySet;

    public DistanceStorage() {
        cityMap = new HashMap<City, HashSet<Crawler>>();
        citySet = new HashSet<>();
    }

    @Override
    public void addItem(Object item) {
        if (item instanceof Distance) {
            Distance dist = (Distance) item;
            addCityToMap(dist.getFromCity(), dist.getToCity(), dist.getDistance());
            addCityToMap(dist.getToCity(), dist.getFromCity(), dist.getDistance());
        }
    }

    public void addItems(List<Distance> items) {
        items.forEach(dist -> {
            addItem(dist);
            citySet.add(dist.getFromCity());
            citySet.add(dist.getToCity());
        });
    }

    private void addCityToMap(City fCity, City tCity, double dist) {
        Crawler cityDistance = new Crawler(tCity, dist);
        if (cityMap.containsKey(fCity)) {
            ((HashSet)cityMap.get(fCity)).add(cityDistance);
        } else {
            HashSet citySet = new HashSet<Crawler>();
            citySet.add(cityDistance);
            cityMap.put(fCity, citySet);
        }
    }

    public HashMap getMap() {
        return cityMap;
    }

    public  void printMap() {
        for (Object obj: cityMap.entrySet()) {
            Map.Entry mapItem = (Map.Entry)obj;
            System.out.print(mapItem.getKey() + " -> ");
            ((HashSet)mapItem.getValue()).forEach(item -> {
                Crawler crawler = (Crawler)item;
                System.out.print(crawler.getCity() + ":");
                System.out.printf("%.0f ", crawler.getDistance());
                    });
            System.out.println();
        }
    }

    public Set<City> getCitySet() {
        return citySet;
    }

    public HashMap getCityMap() {
        return cityMap;
    }
}
