package ru.mngerasimenko.distancecalculator.web;

import ru.mngerasimenko.distancecalculator.dao.CityDaoController;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name= "AddingCityServlet", urlPatterns = "/addCity")
public class AddingCityServlet extends HttpServlet {

    CityDaoController cityDC;

    @Override
    public void init() {
        cityDC = new CityDaoController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String cityName  = req.getParameter("cityName");
        String latitude = req.getParameter("latitude").toUpperCase();
        String longitude = req.getParameter("longitude").toUpperCase();
        City city = null;
        try {
            city = new City(cityName,latitude, longitude);
            int result = cityDC.insertItem(city);
            resp.getWriter().write("city_id : " + result);
        } catch (CityException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
