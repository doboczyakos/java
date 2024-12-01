package com.example.eloadas;

import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.pricing.PricingGetRequest;
import com.oanda.v20.pricing.PricingGetResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class devizaparController {


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


    public void initialize() {
        devizaParok.setItems(options);

    }

    public void comboChange() {
        devizaSelectorLabel.setText(devizaParok.getValue().toString());
    }

    @FXML
    private void loadDevizaPar() throws ParseException {
        //String aktualisAr=pricePolling.getPrice(devizaParok.getValue().toString());

        String aktualisAr=pricePolling.getPrice(devizaParok.getValue().toString());
        eladas.setWrapText(true);
        eladas.setText(aktualisAr);

    }
}
