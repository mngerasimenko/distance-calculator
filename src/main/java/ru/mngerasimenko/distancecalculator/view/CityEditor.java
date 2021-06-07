package ru.mngerasimenko.distancecalculator.view;

import com.vaadin.ui.*;
import ru.mngerasimenko.distancecalculator.dao.CityDaoController;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;

import java.util.List;

public class CityEditor extends CustomComponent {

    private CityDaoController cdc;
    private TextField findCity;
    private Grid<City> cityGrid;
    private Button addCity;
    private Button editCity;
    private Button deleteCity;

    private City cityItem;


    public CityEditor() {
        cdc = new CityDaoController();

        findCity = new TextField("Input city name:");
        findCity.addValueChangeListener(event -> initGrid());
        cityGrid = new Grid<City>();
        initGrid();
        setCompositionRoot(new VerticalLayout(findCity, cityGrid, initButton()));
    }


    private HorizontalLayout initButton() {
        addCity = new Button("Added");
        addCity.addClickListener(event -> {
            Window addedWindows = new Window("Adding city", initEditor(1));
            addedWindows.center();
            UI.getCurrent().addWindow(addedWindows);
        });
        editCity = new Button("Edit");
        editCity.addClickListener(event -> {
            Window addedWindows = new Window("Edit city", initEditor(2));
            addedWindows.center();
            UI.getCurrent().addWindow(addedWindows);
        });
        deleteCity = new Button("Delete");
        deleteCity.addClickListener(event -> {
            try {
                int cityId = cityGrid.getSelectedItems().iterator().next().getCityId();
                String cityName = cityGrid.getSelectedItems().iterator().next().getCityName();
                cdc.deleteCity(cityId);
                Notification.show("City '" + cityName + "' was deleted");
            } catch (DaoException e) {
                e.printStackTrace();
                Notification.show("You can't delete a city! The distance has already been calculated for this city.");
            }
            initGrid();
        });
        return new HorizontalLayout(addCity, editCity, deleteCity);
    }

    private void initGrid() {
        cityGrid.removeAllColumns();
        List<City> cityList = null;
        try {
            cityList = cdc.findItem(findCity.getValue());
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (CityException e) {
            e.printStackTrace();
        }
        if (cityList != null && !cityList.isEmpty()) {
            cityGrid.setItems(cityList);
            cityGrid.addColumn(City::getCityId).setCaption("Id");
            cityGrid.addColumn(City::getCityName).setCaption("City name");
            cityGrid.addColumn(City::getLatitude).setCaption("Latitude");
            cityGrid.addColumn(City::getLongitude).setCaption("Longitude");
        }
    }

    private VerticalLayout initEditor(int type) {

        TextField editCityName = new TextField("City name");
        TextField editLatitude = new TextField("Latitude");
        TextField editLongitude = new TextField("Longitude");
        Button button;
        if(type == 1) {
            button = new Button("Add city");
            button.addClickListener(event -> {
                try {
                    cdc.insertItem(new City(editCityName.getValue(), editLatitude.getValue(), editLongitude.getValue()));
                    initGrid();
                } catch (CityException e) {
                    Notification.show("City Error! " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                    e.printStackTrace();
                } catch (DaoException e) {
                    Notification.show("Error! " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            });
        } else {
            editCityName.setValue(cityGrid.getSelectedItems().iterator().next().getCityName());
            editLatitude.setValue(cityGrid.getSelectedItems().iterator().next().getLatitude());
            editLongitude.setValue(cityGrid.getSelectedItems().iterator().next().getLongitude());
            button = new Button("Save city");
            button.addClickListener(event -> {
                try {
                    City city = cityGrid.getSelectedItems().iterator().next();
                    cdc.editCityName(city, editCityName.getValue());
                    initGrid();
                } catch (CityException e) {
                    Notification.show("City Error! " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                    e.printStackTrace();
                } catch (DaoException e) {
                    Notification.show("Error! " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            });
        }
        return new VerticalLayout(editCityName, editLatitude, editLongitude, button);
    }


}
