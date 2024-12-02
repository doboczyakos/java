package com.example.eloadas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.text.ParseException;
import java.util.ArrayList;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class devizaOldController {


    ObservableList<String> options =
            FXCollections.observableArrayList(
                    "EUR_USD",
                    "USD_JPY",
                    "GBP_USD",
                    "USD_CHF"
            );
    @FXML
    private ComboBox devizaParok;

    @FXML
    private Label devizaSelectorLabel;

    @FXML
    private TextArea eladas;

    @FXML
    private Label vetel;

    @FXML
    private LineChart devizaChart;

    @FXML
    private NumberAxis xAxis ;

    @FXML
    private NumberAxis yAxis ;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    String startD;
    String endD;


    public void initialize() {
        devizaParok.setItems(options);

    }

    public void comboChange() {
        devizaSelectorLabel.setText(devizaParok.getValue().toString());
    }

    public void setStarDate(){
        startD=startDate.getValue().toString();
    }

    public void setEndDate(){
        endD=endDate.getValue().toString();
    }


    @FXML
    private void loadDevizaPar() throws ParseException {

        String aktualisAr=pricePolling.getOldPrice(devizaParok.getValue().toString(), startD,endD);
        eladas.setWrapText(true);
        eladas.setText(aktualisAr);
        ArrayList<Double> chartData=pricePolling.getChart(devizaParok.getValue().toString(), startD,endD);


        devizaChart.setTitle(devizaParok.getValue().toString());
        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < chartData.size(); i++) {
            series.getData().add(new XYChart.Data(i, chartData.get(i)));
        }



        devizaChart.getData().add(series);
        xAxis.setAutoRanging(true);
        xAxis.setForceZeroInRange(false);
        yAxis.setAutoRanging(true);
        yAxis.setForceZeroInRange(false);
        devizaChart.autosize();


    }
}
