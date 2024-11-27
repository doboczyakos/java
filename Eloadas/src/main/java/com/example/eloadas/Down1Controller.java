package com.example.eloadas;

//import MNBSoap.MNBArfolyamServiceSoap;
//import MNBSoap.MNBArfolyamServiceSoapImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Down1Controller {

    @FXML
    private Button downButton;

    @FXML
    private void ondownButtonClicked() {
//        MNBArfolyamServiceSoapImpl mnbSoap = new MNBArfolyamServiceSoapImpl();
//        MNBArfolyamServiceSoap mnbSoapService = mnbSoap.getCustomBindingMNBArfolyamServiceSoap();
        try {
//            System.out.println(mnbSoapService.getInfo());
//            System.out.println(mnbSoapService.getCurrentExchangeRates());
//            System.out.println(mnbSoapService.getExchangeRates("2022-08-14", "2022-09-14", "EUR"));
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
