package ru.mngerasimenko.distancecalculator.view;

import com.vaadin.ui.*;
import ru.mngerasimenko.distancecalculator.dao.CityDaoController;
import ru.mngerasimenko.distancecalculator.domain.City;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;

import java.util.List;

public class CityEditorForm extends CustomComponent {

    private CityDaoController cdc;
    private TextField findCity;
    private Grid<City> cityGrid;
    private Button addCity;
    private Button editCity;
    private Button deleteCity;

    private City cityItem;

    Window popupWindows;

    public CityEditorForm() {
        cdc = new CityDaoController();
        cityItem = new City();
        findCity = new TextField("Input city name:");
        findCity.addValueChangeListener(event -> initGrid());
        cityGrid = new Grid<City>();
        initGrid();
        setCompositionRoot(new VerticalLayout(findCity, cityGrid, initButton()));
    }


    private HorizontalLayout initButton() {
        addCity = new Button("Added");
        addCity.addClickListener(event -> {
            createWindow("Adding city", 1);
        });
        editCity = new Button("Edit");
        editCity.addClickListener(event -> {
            if (!cityGrid.getSelectedItems().isEmpty()) {
                setCityItem();
                createWindow("Edit city", 2);
            }else Notification.show("Please select city for edit");
        });
        deleteCity = new Button("Delete");
        deleteCity.addClickListener(event -> {
            if (!cityGrid.getSelectedItems().isEmpty()) {
                setCityItem();
                createWindow("Delete city", 3);
            }else Notification.show("Please select city for delete");
        });
        return new HorizontalLayout(addCity, editCity, deleteCity);
    }

    private void createWindow(String winName, int type) {
        if (type == 3) {
            int cityId = cityItem.getCityId();
            String cityName = cityItem.getCityName();
            Label label = new Label("Delete city " + cityName + "?");
            Button okButton = new Button("OK", (event -> {
                try {
                    cdc.deleteCity(cityId);
                    initGrid();
                    Notification.show("City '" + cityName + "' was deleted");
                    popupWindows.close();
                } catch (DaoException e) {
                    // e.printStackTrace();
                    Notification.show("You can't delete a city! The distance has already been calculated for this city.");
                    popupWindows.close();
                }
            }));
            Button cancelButton = new Button("Cancel", (event -> popupWindows.close()));
            popupWindows = new Window(winName, new VerticalLayout(label ,new HorizontalLayout(okButton, cancelButton)));
        } else {
            popupWindows = new Window(winName, initEditor(type));
        }
        popupWindows.center();
        popupWindows.setModal(true);
        popupWindows.setResizable(false);
        UI.getCurrent().addWindow(popupWindows);
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

    private void setCityItem() {
        cityItem.setCityId(cityGrid.getSelectedItems().iterator().next().getCityId());
        cityItem.setCityName(cityGrid.getSelectedItems().iterator().next().getCityName());
        cityItem.setLatitude(cityGrid.getSelectedItems().iterator().next().getLatitude());
        cityItem.setLongitude(cityGrid.getSelectedItems().iterator().next().getLongitude());
    }

    private VerticalLayout initEditor(int type) {
        TextField editCityName = new TextField("City name");
        TextField editLatitude = new TextField("Latitude");
        TextField editLongitude = new TextField("Longitude");
        Button button;
        if (type == 1) {
            button = new Button("Add city");
            button.addClickListener(event -> {
                try {
                    cdc.insertItem(new City(editCityName.getValue(), editLatitude.getValue(), editLongitude.getValue()));
                    initGrid();
                    popupWindows.close();
                } catch (CityException e) {
                    Notification.show("City Error! " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                   //e.printStackTrace();
                } catch (DaoException e) {
                    Notification.show("Error! " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                    //e.printStackTrace();
                }
            });
        } else {
            editCityName.setValue(cityItem.getCityName());
            editLatitude.setValue(cityItem.getLatitude());
            editLongitude.setValue(cityItem.getLongitude());
            button = new Button("Save city");
            button.addClickListener(event -> {
                try {
                    cdc.editCity(cityItem, editCityName.getValue(), editLatitude.getValue(), editLongitude.getValue());
                    initGrid();
                    popupWindows.close();
                } catch (CityException e) {
                    Notification.show("City Error! " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                    //e.printStackTrace();
                } catch (DaoException e) {
                    Notification.show("Error! " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                    //e.printStackTrace();
                }
            });
        }
        return new VerticalLayout(editCityName, editLatitude, editLongitude, button);
    }


}
