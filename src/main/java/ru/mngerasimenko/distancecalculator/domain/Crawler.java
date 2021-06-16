package ru.mngerasimenko.distancecalculator.domain;

public class Crawler {

   // private boolean passed;
    private City city;
    private double distance;


    public Crawler( double distance) {
       // this.passed = passed;
        this.distance = distance;
    }

    public Crawler(City city, double distance) {
        this.city = city;
        this.distance = distance;
    }

    public City getCity() { return city; }
    public double getDistance() { return distance; }

    public void setCity(City city) {
        this.city = city;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

//    public boolean isPassed() {
//        return passed;
//    }
//
//    public void setPassed(boolean passed) {
//        this.passed = passed;
//    }
}