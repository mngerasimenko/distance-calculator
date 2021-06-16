package ru.mngerasimenko.distancecalculator.view;

import com.google.gson.Gson;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.*;

import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import ru.mngerasimenko.distancecalculator.exception.CityException;
import ru.mngerasimenko.distancecalculator.exception.DaoException;
import ru.mngerasimenko.distancecalculator.settings.Settings;
import ru.mngerasimenko.distancecalculator.xml.XmlDistanceCalculator;

import javax.inject.Singleton;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBException;

import java.io.*;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

@Singleton
@Title("Distance Calculator")
@Theme("valo")
public class MainView extends UI {

    CityEditorForm cityEditorForm;

    public MainView() {
        cityEditorForm = new CityEditorForm();
    }

    @Override
    public void init(VaadinRequest vaadinRequest) {

        final VerticalLayout mainLayout =  new VerticalLayout();
        VerticalLayout editLayout = new VerticalLayout();

        final Label label = new Label("Distance Calculator");
        final HorizontalLayout menuLayout = new HorizontalLayout();
        final Button buttonCityEditor = new Button("City editor");
        buttonCityEditor.addClickListener(event -> {
            editLayout.removeAllComponents();
            editLayout.addComponent(cityEditorForm);
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

        Upload upload = new Upload(null, new XmlDistanceCalculator());
        upload.setButtonCaption("Load from XML");
        upload.addSucceededListener(event -> {
            Client client = ClientBuilder.newClient();
            URI uri = UriBuilder.fromUri(Settings.getProperty(Settings.BASE_URI)).build();
            WebTarget target = client.target(uri);

            String response = target.path("rest").path("toxml").path(event.getFilename())
                    .request().accept(MediaType.APPLICATION_JSON).get(String.class);

            if (response != null) {
                System.out.println("File " + event.getFilename() + " is loaded");
                Gson gson = new Gson();
                Integer[] added = gson.fromJson(response, Integer[].class);
                Notification.show("Added " + added[0] + " cityes and " + added[1] + " distances").setDelayMsec(2000);

                editLayout.removeAllComponents();
                cityEditorForm.initGrid();
                editLayout.addComponent(cityEditorForm);
            } else Notification.show("Error loading file", Notification.Type.ERROR_MESSAGE);
        });

        Button saveToXml = new Button("Save to XML");
        saveToXml.addClickListener(event -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMDDHHmmss");
            File dir = new File(Settings.getProperty(Settings.XML_DIR));
            if (!dir.exists()) {
                dir.mkdir();
            }
            try {
                String fileXml = Settings.getProperty(Settings.XML_DIR) + "dc_" + sdf.format(new Date()) + ".xml";
                new XmlDistanceCalculator().toXmlFile(new File(fileXml));
                Notification.show("Save susses in " + fileXml);
            } catch (CityException e) {
                e.printStackTrace();
            } catch (DaoException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });

        menuLayout.addComponent(buttonCityEditor);
        menuLayout.addComponent(buttonCrowFlight);
        menuLayout.addComponent(buttonMatrix);
        menuLayout.addComponent(upload);
        menuLayout.addComponent(saveToXml);
        mainLayout.addComponent(label);
        mainLayout.addComponent(menuLayout);
        mainLayout.addComponent(editLayout);

        setContent(mainLayout);
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainView.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

    }
}