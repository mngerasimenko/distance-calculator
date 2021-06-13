package ru.mngerasimenko.distancecalculator.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

@XmlRootElement(name = "distanceCalculator")
@XmlSeeAlso({City.class, Distance.class})
public class XmlStorage {

    private List<City> citysList;
    private List<Distance> distancesList;

    public XmlStorage() {
    }

    public XmlStorage(List<City> citysList, List<Distance> distancesList) {
        this.citysList = citysList;
        this.distancesList = distancesList;
    }

    @XmlElementWrapper(name = "citys")
    @XmlElement(name = "city")
    public List getCityList() {
        return citysList;
    }

    public void setCityList(List list) {
        this.citysList = list;
    }

    @XmlElementWrapper(name = "distances")
    @XmlElement(name = "distance")
    public List getDistanceList() {
        return distancesList;
    }

    public void setDistanceList(List list) {
        this.distancesList = list;
    }
}
