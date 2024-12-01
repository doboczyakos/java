package com.example.eloadas;

import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.order.MarketOrderRequest;
import com.oanda.v20.order.OrderCreateRequest;
import com.oanda.v20.order.OrderCreateResponse;
import com.oanda.v20.primitives.InstrumentName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Calendar;

public class openController {
    static Context ctx;
    static AccountID accountId;



    ObservableList<String> options =
            FXCollections.observableArrayList(
                    "EUR_USD",
                    "USD_JPY",
                    "GBP_USD",
                    "USD_CHF",
                    "AUD_CHF"
            );
    @FXML
    private ComboBox devizaParok;

    public void initialize() {
        devizaParok.setItems(options);

    }

    @FXML
    private TextField amount;

    @FXML
    private Label result;

    @FXML
    public void openPosition() {
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        if(today != Calendar.SATURDAY && today != Calendar.SUNDAY) {


            ctx = new
                    ContextBuilder(config.URL).setToken(config.TOKEN).setApplication("StepByStepOrder").build();
            accountId = config.ACCOUNTID;

            open(devizaParok.getValue().toString(), Integer.parseInt(amount.getText()));
        }else{
            result.setText("Jelenleg hétvége van. Nem lehet kereskedni!");
        }
    }

    void open(String instrumentInput,int amount){
        //System.out.println("Place a Market Order");
        InstrumentName instrument = new InstrumentName(instrumentInput);
        try {
            OrderCreateRequest request = new OrderCreateRequest(accountId);
            MarketOrderRequest marketorderrequest = new MarketOrderRequest();
            marketorderrequest.setInstrument(instrument);
            marketorderrequest.setUnits(amount);
            request.setOrder(marketorderrequest);
            OrderCreateResponse response = ctx.order.create(request);
            result.setText("Sikeres pozicio nyitas. TradeId: "+response.getOrderFillTransaction().getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

