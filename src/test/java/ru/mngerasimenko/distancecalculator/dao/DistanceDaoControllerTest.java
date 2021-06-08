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


public class DistanceDaoControllerTest {

    private static CityDaoController cityDC;
    private static DistanceDaoController distanceDC;

    @BeforeClass
    public static void initTest() throws Exception {
        DBInit.initDB();
        cityDC = new CityDaoController(new SimpleConnection());
        distanceDC =new DistanceDaoController(new SimpleConnection());
    }

    @Test
    public void findItem() throws CityException, DaoException {
        List<Distance> distancesList = distanceDC.findItem("mo");
        Assert.assertTrue(distancesList.size() == 2);
    }

    @Test
    public void getItem() throws CityException, DaoException {
        Distance distance = distanceDC.getItem(1);
        Assert.assertTrue(distance.getFromCity().getCityName().equals("Moscow"));
    }

    @Test
    public void insertItem() throws CalculateException, DaoException, CityException {

        City fromCity = cityDC.getItem(5);
        City toCity = cityDC.getItem(8);
        Distance dist = new Distance(fromCity, toCity);
        int ind = distanceDC.insertItem(dist);
        Assert.assertTrue(ind == 5);
    }

    @Test(expected = CalculateException.class)
    public void insertItemError() throws CalculateException, DaoException, CityException {
        City fromCity = cityDC.getItem(5);
        City toCity = cityDC.getItem(5);
        Distance dist = new Distance(fromCity, toCity);
        int ind = distanceDC.insertItem(dist);
        Assert.assertTrue(ind == 5);
    }

    @Test
    public void getAll() throws CityException, DaoException {
        List<Distance> distList = distanceDC.getAll();
        Assert.assertTrue(distList.size() == 4);
    }

    @Test
    public void getExisting() {
        int result = 0;
        try {
            City fromCity = cityDC.getItem(1);
            City toCity = cityDC.getItem(2);
            result = distanceDC.getExisting(fromCity,toCity);
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (CityException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(result == 1);
    }

    @Test
    public void getExisting1() {
        int result = 0;
        try {
            City fromCity = cityDC.getItem(7);
            City toCity = cityDC.getItem(1);
            result = distanceDC.getExisting(fromCity,toCity);
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (CityException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(result == -1);
    }

}