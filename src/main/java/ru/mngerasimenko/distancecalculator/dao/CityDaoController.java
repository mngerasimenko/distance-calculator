package ru.mngerasimenko.distancecalculator.dao;

import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;
import ru.mngerasimenko.distancecalculator.settings.Settings;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CityDaoController extends DaoController<City, Integer> {

    private final String FIND_CITY = "SELECT * FROM dc_city WHERE UPPER(city_name) LIKE UPPER(?);";
    private final String GET_CITY = "SELECT * FROM dc_city WHERE city_id = ?;";
    private final String INSERT_CITY = "INSERT INTO dc_city (city_name, latitude, longitude)" +
            "VALUES (?, ?, ?);";
    private final String GET_ALL = "SELECT * FROM dc_city LIMIT ?;";

    @Override
    public List<City> findItem(String pattern) throws DaoException, InvalidCoordinateFormatException {
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
            rs.close();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return cityList;
    }

    @Override
    public City getItem(Integer id) throws DaoException, InvalidCoordinateFormatException {
        City city = null;
        try (Connection con = getConnection();
             PreparedStatement stmp = con.prepareStatement(GET_CITY)) {

            stmp.setInt(1, id);
            ResultSet rs = stmp.executeQuery();
            if (rs.next()) {
                city = new City(rs.getInt("city_id"), rs.getString("city_name"),
                        rs.getString("latitude"), rs.getString("longitude"));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return city;
    }

    @Override
    public Integer insertItem(City item) throws DaoException {
        Integer result = -1;
        try (Connection con = getConnection();
             PreparedStatement stmp = con.prepareStatement(INSERT_CITY, new String[]{"city_id"})) {

            stmp.setString(1, item.getCity_name());
            stmp.setString(2, item.getLatitude());
            stmp.setString(3, item.getLongitude());
            stmp.executeUpdate();

            ResultSet resultSet = stmp.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<City> getAll() throws DaoException, InvalidCoordinateFormatException{
        List<City> cityList = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmp = con.prepareStatement(GET_ALL)) {

            stmp.setInt(1, Integer.parseInt(Settings.getProperty(Settings.DB_LIMIT)));
            ResultSet rs = stmp.executeQuery();
            while (rs.next()) {

                City city = new City(rs.getInt("city_id"), rs.getString("city_name"),
                        rs.getString("latitude"), rs.getString("longitude"));
                cityList.add(city);

            }
            rs.close();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return cityList;
    }
}
