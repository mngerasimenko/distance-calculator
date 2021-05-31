package ru.mngerasimenko.distancecalculator.web;

import ru.mngerasimenko.distancecalculator.calculator.CrowFlightDistance;
import ru.mngerasimenko.distancecalculator.dao.CityDaoController;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/cfDistance")
public class CFDistanceService {

    @GET
    public String getCFDistance() {
        CityDaoController cityDC = new CityDaoController();
        String result = null;
        try {
            City fromCity = cityDC.getItem(1);
            City toCity = cityDC.getItem(2);
            result =  (fromCity.toString() + toCity.toString() + new CrowFlightDistance(fromCity, toCity).getDistance());
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (CityException e) {
            e.printStackTrace();
        }
        return result;
    }
}
