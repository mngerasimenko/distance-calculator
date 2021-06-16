package ru.mngerasimenko.distancecalculator;


import ru.mngerasimenko.distancecalculator.dao.CityDaoController;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.xml.XmlDistanceCalculator;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DistanceCalculator {

    public static void main(String[] args) throws CityException, JAXBException, DaoException, IOException {


        //XmlDistanceCalculator xmldc = new XmlDistanceCalculator();
        //xmldc.toXmlFile(new File("src/main/resources/data.xml"));

        //xmldc.fromXmlFile(new File("src/main/resources/data.xml"));



    }
}
