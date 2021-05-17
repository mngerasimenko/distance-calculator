package ru.mngerasimenko.distancecalculator;

import ru.mngerasimenko.distancecalculator.calculator.CrowFlightDistance;
import ru.mngerasimenko.distancecalculator.dao.CityDaoController;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CrowFlightDistanceServlet", urlPatterns = "/getCFDistance")
public class CrowFlightDistanceServlet extends HttpServlet {

    private CityDaoController cdc;

    @Override
    public void init() {
        cdc = new CityDaoController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            City fromCity = cdc.getItem(1);
            City toCity = cdc.getItem(2);
            resp.getWriter().println(fromCity.toString() + toCity.toString() + new CrowFlightDistance(fromCity, toCity).getDistance());

            String cityName  = req.getParameter("cityName");
            String latitude = req.getParameter("latitude").toUpperCase();
            String longitude = req.getParameter("longitude").toUpperCase();
            City city = new City(cityName,latitude, longitude);
            int result = cdc.insertItem(city);
            resp.getWriter().write("city_id : " + result);

        } catch (DaoException e) {
            e.printStackTrace();
        } catch (CityException e) {
            e.printStackTrace();
        }


    }
}
