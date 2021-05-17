package ru.mngerasimenko.distancecalculator.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBInit {

    public static void initDB() throws Exception {
        URL urlCreate = CityDaoController.class.getClassLoader()
                .getResource("distance_calculator.sql");
        List<String> strCreate = Files.readAllLines(Paths.get(urlCreate.toURI()));
        URL urlData = CityDaoController.class.getClassLoader()
                .getResource("data_distance_calculator.sql");
        List<String> strData = Files.readAllLines(Paths.get(urlData.toURI()));

        String[] sqlStrings = (String.join(" ", strCreate)
                + String.join(" ", strData)).split(";");

        try (Connection con =  (new SimpleConnection()).getConnection()) {
            Statement stmt = con.createStatement();
            for(String sql : sqlStrings) {
                stmt.executeUpdate(sql);
            }
        }
    }
}
