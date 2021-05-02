package ru.mngerasimenko.distancecalculator.dao;

import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.domain.Distance;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DistanceDaoController extends DaoController<Distance, Integer>{

    private final String FIND_DISTANCE = "SELECT * FROM dc_distance WHERE (UPPER(from_city) LIKE UPPER(?)) OR (UPPER(to_city) LIKE UPPER(?));";
    private final String INSERT_DISTANCE = "INSERT INTO dc_distance (from_city, to_city, distance)" +
            "VALUES (?, ?, ?);";

    @Override
    public List<Distance> findItem(String cityName) throws DaoException, InvalidCoordinateFormatException {
        List<Distance> distanceList = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmp = con.prepareStatement(FIND_DISTANCE)) {

            City fromCity = new CityDaoController().findItem(cityName).get(0);
            stmp.setInt(1, fromCity.getCity_id());
            stmp.setInt(2, fromCity.getCity_id());
            ResultSet rs = stmp.executeQuery();
            while (rs.next()) {
                fromCity = new CityDaoController().getItem(rs.getInt("from_city"));
                City toCity = new CityDaoController().getItem(rs.getInt("to_city"));
                Distance distance = new Distance(rs.getInt("distance_id"), fromCity, toCity);
                distanceList.add(distance);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return distanceList;
    }

    @Override
    public Distance getItem(Integer id) throws DaoException, InvalidCoordinateFormatException {
        return null;
    }

    @Override
    public Integer insertItem(Distance item) throws DaoException {
        Integer result = -1;
        try (Connection con = getConnection();
             PreparedStatement stmp = con.prepareStatement(INSERT_DISTANCE, new String[]{"distance_id"})) {

            stmp.setInt(1, item.getFromCity().getCity_id());
            stmp.setInt(2, item.getToCity().getCity_id());
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

}
