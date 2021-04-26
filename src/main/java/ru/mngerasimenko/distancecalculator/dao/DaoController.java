package ru.mngerasimenko.distancecalculator.dao;

import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.settings.Settings;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DaoController {

    private final String GET_CITY = "SELECT * FROM dc_city WHERE UPPER(city_name) LIKE UPPER(?);";

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(Settings.DB_URL, Settings.DB_LOGIN, Settings.DB_PASSWORD);
        return  connection;
    }

    public List<City> findCity(String pattern) throws DaoException {
        List<City> cityList = new LinkedList<>();
        try(Connection con = getConnection();
            PreparedStatement stmp = con.prepareStatement(GET_CITY)) {

            stmp.setString(1,"%" + pattern + "%");
            ResultSet rs = stmp.executeQuery();
            while (rs.next()) {
                City city = new City(rs.getLong("city_id"), rs.getString("city_name"),
                        rs.getString("latitude"), rs.getString("longitude"));
                cityList.add(city);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return cityList;
    }
}
