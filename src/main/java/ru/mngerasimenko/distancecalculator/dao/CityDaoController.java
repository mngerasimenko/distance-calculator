package ru.mngerasimenko.distancecalculator.dao;

import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.settings.Settings;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class CityDaoController extends DaoController<City, Integer> {

    private final String FIND_CITY = "SELECT * FROM dc_city " +
            "WHERE UPPER(city_name) LIKE UPPER(?);";
    private final String GET_CITY = "SELECT * FROM dc_city WHERE city_id = ?;";
    private final String INSERT_CITY = "INSERT INTO dc_city (city_name, latitude, longitude)" +
            "VALUES (?, ?, ?);";
    private final String GET_ALL = "SELECT * FROM dc_city LIMIT ?;";
    private final String EDIT_CITY_NAME = "UPDATE dc_city SET city_name = ? WHERE city_id = ?;";
    private final String EDIT_CITY = "UPDATE dc_city SET city_name = ?, latitude = ?, longitude = ? WHERE city_id = ?;";
    private final String DELETE_CITY = "DELETE FROM dc_city WHERE city_id = ?;";

    public CityDaoController() {
        super();
    }

    public CityDaoController(ConnectionBuilder connectionBuilder) {
        super(connectionBuilder);
    }

    public int editCityName(City city, String name) throws DaoException, CityException {
        Integer result = -1;
        if (city == null || city.getCityId() == 0) {
            throw new CityException(Settings.ERROR_CITY_2);
        }
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(EDIT_CITY_NAME);) {

            stmt.setString(1, name);
            stmt.setInt(2, city.getCityId());
            result = stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    public int editCity(City city, String name, String latitude, String longitude) throws DaoException, CityException {
        new City(name, latitude, longitude);
        Integer result = -1;
        if (city == null || city.getCityId() == 0) {
            throw new CityException(Settings.ERROR_CITY_2);
        }
        try (Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(EDIT_CITY);) {
            stmt.setString(1, name);
            stmt.setString(2, latitude);
            stmt.setString(3, longitude);
            stmt.setInt(4, city.getCityId());
            result = stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<City> findItem(String pattern) throws DaoException, CityException {
        List<City> cityList = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(FIND_CITY)) {

            stmt.setString(1, "%" + pattern + "%");
            ResultSet rs = stmt.executeQuery();
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
    public City getItem(Integer id) throws DaoException, CityException {
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
             PreparedStatement stmt = con.prepareStatement(INSERT_CITY, new String[]{"city_id"})) {

            stmt.setString(1, item.getCityName());
            stmt.setString(2, item.getLatitude());
            stmt.setString(3, item.getLongitude());
            stmt.executeUpdate();

            ResultSet resultSet = stmt.getGeneratedKeys();
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
    public List<City> getAll() throws DaoException, CityException {
        List<City> cityList = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_ALL)) {

            stmt.setInt(1, Integer.parseInt(Settings.getProperty(Settings.DB_LIMIT)));
            ResultSet rs = stmt.executeQuery();
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

    public void deleteCity(int cityId) throws DaoException {

        if (cityId == 0) {
            throw new DaoException("City id is '0'");
        }
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_CITY);) {
            stmt.setInt(1, cityId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

    }
}
