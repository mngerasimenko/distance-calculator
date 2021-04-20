package ru.mngerasimenko.distancecalculator.calculator;

import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.domain.Distance;
import ru.mngerasimenko.distancecalculator.storage.DistanceStorage;
import ru.mngerasimenko.distancecalculator.storage.Storage;

import java.lang.reflect.Array;
import java.util.*;

public class MatrixDistance implements Calculations{

    List<Distance> storage;
    City fromCity;
    City toCity;
    ArrayList cityList;
    //HashMap<City, HashSet<DistanceStorage.DistanceToCity>> cityMap;


    public MatrixDistance(List storage, City fromCity, City toCity) {
        this.storage = storage;
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    @Override
    public double getDistance() {
        HashSet<City> cityPath = new HashSet<>();

        return 0;
    }

    public void searchPath(City fCity, City toCity) {



    }

}
