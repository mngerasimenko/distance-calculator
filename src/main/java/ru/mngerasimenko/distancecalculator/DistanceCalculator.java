package ru.mngerasimenko.distancecalculator;

import ru.mngerasimenko.distancecalculator.calculator.CrowFlightDistance;
import ru.mngerasimenko.distancecalculator.calculator.MatrixDistance;
import ru.mngerasimenko.distancecalculator.dao.DaoController;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.settings.Settings;
import ru.mngerasimenko.distancecalculator.storage.CityStorage;
import ru.mngerasimenko.distancecalculator.domain.Distance;
import ru.mngerasimenko.distancecalculator.storage.DistanceStorage;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class DistanceCalculator {

    private static CityStorage cityStorage = new CityStorage();
    private static DistanceStorage distanceStorage = new DistanceStorage();

    public static void main(String[] args) throws SQLException {
//        Connection connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/distance_calculator?useUnicode=true" +
//                        "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
//                "root", "root");
        Connection connection = DriverManager.getConnection(Settings.DB_URL, Settings.DB_LOGIN, Settings.DB_PASSWORD);
        Statement stmp = connection.createStatement();
        ResultSet rs = stmp.executeQuery("SELECT * FROM dc_city");
        while (rs.next()) {
            System.out.println(rs.getInt("city_id") + ": " + rs.getString("city_name") +
                    " " + rs.getString("latitude") + " " + rs.getString("longitude"));
        }

//        stmp.execute("INSERT INTO dc_city (city_name, latitude, longitude) " +
//                "VALUE ('Moscow','55.7522N','37.6156E')," +
//                "('Samara','53.2001N','50.15E')," +
//                "('Habarovsk','48.4827N','135.084E');");

        rs = stmp.executeQuery("SELECT * FROM dc_city");
        while (rs.next()) {
            System.out.println(rs.getInt("city_id") + ": " + rs.getString("city_name") +
                    " " + rs.getString("latitude") + " " + rs.getString("longitude"));
        }


        City moscow = new City("Moscow", "55.7522N", "37.6156E");
        City samara = new City("Samara", "53.2001N", "50.15E");
        City habarovsk = new City("Habarovsk", "48.4827N", "135.084E");
        City tura = new City("Tura", "64.28N", "100.22E");
        City sydney = new City("Sydney", "33.874S", "151.213E");


        cityStorage.addItem(moscow);
        cityStorage.addItem(samara);
        cityStorage.addItem(tura);
        cityStorage.addItem(habarovsk);
        cityStorage.addItem(sydney);


        distanceStorage.addItem(new Distance(moscow, samara));
        distanceStorage.addItem(new Distance(moscow, habarovsk));
        distanceStorage.addItem(new Distance(samara, habarovsk));
        distanceStorage.addItem(new Distance(habarovsk, sydney));
        distanceStorage.addItem(new Distance(tura, sydney));
        distanceStorage.addItem(new Distance(tura, habarovsk));
        distanceStorage.addItem(new Distance(tura, moscow));

        cityStorage.getAllItem().forEach(System.out::println);
        System.out.println();
        distanceStorage.printMap();
        System.out.println();
        MatrixDistance matrixDistance = new MatrixDistance(moscow, samara);
        System.out.printf("%.0f\n", matrixDistance.getDistance());
        System.out.println(matrixDistance.i);


        DaoController dc = new DaoController();
        try {
            for (City city : dc.findCity("s")) {
                System.out.println(city);
            }
        } catch (DaoException ex) {
            ex.printStackTrace();
        }

    }
}
