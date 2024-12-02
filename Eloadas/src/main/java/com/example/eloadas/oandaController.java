package com.example.eloadas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class oandaController {
    @FXML
    private Button oandaTest;

    @FXML private TableView<accountSummary>table;
    @FXML private TableColumn<accountSummary,String> userid;
    @FXML private TableColumn<accountSummary,String> alias;
    @FXML private TableColumn<accountSummary,String> currency;
    @FXML private TableColumn<accountSummary,String> balance;
    @FXML private TableColumn<accountSummary,String> openTime;
    @FXML private TableColumn<accountSummary,String> commision;
    @FXML private TableColumn<accountSummary,String> openTradeCount;
    @FXML private TableColumn<accountSummary,String> openPositionCount;
    @FXML private TableColumn<accountSummary,String> unrealized;
    @FXML private TableColumn<accountSummary,String> value ;
    @FXML private TableColumn<accountSummary,String> withdrawLimit ;
    @FXML
    private void loadSummary() {
        String token = "ebef36cadce744cb18dbbb91447c1d41-403b56730435d46c0d7c5386cba6505f";
        String account = "101-004-30457100-001";
        ObservableList<accountSummary> data = FXCollections.observableArrayList(
                new accountSummary(token,account)
        );
        TableColumn userid = new TableColumn("Azonosito");
        userid.setCellValueFactory(new PropertyValueFactory<accountSummary,String>("account"));

        TableColumn alias = new TableColumn("Alias");
        alias.setCellValueFactory(new PropertyValueFactory<accountSummary,String>("alias"));

        TableColumn currency = new TableColumn("Pénznem");
        currency.setCellValueFactory(new PropertyValueFactory<accountSummary,String>("currency"));

        TableColumn balance = new TableColumn("Egyenleg");
        balance.setCellValueFactory(new PropertyValueFactory<accountSummary,String>("balance"));

        TableColumn useridopenTime = new TableColumn("Számla nyitás ideje");
        useridopenTime.setCellValueFactory(new PropertyValueFactory<accountSummary,String>("openTime"));

        TableColumn commision = new TableColumn("Commisszió összeg");
        commision.setCellValueFactory(new PropertyValueFactory<accountSummary,String>("commision"));

        TableColumn openTradeCount = new TableColumn("Nyitott trade opciók");
        openTradeCount.setCellValueFactory(new PropertyValueFactory<accountSummary,String>("openTradeCount"));

        TableColumn openPositionCount = new TableColumn("Nyitott poziciók");
        openPositionCount.setCellValueFactory(new PropertyValueFactory<accountSummary,String>("openPositionCount"));

        TableColumn unrealized = new TableColumn("Nem realizált egyenleg");
        unrealized.setCellValueFactory(new PropertyValueFactory<accountSummary,String>("unrealized"));

        TableColumn value = new TableColumn("Pozició érték");
        value.setCellValueFactory(new PropertyValueFactory<accountSummary,String>("value"));

        TableColumn withdrawLimit = new TableColumn("Felvételi limi");
        withdrawLimit.setCellValueFactory(new PropertyValueFactory<accountSummary,String>("withdrawLimit"));

        table.setItems(data);
        table.getColumns().addAll(userid,alias,currency,balance,useridopenTime,commision,openTradeCount,openPositionCount,unrealized,value,withdrawLimit);



    }
}
