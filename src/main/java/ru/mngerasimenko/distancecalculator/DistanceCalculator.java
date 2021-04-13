package ru.mngerasimenko.distancecalculator;

import ru.mngerasimenko.distancecalculator.calculator.CrowFlightDistance;
import ru.mngerasimenko.distancecalculator.citystorage.City;
import ru.mngerasimenko.distancecalculator.citystorage.CityStorage;
import ru.mngerasimenko.distancecalculator.distancestorage.Distance;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;
import ru.mngerasimenko.distancecalculator.settings.Settings;

public class DistanceCalculator {

    private static CityStorage cityStorage = new CityStorage();

    public static void main(String[] args) {


            City moscow = new City("Moscow", "55.7522N", "37.6156E");
            City samara = new City("Samara", "53.2001N", "50.15E");
            City habarovsk = new City("Habarovsk", "48.4827N", "135.084E");
//        City tura = new City("Tura", 64.28, 100.22);
        City sydney = new City("Sydney", "33.874S", "151.213E");

            //   City tura = new City("Tura", 1.121387, 1.748224);
            //   City sydney = new City("Sydney", -0.590913, 2.637827);



        cityStorage.addCity(moscow);
        cityStorage.addCity(samara);
        cityStorage.addCity(habarovsk);

        CrowFlightDistance cFDistance = new CrowFlightDistance(samara, sydney);
        Distance d1 = new Distance(cFDistance);
        System.out.println(cFDistance.getDistance());


    }
}
