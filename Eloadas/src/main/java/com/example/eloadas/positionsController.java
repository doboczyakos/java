package com.example.eloadas;

import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.trade.Trade;
import com.oanda.v20.trade.TradeCloseRequest;
import com.oanda.v20.trade.TradeSpecifier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class positionsController {

    @FXML
    private Label result;

    @FXML
    private TableView<positions> positionsArea;    @FXML private TableColumn<accountSummary,String> userid;
    @FXML private TableColumn<accountSummary,String> tradeId;
    @FXML private TableColumn<accountSummary,String> instrument;
    @FXML private TableColumn<accountSummary,String> date;
    @FXML private TableColumn<accountSummary,String> amount;
    @FXML private TableColumn<accountSummary,String> price;
    @FXML private TableColumn<accountSummary,String> margin;

    @FXML
    private Button close;

    @FXML
    private TextField closeId;

    public class positions{

        public Long tradeId;
        public String instrument;
        public String date;
        public Long amount;
        public Double price;
        public Double margin;

        public Long getTradeId() {
            return tradeId;
        }

        public void setTradeId(Long tradeId) {
            this.tradeId = tradeId;
        }

        public String getInstrument() {
            return instrument;
        }

        public void setInstrument(String instrument) {
            this.instrument = instrument;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }


        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getMargin() {
            return margin;
        }

        public void setMargin(Double margin) {
            this.margin = margin;
        }

        public positions(Long tradeId, String instrument, String date, Long amount, Double price, Double margin) {
            this.tradeId = tradeId;
            this.instrument = instrument;
            this.date = date;
            this.amount = amount;
            this.price = price;
            this.margin = margin;
        }
    }

    public void listPositions() throws ExecuteException, RequestException {
        Context ctx = new ContextBuilder(config.URL).setToken(config.TOKEN).setApplication("positionsController").build();
        List<Trade> trades = ctx.trade.listOpen(config.ACCOUNTID).getTrades();
        ObservableList<positions> data= FXCollections.observableArrayList();
        for(Trade trade: trades) {
            System.out.println(trade.getId() + "\t" + trade.getInstrument() + "\t" + trade.getOpenTime() + "\t" + trade.getCurrentUnits() + "\t" + trade.getPrice() + "\t" + trade.getUnrealizedPL());

            data.add(
                    new positions(Long.parseLong(trade.getId().toString()), trade.getInstrument().toString(), trade.getOpenTime().toString(), Long.parseLong(trade.getCurrentUnits().toString()), Double.parseDouble(trade.getPrice().toString()), Double.parseDouble(trade.getUnrealizedPL().toString()))
            );
        }

        TableColumn userid = new TableColumn("Trade Id");
        userid.setCellValueFactory(new PropertyValueFactory<positions,String>("tradeId"));

        TableColumn alias = new TableColumn("Instrument");
        alias.setCellValueFactory(new PropertyValueFactory<positions,String>("instrument"));

        TableColumn currency = new TableColumn("Nyitasi ido");
        currency.setCellValueFactory(new PropertyValueFactory<positions,String>("date"));

        TableColumn balance = new TableColumn("Mennyiseg");
        balance.setCellValueFactory(new PropertyValueFactory<positions,String>("amount"));

        TableColumn useridopenTime = new TableColumn("Osszeg");
        useridopenTime.setCellValueFactory(new PropertyValueFactory<positions,String>("price"));

        TableColumn commision = new TableColumn("Nem realizalt");
        commision.setCellValueFactory(new PropertyValueFactory<positions,String>("margin"));


            positionsArea.setItems(data);
            positionsArea.getColumns().addAll(userid, alias, currency, balance, useridopenTime, commision);

}

public  void closePosition(){
    try {
        String tradeId=closeId.getText();
        Context ctx = new ContextBuilder(config.URL).setToken(config.TOKEN).setApplication("positionsController").build();
        ctx.trade.close(new TradeCloseRequest(config.ACCOUNTID, new TradeSpecifier(tradeId)));
        closeId.setText("Trade id: "+tradeId+" sikeresen torlesre kerult!");
    } catch (Exception e) { throw new RuntimeException(e); }
}
}









