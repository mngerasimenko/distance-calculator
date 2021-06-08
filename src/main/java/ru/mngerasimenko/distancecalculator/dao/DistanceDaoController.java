package ru.mngerasimenko.distancecalculator.dao;

import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.domain.Distance;
import ru.mngerasimenko.distancecalculator.exception.CalculateException;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DistanceDaoController extends DaoController<Distance, Integer> {

    private final String FIND_DISTANCE = "SELECT d.distance_id, fc.*, tc.*, d.distance " +
            "FROM dc_distance AS d " +
            "INNER JOIN dc_city AS fc ON fc.city_id = d.from_city " +
            "INNER JOIN dc_city AS tc ON tc.city_id = d.to_city " +
            "WHERE (UPPER(fc.city_name) LIKE UPPER(?)) OR (UPPER(tc.city_name) LIKE UPPER(?));";
    private final String INSERT_DISTANCE = "INSERT INTO dc_distance (from_city, to_city, distance)" +
            "VALUES (?, ?, ?);";
    private final String GET_DISTANCE = "SELECT d.distance_id, fc.*, tc.*, d.distance " +
            "FROM dc_distance AS d " +
            "    INNER JOIN dc_city AS fc ON fc.city_id = d.from_city " +
            "    INNER JOIN dc_city AS tc ON tc.city_id = d.to_city " +
            "WHERE d.distance_id = ?;";
    private final String GET_ALL = "SELECT d.distance_id, d.distance, fc.*, tc.* " +
            "FROM dc_distance AS d " +
            "INNER JOIN dc_city AS fc ON d.from_city = fc.city_id " +
            "INNER JOIN dc_city AS tc ON d.to_city = tc.city_id;";
    private final String GET_EXISTING = "SELECT distance_id from dc_distance " +
            "WHERE (to_city = ? or to_city = ?) and (from_city = ? or from_city = ?);";

    public DistanceDaoController() {
        super();
    }
    public DistanceDaoController(ConnectionBuilder connectionBuilder) {
        super(connectionBuilder);
    }

    @Override
    public List<Distance> findItem(String pattern) throws DaoException, CityException {
        List<Distance> distanceList = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmp = con.prepareStatement(FIND_DISTANCE)) {

            stmp.setString(1, "%" + pattern + "%");
            stmp.setString(2, "%" + pattern + "%");
            ResultSet rs = stmp.executeQuery();
            while (rs.next()) {
                City fromCity = new City(rs.getInt("fc.city_id"), rs.getString("fc.city_name"),
                        rs.getString("fc.latitude"), rs.getString("fc.longitude"));
                City toCity = new City(rs.getInt("tc.city_id"), rs.getString("tc.city_name"),
                        rs.getString("tc.latitude"), rs.getString("tc.longitude"));
                Distance distance = new Distance(
                        rs.getInt("distance_id"), fromCity, toCity, rs.getDouble("d.distance")
                );
                distanceList.add(distance);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return distanceList;
    }

    @Override
    public Distance getItem(Integer id) throws DaoException, CityException {
        Distance distance = null;
        try (Connection con = getConnection();
             PreparedStatement stmp = con.prepareStatement(GET_DISTANCE)) {

            stmp.setInt(1, id);
            ResultSet rs = stmp.executeQuery();
            if (rs.next()) {
                City fromCity = new City(rs.getInt("fc.city_id"), rs.getString("fc.city_name"),
                        rs.getString("fc.latitude"), rs.getString("fc.longitude"));
                City toCity = new City(rs.getInt("tc.city_id"), rs.getString("tc.city_name"),
                        rs.getString("tc.latitude"), rs.getString("tc.longitude"));
                distance = new Distance(rs.getInt("distance_id"), fromCity, toCity, rs.getDouble("distance"));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return distance;
    }

    @Override
    public Integer insertItem(Distance item) throws DaoException, CalculateException {
        Integer result = -1;
        int idFromCity = item.getFromCity().getCityId();
        int idToCity = item.getToCity().getCityId();
        if (idFromCity == idToCity) throw new CalculateException("Cities are equal");

        try (Connection con = getConnection();
             PreparedStatement stmp = con.prepareStatement(INSERT_DISTANCE, new String[]{"distance_id"})) {

            stmp.setInt(1, item.getFromCity().getCityId());
            stmp.setInt(2, item.getToCity().getCityId());
            stmp.setDouble(3, item.getDistance());
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
    public List<Distance> getAll() throws DaoException, CityException {
        List<Distance> distanceList = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmp = con.prepareStatement(GET_ALL)) {

            ResultSet rs = stmp.executeQuery();
            while (rs.next()) {
                City fromCity = new City(rs.getInt("fc.city_id"), rs.getString("fc.city_name"),
                        rs.getString("fc.latitude"), rs.getString("fc.longitude"));
                City toCity = new City(rs.getInt("tc.city_id"), rs.getString("tc.city_name"),
                        rs.getString("tc.latitude"), rs.getString("tc.longitude"));
                Distance distance = new Distance(
                        rs.getInt("distance_id"), fromCity, toCity, rs.getDouble("d.distance")
                );
                distanceList.add(distance);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return distanceList;
    }

    public int getExisting(City fromCity, City toCity) throws DaoException, CityException {
        int result = -1;
        if(fromCity == null || toCity == null) throw new CityException("City is empty");
        try (Connection con = getConnection();
             PreparedStatement stmp = con.prepareStatement(GET_EXISTING)) {
               int id_1 = fromCity.getCityId();
               int id_2 = toCity.getCityId();
               stmp.setInt(1, id_1);
               stmp.setInt(2, id_2);
               stmp.setInt(3, id_1);
               stmp.setInt(4, id_2);
               ResultSet rs = stmp.executeQuery();
               if (rs.next()) {
                   result = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }
}
