package ru.mngerasimenko.distancecalculator.view;

import com.vaadin.ui.*;
import ru.mngerasimenko.distancecalculator.calculator.MatrixDistance;
import ru.mngerasimenko.distancecalculator.dao.CityDaoController;
import ru.mngerasimenko.distancecalculator.dao.DistanceDaoController;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.storage.DistanceStorage;

import java.util.List;
import java.util.Set;

public class MatrixDistanceForm extends CustomComponent {

    DistanceDaoController ddc;
    DistanceStorage distanceStorage;
    Set<City> citySet;
    TextField distanceField;

    public MatrixDistanceForm() {

        ddc = new DistanceDaoController();
        distanceStorage = new DistanceStorage();
        try {
            distanceStorage.addItems(ddc.getAll());
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (CityException e) {
            e.printStackTrace();
        }
        citySet = distanceStorage.getCitySet();


        final Label formName = new Label("Distance calculation based on the matrix");
        ComboBox boxFromCity = initComboBox("From city");
        ComboBox boxToCity = initComboBox("To city");
        distanceField = new TextField("Distance (kilometers)");
        distanceField.setReadOnly(true);

        Button buttonCalculation = new Button("Calculate");
        buttonCalculation.addClickListener(event -> {
            City fromCity = (City)boxFromCity.getValue();
            City toCity = (City)boxToCity.getValue();

           // Notification.show(fromCity + " " + toCity, Notification.Type.ERROR_MESSAGE);
            MatrixDistance matrixDistance = new MatrixDistance((City)boxFromCity.getValue(),
                    (City)boxToCity.getValue());


            //matrixDistance.getDistance();
            distanceField.setValue(String.valueOf(matrixDistance.getDistance()));
        });

        setCompositionRoot(new VerticalLayout(formName, boxFromCity, boxToCity,
                distanceField, buttonCalculation));
    }

    private ComboBox initComboBox(String name) {
        ComboBox boxCity = new ComboBox(name);
        boxCity.setWidth("500");
        boxCity.setItems(citySet);
        boxCity.addValueChangeListener(event -> distanceField.setValue(""));
        return boxCity;
    }
}
