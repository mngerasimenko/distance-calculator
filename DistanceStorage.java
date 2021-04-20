package ru.mngerasimenko.distancecalculator.storage;


import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.domain.Distance;

import java.util.*;

public class DistanceStorage{

    HashMap<City, HashSet<DistanceToCity>> cityMap;


    public void addDistance(Distance distance) {

        if (cityMap == null) {
            cityMap = new HashMap<City, HashSet<DistanceToCity>>();
        }
        addToMap(distance.getFromCity(), distance.getToCity(), distance.getDistance());
        addToMap(distance.getToCity(), distance.getFromCity(), distance.getDistance());

//        for (Map.Entry<City, HashSet<DistanceToCity>> pair: cityMap.entrySet()) {
//
//            System.out.println(pair.getKey() + " --> " + pair.getValue());
//
//        }
    }

    private void addToMap(City fromCity, City toCity, Double distance) {
        DistanceToCity distanceToCity = new DistanceToCity(toCity, distance);
        if (cityMap.containsKey(fromCity)) {
            cityMap.get(fromCity).add(distanceToCity);
        } else {
            HashSet<DistanceToCity> distancesSet = new HashSet<>();
            distancesSet.add(distanceToCity);
            cityMap.put(fromCity, distancesSet);
        }
    }

    public HashMap<City, HashSet<DistanceToCity>> getAll() {
        return cityMap;
    }

    public static class DistanceToCity<City, Double> {

        public final City key;
        public final Double value;

        public DistanceToCity(City key, Double value) {
            this.key = key;
            this.value = value;
        }

        public City getKey() {
            return key;
        }

        public Double getValue() {
            return value;
        }

//        public boolean equals(Object o) {
//            return o instanceof DistanceToCity && Objects.equals(key, ((DistanceToCity<?,?>)o).key) && Objects.equals(value, ((DistanceToCity<?,?>)o).value);
//        }
//
//        public int hashCode() {
//            return 31 * Objects.hashCode(key) + Objects.hashCode(value);
//        }

        public String toString() {
            return key + " " + value;
        }
    }


}
