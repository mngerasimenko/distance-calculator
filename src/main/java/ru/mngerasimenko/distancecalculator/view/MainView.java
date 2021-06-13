package ru.mngerasimenko.distancecalculator.view;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.xml.XmlDistanceCalculator;

import javax.inject.Singleton;
import javax.servlet.annotation.WebServlet;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;

@Singleton
@Title("Distance Calculator")
@Theme("valo")
public class MainView extends UI {

    public MainView() {

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

        Button fromXmlFile = new Button("Load from Xml");
        fromXmlFile.addClickListener(clickEvent -> {
            XmlDistanceCalculator xmldc = new XmlDistanceCalculator();
            try {
                int[] added = xmldc.fromXmlFile(new File("D:\\javaProjects\\distance-calculator\\src\\main\\resources\\data.xml"));
                Notification.show("Added " + added[0] + " cityes and " + added[1] + " distances").setDelayMsec(2000);
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (CityException e) {
                e.printStackTrace();
            } catch (DaoException e) {
                e.printStackTrace();
            }
        });
        layout.addComponent(fromXmlFile);
        setContent(layout);
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainView.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

    }
}