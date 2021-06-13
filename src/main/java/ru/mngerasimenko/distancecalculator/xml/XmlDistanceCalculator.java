package ru.mngerasimenko.distancecalculator.xml;

import ru.mngerasimenko.distancecalculator.dao.CityDaoController;
import ru.mngerasimenko.distancecalculator.dao.DistanceDaoController;
import ru.mngerasimenko.distancecalculator.dao.SimpleConnection;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.domain.Distance;
import ru.mngerasimenko.distancecalculator.domain.XmlStorage;
import ru.mngerasimenko.distancecalculator.exception.AlreadyAddedException;
import ru.mngerasimenko.distancecalculator.exception.CalculateException;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class XmlDistanceCalculator {

    DistanceDaoController ddc;
    CityDaoController cdc;
    XmlStorage storage;

    public XmlDistanceCalculator() {
        cdc = new CityDaoController();
        ddc = new DistanceDaoController();
    }

    public void toXmlFile(File xmlFile) throws CityException, DaoException, JAXBException {
        storage = new XmlStorage(cdc.getAll(), ddc.getAll());
        JAXBContext context = JAXBContext.newInstance(XmlStorage.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(storage, xmlFile);
    }

    public int[] fromXmlFile(File xmlFile) throws JAXBException, FileNotFoundException, CityException, DaoException {
        int countCity = 0;
        int countDist = 0;
        JAXBContext context = JAXBContext.newInstance(XmlStorage.class);
        Unmarshaller un = context.createUnmarshaller();
        storage = (XmlStorage) un.unmarshal(new InputStreamReader(new FileInputStream(xmlFile), StandardCharsets.UTF_8));
        for(Object city: storage.getCityList()) {
            try {
                cdc.insertItem((City) city);
                countCity++;
            } catch (AlreadyAddedException ex) {
                // city Already added
            }

        }
        for(Object dist: storage.getDistanceList()) {

            Distance distance = (Distance) dist;
            City fromCity = distance.getFromCity();
            for (City city: cdc.findItem(fromCity.getCityName())) {
                if (city.equals(fromCity)) {
                    distance.setFromCity(city);
                }
            }
            City toCity = distance.getToCity();
            for (City city: cdc.findItem(toCity.getCityName())) {
                if (city.equals(toCity)) {
                    distance.setToCity(city);
                }
            }
            try {
                ddc.insertItem(distance);
                countDist++;
            } catch (AlreadyAddedException ex) {
             // distance Already added
            } catch (CalculateException e) {
                e.printStackTrace();
            }
        }
        int[] result = {countCity, countDist};
        return result;
    }

}
