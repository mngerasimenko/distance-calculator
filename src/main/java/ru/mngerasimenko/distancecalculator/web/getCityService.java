package ru.mngerasimenko.distancecalculator.web;

import ru.mngerasimenko.distancecalculator.dao.CityDaoController;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/getCity")
@Singleton
public class getCityService {

    private CityDaoController cdc;

    @PostConstruct
    public void init() {
        cdc = new CityDaoController();
        System.out.println("START");
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public City getCity(@PathParam("id") int id) throws CityException, DaoException {

        return cdc.getItem(id);
    }
}
