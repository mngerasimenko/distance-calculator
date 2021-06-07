package ru.mngerasimenko.distancecalculator;

import ru.mngerasimenko.distancecalculator.dao.CityDaoController;
import ru.mngerasimenko.distancecalculator.dao.DaoController;
import ru.mngerasimenko.distancecalculator.dao.DistanceDaoController;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.domain.Distance;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;
import ru.mngerasimenko.distancecalculator.storage.CityStorage;
import ru.mngerasimenko.distancecalculator.storage.DistanceStorage;
import ru.mngerasimenko.distancecalculator.view.MainView;


public class DistanceCalculator {

    private static CityStorage cityStorage = new CityStorage();
    private static DistanceStorage distanceStorage = new DistanceStorage();

    public static void main(String[] args) throws CityException {
       // MainView mainView = new MainView();

    }
}
