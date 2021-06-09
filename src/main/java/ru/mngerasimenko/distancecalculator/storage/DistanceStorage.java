package ru.mngerasimenko.distancecalculator.storage;


import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.domain.Distance;
import ru.mngerasimenko.distancecalculator.domain.Сrawler;

import java.util.*;

public class DistanceStorage extends Storage{

    public static HashMap cityMap;
    private Set<City> citySet = new HashSet<>();

    static {
        cityMap = new HashMap<City, HashSet<Сrawler>>();
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
        Сrawler cityDistance = new Сrawler(tCity, dist);
        if (cityMap.containsKey(fCity)) {
            ((HashSet)cityMap.get(fCity)).add(cityDistance);
        } else {
            HashSet citySet = new HashSet<Сrawler>();
            citySet.add(cityDistance);
            cityMap.put(fCity, citySet);
        }
    }

    public HashMap getMap() {
        return cityMap;
    }

    public static void printMap() {
        for (Object obj: cityMap.entrySet()) {
            Map.Entry mapItem = (Map.Entry)obj;
            System.out.print(mapItem.getKey() + " -> ");
            ((HashSet)mapItem.getValue()).forEach(item -> {
                Сrawler сrawler = (Сrawler)item;
                System.out.print(сrawler.getCity() + ":");
                System.out.printf("%.0f ", сrawler.getDistance());
                    });
            System.out.println();
        }
    }

    public Set<City> getCitySet() {
        return citySet;
    }
}
