package ru.mngerasimenko.distancecalculator.dao;

import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class CityDaoControllerTest {

    @BeforeClass
    public static void initTest() throws Exception {
        URL urlCreate = CityDaoController.class.getClassLoader()
                .getResource("distance_calculator.sql");
        List<String> strCreate = Files.readAllLines(Paths.get(urlCreate.toURI()));
        URL urlData = CityDaoController.class.getClassLoader()
                .getResource("data_distance_calculator.sql");
        List<String> strData = Files.readAllLines(Paths.get(urlData.toURI()));

        String[] sqlStrings = (String.join(" ", strCreate)
                + String.join(" ", strData)).split(";");

        try (Connection con = ConnectionBuilder.getConnection()) {
            Statement stmt = con.createStatement();
            for(String sql : sqlStrings) {
                stmt.executeUpdate(sql);
            }
        }
    }

    @Test
    public void testExample() {
        System.out.println("TEST1");
    }
    @Test
    public void testExampl1() {
        System.out.println("TEST2");
    }
    @Test
    public void testExampl2() {
        System.out.println("TEST3");
    }

}