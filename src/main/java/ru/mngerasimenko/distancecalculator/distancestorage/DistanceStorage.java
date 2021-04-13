package ru.mngerasimenko.distancecalculator.distancestorage;

import ru.mngerasimenko.distancecalculator.distancestorage.Distance;

import java.util.List;

public class DistanceStorage {

    private List<Distance> distanceList;

    public void addDistance(Distance distance) {
        distanceList.add(distance);
    }
}
