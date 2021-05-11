package ru.mngerasimenko.distancecalculator.dao;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;

import java.util.List;

public class CityDaoControllerTest {

    @BeforeClass
    public static void initTest() throws Exception {
        DBInit.initDB();
    }

    @Test
    public void findItem() throws CityException, DaoException {
        List<City> cityList = new CityDaoController().findItem("а");
        Assert.assertTrue(cityList.size() == 2);
    }

    @Test
    public void getItem() throws CityException, DaoException {
        City city = new CityDaoController().getItem(1);
        Assert.assertTrue(city.getCity_name().equals("Москва"));
    }

    @Test(expected = NullPointerException.class)
    public void getItemError() throws CityException, DaoException {
        City city = new CityDaoController().getItem(20);
        Assert.assertTrue(city.getCity_name().equals("Moscow"));
    }

    @Test
    public void insertItem() throws CityException, DaoException {
        City city = new City("TestCity","12.232N","34.545E");
        int ind = new CityDaoController().insertItem(city);
        Assert.assertTrue(ind == 9);
    }
    @Test(expected = CityException.class)
    public void insertItemError1() throws CityException, DaoException {
        City city = new City("","12.232N","34.545E");
        int ind = new CityDaoController().insertItem(city);
    }
    @Test(expected = InvalidCoordinateFormatException.class)
    public void insertItemError2() throws CityException, DaoException {
        City city = new City("TestCity","12.232T","34.545E");
        int ind = new CityDaoController().insertItem(city);
    }
    @Test(expected = InvalidCoordinateFormatException.class)
    public void insertItemError3() throws CityException, DaoException {
        City city = new City("TestCity","343434","34.545E");
        int ind = new CityDaoController().insertItem(city);
    }
    @Test(expected = InvalidCoordinateFormatException.class)
    public void insertItemError4() throws CityException, DaoException {
        City city = new City("TestCity","32.232W","34.545S");
        int ind = new CityDaoController().insertItem(city);
    }
    @Test(expected = InvalidCoordinateFormatException.class)
    public void insertItemError5() throws CityException, DaoException {
        City city = new City("TestCity","","34.545W");
        int ind = new CityDaoController().insertItem(city);
    }

    @Test
    public void getAll() throws CityException, DaoException {
        List<City> cityList = new CityDaoController().getAll();
        Assert.assertTrue(cityList.size() == 8);
    }


    @Test
    public void editCityName() throws CityException, DaoException {
        CityDaoController cdc = new CityDaoController();
        City city = cdc.getItem(1);
        int result = cdc.editCityName(city, "Вася");
        Assert.assertTrue(result == 1);
    }
    @Test(expected = CityException.class)
    public void editCityName1() throws CityException, DaoException {
        CityDaoController cdc = new CityDaoController();
        City city = new City("TestCity","23.234s","43.3555w");
        cdc.editCityName(city, "TestCity");
    }

}