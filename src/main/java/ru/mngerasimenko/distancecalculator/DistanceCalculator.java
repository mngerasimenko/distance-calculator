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




public class DistanceCalculator {

    private static CityStorage cityStorage = new CityStorage();
    private static DistanceStorage distanceStorage = new DistanceStorage();

    public static void main(String[] args) throws CityException {
//        Connection connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/distance_calculator?useUnicode=true" +
//                        "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
//                "root", "root");
//        Connection connection = DriverManager.getConnection(
//                Settings.getProperty(Settings.DB_URL),
//                Settings.getProperty(Settings.DB_LOGIN),
//                Settings.getProperty(Settings.DB_PASSWORD));
//        Statement stmp = connection.createStatement();
//        ResultSet rs = stmp.executeQuery("SELECT * FROM dc_city");
//        while (rs.next()) {
//            System.out.println(rs.getInt("city_id") + ": " + rs.getString("city_name") +
//                    " " + rs.getString("latitude") + " " + rs.getString("longitude"));
//        }
//
////        stmp.execute("INSERT INTO dc_city (city_name, latitude, longitude) " +
////                "VALUE ('Moscow','55.7522N','37.6156E')," +
////                "('Samara','53.2001N','50.15E')," +
////                "('Habarovsk','48.4827N','135.084E');");
//
//        rs = stmp.executeQuery("SELECT * FROM dc_city");
//        while (rs.next()) {
//            System.out.println(rs.getInt("city_id") + ": " + rs.getString("city_name") +
//                    " " + rs.getString("latitude") + " " + rs.getString("longitude"));
//        }
//
//
//        City moscow = new City("Moscow", "55.7522N", "37.6156E");
//        City samara = new City("Samara", "53.2001N", "50.15E");
//        City habarovsk = new City("Habarovsk", "48.4827N", "135.084E");
//        City tura = new City("Tura", "64.28N", "100.22E");
//        City sydney = new City("Sydney", "33.874S", "151.213E");
//
//
//        cityStorage.addItem(moscow);
//        cityStorage.addItem(samara);
//        cityStorage.addItem(tura);
//        cityStorage.addItem(habarovsk);
//        cityStorage.addItem(sydney);
//
//
//        distanceStorage.addItem(new Distance(moscow, samara));
//        distanceStorage.addItem(new Distance(moscow, habarovsk));
//        distanceStorage.addItem(new Distance(samara, habarovsk));
//        distanceStorage.addItem(new Distance(habarovsk, sydney));
//        distanceStorage.addItem(new Distance(tura, sydney));
//        distanceStorage.addItem(new Distance(tura, habarovsk));
//        distanceStorage.addItem(new Distance(tura, moscow));
//
//        cityStorage.getAllItem().forEach(System.out::println);
//        System.out.println();
//        distanceStorage.printMap();
//        System.out.println();
//        MatrixDistance matrixDistance = new MatrixDistance(moscow, samara);
//        System.out.printf("%.0f\n", matrixDistance.getDistance());
//        System.out.println(matrixDistance.i);



        DaoController<City, Integer> cityDC = new CityDaoController();
        DaoController<Distance, Integer> distanceDC = new DistanceDaoController();
        try {
            System.out.println(cityDC.getItem(5));
            System.out.println("---------------------------------------------------------------------------------");
            //System.out.println(dc.insertItem(new City("Kinel","56.333S","65.32124W")));
            for (City city : cityDC.getAll()) {
                System.out.println(city);
            }
            System.out.println("---------------------------------------------------------------------------------");

//            ddc.insertItem(new Distance(cdc.getItem(5), cdc.getItem(1)));
//            ddc.insertItem(new Distance(cdc.getItem(1), cdc.getItem(2)));
//            ddc.insertItem(new Distance(cdc.getItem(1), cdc.getItem(4)));
//            ddc.insertItem(new Distance(cdc.getItem(2), cdc.getItem(3)));
//            ddc.insertItem(new Distance(cdc.getItem(3), cdc.getItem(4)));
//            ddc.insertItem(new Distance(cdc.getItem(4), cdc.getItem(5)));

            for (Distance distance: distanceDC.getAll()) {
                System.out.println(distance);
            }
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println(distanceDC.getItem(3));
        } catch (DaoException ex) {
            ex.printStackTrace();
        } catch (InvalidCoordinateFormatException ex) {
            ex.printStackTrace();
        }
    }

}
