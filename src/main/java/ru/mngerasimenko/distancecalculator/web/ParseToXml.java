package ru.mngerasimenko.distancecalculator.web;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.xml.XmlDistanceCalculator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Path("/toxml")
public class ParseToXml {

    @GET
    @Path("/{fileName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer[] ParseToXml(@PathParam("fileName") String fileName) {
        XmlDistanceCalculator toxml = new XmlDistanceCalculator();
        Integer[] added = null;
        try {
            added = toxml.fromXmlFile(new File(fileName));
        } catch (CityException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return added;
    }

}
