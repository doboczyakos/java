package com.example.eloadas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class mnbController {

    @FXML
     ComboBox devizaParok;
    @FXML
     DatePicker startDatePicker;
    @FXML
     DatePicker startEndPicker;

    @FXML
    private LineChart devizaChart;

    @FXML
    private NumberAxis xAxis ;

    @FXML
    private NumberAxis yAxis ;

    public void letoltes1(){
        mnbSoap Soap = new mnbSoap();
        Soap.letoltes1();
    }

    public void letoltes2(){
        mnbSoap Soap = new mnbSoap();
        String currency = devizaParok.getValue().toString();
        String startDate = startDatePicker.getValue().toString();
        String endDate = startEndPicker.getValue().toString();
        String adatok=Soap.letoltes2(currency,startDate,endDate);

    }

    public void graf(){
        try {
            File myObj = new File("MNB.txt");
            Scanner myReader = new Scanner(myObj);
            String adatok="";
            while (myReader.hasNextLine()) {
                adatok += myReader.nextLine();
                //System.out.println(data);
            }
            adatok=adatok.replace(",",".");
            String[] devizak=adatok.split("</Rate>");
            int runs=adatok.length()-3;
            System.out.println(runs);
            XYChart.Series series = new XYChart.Series();
            for (int i = 0; i < devizak.length-1; i++) {
                String[] de=devizak[i].split(">");
                series.getData().add(new XYChart.Data(i, Double.parseDouble(de[de.length-1])));

            }
            devizaChart.getData().add(series);
            xAxis.setAutoRanging(true);
            xAxis.setForceZeroInRange(false);
            yAxis.setAutoRanging(true);
            yAxis.setForceZeroInRange(false);
            devizaChart.autosize();

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void initialize() {
        ObservableList<String> options =
                    FXCollections.observableArrayList(
                            "EUR",
                            "USD",
                            "GBP",
                            "CHF"
                   );
        devizaParok.setItems(options);
    }


}