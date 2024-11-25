package com.example.eloadas;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.eloadas.hulladekSzallitas.Lakig;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReadController {

    @FXML
    private TableView<Lakig> table;

    @FXML
    private TableColumn<Lakig, String> igenyColumn;

    @FXML
    private TableColumn<Lakig, String> szolgColumn;

    @FXML
    private TableColumn<Lakig, Integer> mennyisegColumn;

    @FXML
    public void initialize() {
        igenyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(new SimpleDateFormat().getDateInstance(SimpleDateFormat.SHORT).format(cellData.getValue().getIgeny())));
        szolgColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSzolgaltatas().getJelentes()));
        mennyisegColumn.setCellValueFactory(new PropertyValueFactory<>("mennyiseg"));

        ObservableList<Lakig> data = FXCollections.observableArrayList();

        var cfg = new Configuration().configure("hibernate.cfg.xml");
        try (Session session = cfg.buildSessionFactory().openSession()) {
            data.addAll(session.createQuery("FROM Lakig", Lakig.class).list());
        }

        table.setItems(data);
    }
}
