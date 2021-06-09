package ru.mngerasimenko.distancecalculator;

import ru.mngerasimenko.distancecalculator.calculator.MatrixDistance;
import ru.mngerasimenko.distancecalculator.dao.CityDaoController;
import ru.mngerasimenko.distancecalculator.dao.DaoController;
import ru.mngerasimenko.distancecalculator.dao.DistanceDaoController;
import ru.mngerasimenko.distancecalculator.dao.SimpleConnection;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.domain.Distance;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;
import ru.mngerasimenko.distancecalculator.storage.CityStorage;
import ru.mngerasimenko.distancecalculator.storage.DistanceStorage;
import ru.mngerasimenko.distancecalculator.view.MainView;

import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class DistanceCalculator {


    //private static CityStorage cityStorage = new CityStorage();



    public static void main(String[] args) throws CityException, DaoException {

        DistanceStorage distanceStorage = new DistanceStorage();
        DistanceDaoController ddc = new DistanceDaoController(new SimpleConnection());

        distanceStorage.addItems(ddc.getAll());

        Set<City> citySet = distanceStorage.getCitySet();
        List cityList =  Arrays.asList(citySet.toArray());
        City fromCity = (City)cityList.get(1);
        City toCity = (City)cityList.get(0);

        System.out.println(fromCity);
        System.out.println(toCity);
        distanceStorage.addItems(ddc.getAll());

        MatrixDistance matrixDistance = new MatrixDistance(fromCity, toCity);
        double dist = matrixDistance.getDistance();
        System.out.println(dist);

    }
}
