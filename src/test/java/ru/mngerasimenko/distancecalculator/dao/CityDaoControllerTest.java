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

    private static CityDaoController cityDC;

    @BeforeClass
    public static void initTest() throws Exception{
        DBInit.initDB();
        cityDC = new CityDaoController(new SimpleConnection());
    }

    @Test
    public void findItem() throws CityException, DaoException {
        List<City> cityList = cityDC.findItem("sa");
        Assert.assertTrue(cityList.size() == 2);
    }

    @Test
    public void getItem() throws CityException, DaoException {
        City city = cityDC.getItem(1);
        Assert.assertTrue(city.getCityName().equals("Moscow"));
    }

    @Test(expected = NullPointerException.class)
    public void getItemError() throws CityException, DaoException {
        City city = cityDC.getItem(20);
        Assert.assertTrue(city.getCityName().equals("Moscow"));
    }

    @Test
    public void insertItem() throws CityException, DaoException {
        City city = new City("TestCity","12.232N","34.545E");
        int ind = cityDC.insertItem(city);
        Assert.assertTrue(ind == 9);
    }
    @Test(expected = CityException.class)
    public void insertItemError1() throws CityException {
        City city = new City("","12.232N","34.545E");
    }
    @Test(expected = InvalidCoordinateFormatException.class)
    public void insertItemError2() throws CityException {
        City city = new City("TestCity","12.232T","34.545E");
    }
    @Test(expected = InvalidCoordinateFormatException.class)
    public void insertItemError3() throws CityException {
        City city = new City("TestCity","343434","34.545E");
    }
    @Test(expected = InvalidCoordinateFormatException.class)
    public void insertItemError4() throws CityException {
        City city = new City("TestCity","32.232W","34.545S");
    }
    @Test(expected = InvalidCoordinateFormatException.class)
    public void insertItemError5() throws CityException {
        City city = new City("TestCity","","34.545W");
    }
    @Test(expected = DaoException.class)
    public void insertItemError6() throws CityException, DaoException {
        City city = new City("Moscow","55.7522N","37.6156E");
        cityDC.insertItem(city);
    }


    @Test
    public void getAll() throws CityException, DaoException {
        List<City> cityList = cityDC.getAll();
        Assert.assertTrue(cityList.size() == 8);
    }

    @Test
    public void editCityName() throws CityException, DaoException {
        City city = cityDC.getItem(1);
        int result = cityDC.editCityName(city, "Orenburg");
        Assert.assertTrue(result == 1);
    }
    @Test(expected = CityException.class)
    public void editCityName1() throws CityException, DaoException {
        City city = new City("TestCity","23.234s","43.3555w");
        cityDC.editCityName(city, "TestCity");
    }

    @Test(expected = DaoException.class)
    public void deleteCityError() throws DaoException {
        cityDC.deleteCity(3);
    }
    @Test()
    public void deleteCity() throws DaoException {
        cityDC.deleteCity(7);
    }

}