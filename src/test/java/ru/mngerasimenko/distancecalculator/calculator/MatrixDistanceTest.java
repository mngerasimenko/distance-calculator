package ru.mngerasimenko.distancecalculator.calculator;

import org.junit.Before;
import org.junit.Test;
import ru.mngerasimenko.distancecalculator.dao.CityDaoController;
import ru.mngerasimenko.distancecalculator.dao.DistanceDaoController;
import ru.mngerasimenko.distancecalculator.dao.SimpleConnection;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.storage.DistanceStorage;

import java.util.Set;

import static org.junit.Assert.*;

public class MatrixDistanceTest {

    DistanceDaoController ddc = new DistanceDaoController(new SimpleConnection());
    CityDaoController cdc = new CityDaoController(new SimpleConnection());
    DistanceStorage distanceStorage;
    MatrixDistance matrixDistance;
    Set<City> citySet;

    @Before
    public void setUp() throws Exception {
        distanceStorage = new DistanceStorage();
        distanceStorage.addItems(ddc.getAll());
        //citySet = distanceStorage.getCitySet();
        matrixDistance = new MatrixDistance(cdc.getItem(1), cdc.getItem(2));
    }

    @Test
    public void getDistance() throws CityException, DaoException {
        distanceStorage = new DistanceStorage();
        distanceStorage.addItems(ddc.getAll());
        //citySet = distanceStorage.getCitySet();
        matrixDistance = new MatrixDistance(cdc.getItem(2), cdc.getItem(4));
        System.out.println(matrixDistance.getDistance());
    }
}