package ru.mngerasimenko.distancecalculator.storage;


import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.domain.Distance;

import java.util.*;

public class DistanceStorage extends Storage{

    private static HashMap<City, HashSet<CityDistance>> cityMap;

    static {
        cityMap = new HashMap<City, HashSet<CityDistance>>();
    }

    @Override
    public void addItem(Object item) {
        if (item instanceof Distance) {
            Distance dist = (Distance) item;
            addCityToMap(dist.getFromCity(), dist.getToCity(), dist.getDistance());
            addCityToMap(dist.getToCity(), dist.getFromCity(), dist.getDistance());
        }
    }

    private void addCityToMap(City fCity, City tCity, double dist) {
        CityDistance cityDistance = new CityDistance(tCity, dist);
        if (cityMap.containsKey(fCity)) {
            cityMap.get(fCity).add(cityDistance);
        } else {
            HashSet citySet = new HashSet<CityDistance>();
            citySet.add(cityDistance);
            cityMap.put(fCity, citySet);
        }
    }

    public HashMap<City, HashSet<CityDistance>> getMap() {
        return cityMap;
    }

    public void printMap() {
        for (Map.Entry<City, HashSet<CityDistance>> distance: cityMap.entrySet()) {
            System.out.println(distance.getKey() +" "+ distance.getValue());
        }
    }

    public double searchPath(City fromCity, City toCity) {

        return 0;
    }

    public static class CityDistance {
        private City toCity;
        double distance;

        public CityDistance(City toCity, double distance) {
            this.toCity = toCity;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return toCity + "-" + (int)distance;
        }
    }

}
