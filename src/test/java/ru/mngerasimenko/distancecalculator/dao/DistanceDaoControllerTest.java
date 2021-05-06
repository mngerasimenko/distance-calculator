package ru.mngerasimenko.distancecalculator.dao;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.domain.Distance;
import ru.mngerasimenko.distancecalculator.exception.CalculateException;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;

import java.util.List;

import static org.junit.Assert.*;

public class DistanceDaoControllerTest {

    @BeforeClass
    public static void initTest() throws Exception {
        DBInit.initDB();
    }

    @Test
    public void findItem() throws CityException, DaoException {
        List<Distance> distancesList = new DistanceDaoController().findItem("mo");
        Assert.assertTrue(distancesList.size() == 2);
    }

    @Test
    public void getItem() throws CityException, DaoException {
        Distance distance = new DistanceDaoController().getItem(1);
        Assert.assertTrue(distance.getFromCity().getCity_name().equals("Moscow"));
    }

    @Test
    public void insertItem() throws CalculateException, DaoException, CityException {
        City fromCity = new CityDaoController().getItem(5);
        City toCity = new CityDaoController().getItem(8);
        Distance dist = new Distance(fromCity, toCity);
        int ind = new DistanceDaoController().insertItem(dist);
        Assert.assertTrue(ind == 5);
    }

    @Test(expected = CalculateException.class)
    public void insertItemError() throws CalculateException, DaoException, CityException {
        City fromCity = new CityDaoController().getItem(5);
        City toCity = new CityDaoController().getItem(5);
        Distance dist = new Distance(fromCity, toCity);
        int ind = new DistanceDaoController().insertItem(dist);
        Assert.assertTrue(ind == 5);
    }

    @Test
    public void getAll() throws CityException, DaoException {
        List<Distance> distList = new DistanceDaoController().getAll();
        Assert.assertTrue(distList.size() == 4);
    }
}