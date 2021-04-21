package ru.mngerasimenko.distancecalculator.domain;

public class Сrawler {

    private boolean passed;
    private City city;
    private double distance;


    public Сrawler(boolean passed, double distance) {
        this.passed = passed;
        this.distance = distance;
    }

    public Сrawler(City city, double distance) {
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

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}