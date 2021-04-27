package ru.mngerasimenko.distancecalculator.dao;

import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.settings.Settings;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CityDaoController extends DaoController<City, Integer> {

    private final String FIND_CITY = "SELECT * FROM dc_city WHERE UPPER(city_name) LIKE UPPER(?);";
    private final String GET_CITY = "SELECT * FROM dc_city WHERE city_id = ?;";

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(Settings.getProperty(Settings.DB_URL),
                Settings.getProperty(Settings.DB_LOGIN), Settings.getProperty(Settings.DB_PASSWORD));
        return connection;
    }

    @Override
    public List<City> findItem(String pattern) throws DaoException {
        List<City> cityList = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmp = con.prepareStatement(FIND_CITY)) {

            stmp.setString(1, "%" + pattern + "%");
            ResultSet rs = stmp.executeQuery();
            while (rs.next()) {
                City city = new City(rs.getInt("city_id"), rs.getString("city_name"),
                        rs.getString("latitude"), rs.getString("longitude"));
                cityList.add(city);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return cityList;
    }

    @Override
    public City getItem(Integer id) throws DaoException {
        City city = null;
        try (Connection con = getConnection();
             PreparedStatement stmp = con.prepareStatement(GET_CITY)) {

            stmp.setInt(1, id);
            ResultSet rs = stmp.executeQuery();
            if (rs.next()) {
                int i = rs.getInt("city_id");
                city = new City(rs.getInt("city_id"), rs.getString("city_name"),
                        rs.getString("latitude"), rs.getString("longitude"));
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return city;
    }
}
