package ru.mngerasimenko.distancecalculator.view;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.inject.Singleton;
import javax.servlet.annotation.WebServlet;

@Singleton
@Title("Distanse Calculator")
@Theme("valo")
public class MainView extends UI {

    private CityEditorForm cityEditor;

    public MainView() {

        //cityEditor = new CityEditorForm();
    }

    @Override
    public void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout =  new VerticalLayout();
        VerticalLayout editLayout = new VerticalLayout();


        final Label label = new Label("Distance Calculator");

        final HorizontalLayout menuLayout = new HorizontalLayout();
        final Button buttonCityEditor = new Button("City editor");
        buttonCityEditor.addClickListener(event -> {
            editLayout.removeAllComponents();
            editLayout.addComponent(new CityEditorForm());
        });
        final Button buttonCrowFlight = new Button("Crow flight distance");
        buttonCrowFlight.addClickListener(event -> {
            editLayout.removeAllComponents();
            editLayout.addComponent(new CrowFlightDistanceForm());
        });
        final Button buttonMatrix = new Button("Matrix distance");
        buttonMatrix.addClickListener(event -> {
            editLayout.removeAllComponents();
            editLayout.addComponent(new MatrixDistanceForm());
        });

        menuLayout.addComponent(buttonCityEditor);
        menuLayout.addComponent(buttonCrowFlight);
        menuLayout.addComponent(buttonMatrix);

        layout.addComponent(label);
        layout.addComponent(menuLayout);
        layout.addComponent(editLayout);
        setContent(layout);


//        final TextField field = new TextField("Type city id:");
//        field.setWidth("500");
//
//        Button button = new Button("Get city");
//        button.addClickListener(e -> {
//            try {
//                layout.addComponent(new Label(cdc.getItem(Integer.valueOf(field.getValue())).toString()));
//                Notification.show(cdc.getItem(Integer.valueOf(field.getValue())).toString());
//            } catch (DaoException daoException) {
//                daoException.printStackTrace();
//            } catch (CityException cityException) {
//                cityException.printStackTrace();
//            }
//        });
//        layout.addComponents(field, button);
//
//        try {
//            ComboBox box = new ComboBox("", cdc.getAll());
//            box.setWidth("500");
//            layout.addComponent(box);
//
//        } catch (DaoException e) {
//            e.printStackTrace();
//        } catch (CityException e) {
//            e.printStackTrace();
//        }
//
//        setContent(layout);
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainView.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

    }
}