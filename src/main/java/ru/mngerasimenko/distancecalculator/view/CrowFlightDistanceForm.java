package ru.mngerasimenko.distancecalculator.view;

import com.vaadin.ui.*;
import ru.mngerasimenko.distancecalculator.dao.CityDaoController;
import ru.mngerasimenko.distancecalculator.dao.DistanceDaoController;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.domain.Distance;
import ru.mngerasimenko.distancecalculator.exception.CalculateException;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;

import java.util.List;

public class CrowFlightDistanceForm extends CustomComponent {

    CityDaoController cdc;
    DistanceDaoController ddc;
    List cityList;
    City fromCity = null;
    City toCity = null;

    private ComboBox boxFromCity;
    private ComboBox boxToCity;
    private TextField distanceField;
    private Button calculateButton;
    private CheckBox saveBD;


    public CrowFlightDistanceForm() {

        cdc = new CityDaoController();
        ddc = new DistanceDaoController();
        setCityList();

        Label formName = new Label("Crow flight distance calculator");
        saveBD = new CheckBox("Save result to database");
        saveBD.setEnabled(false);
        distanceField = new TextField("Distance (kilometers)");
        distanceField.setReadOnly(true);

        boxFromCity = new ComboBox("From city", cityList);
        boxFromCity.setWidth("500");
        boxFromCity.addValueChangeListener(event -> setDistanceField());

        boxToCity = new ComboBox("To city", cityList);
        boxToCity.setWidth("500");
        boxToCity.addValueChangeListener(event -> setDistanceField());

        calculateButton = new Button("Calculate");
        calculateButton.addClickListener(event -> {
            try {
                City fromCity = (City)boxFromCity.getValue();
                City toCity = (City)boxToCity.getValue();
                Distance distance = new Distance(fromCity, toCity);
                distanceField.setValue(String.valueOf(distance.getDistance()));
                if (saveBD.getValue()) {
                    ddc.insertItem(distance);
                    Notification.show("Distance between the cities of "+
                            fromCity.getCityName() + " and " +
                            toCity.getCityName() + " is saved in the database",
                            Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(1000);
                    calculateButton.setEnabled(false);
                }
            } catch (CityException e) {
                Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
            } catch (CalculateException e) {
                Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
               // e.printStackTrace();
            } catch (DaoException e) {
                Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
               // e.printStackTrace();
            }
        });

        setCompositionRoot(new VerticalLayout(formName, boxFromCity, boxToCity,
                saveBD, distanceField, calculateButton));
    }

    public void setCityList() {
        try {
            cityList = cdc.getAll();
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (CityException e) {
            e.printStackTrace();
        }
    }

    private void setDistanceField() {
        calculateButton.setEnabled(true);
        int distanceId = 0;
        toCity = (City)boxToCity.getValue();
        fromCity = (City)boxFromCity.getValue();
        try {
            if (fromCity == null || toCity == null || fromCity.equals(toCity)) {
                saveBD.setEnabled(false);
                saveBD.setValue(false);
                distanceField.setValue("");
            } else {
                distanceId = ddc.getExisting(fromCity, toCity);
                saveBD.setEnabled(true);
                if (distanceId  == -1) {
                    distanceField.setValue("");
                } else {
                    saveBD.setEnabled(false);
                    saveBD.setEnabled(false);
                    distanceField.setValue(String.valueOf(ddc.getItem(distanceId).getDistance()));
                }
            }
        } catch (DaoException | CityException e) {
            e.printStackTrace();
            Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }
}
