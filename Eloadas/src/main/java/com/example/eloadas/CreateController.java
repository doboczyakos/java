package com.example.eloadas;

import com.example.eloadas.hulladekSzallitas.Lakig;
import com.example.eloadas.hulladekSzallitas.Szolgaltatas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.ZoneId;
import java.util.Date;

public class CreateController {
    private final SessionFactory sessionFactory;

    @FXML
    private ComboBox<Szolgaltatas> szolgaltatasComboBox;

    @FXML
    private DatePicker igenyField;

    @FXML
    private TextField mennyisegField;

    @FXML
    private StackPane contentPane;

    public CreateController() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @FXML
    public void initialize() {
        try (Session session = sessionFactory.openSession()) {
            ObservableList<Szolgaltatas> szolgaltatasok = FXCollections.observableArrayList(
                    session.createQuery("from Szolgaltatas", Szolgaltatas.class).list()
            );
            szolgaltatasComboBox.setItems(szolgaltatasok);
        }

        szolgaltatasComboBox.setCellFactory(param -> szolgaltatasComboBoxSetup());
        szolgaltatasComboBox.setButtonCell(szolgaltatasComboBoxSetup());
    }

    private static ListCell<Szolgaltatas> szolgaltatasComboBoxSetup() {
        return new ListCell<>() {
            @Override
            protected void updateItem(Szolgaltatas item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getJelentes());
                }
            }
        };
    }

    @FXML
    private void addRecord() throws Exception {
        Szolgaltatas selectedSzolgaltatas = szolgaltatasComboBox.getValue();
        if (selectedSzolgaltatas != null) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                var newLakig = new Lakig();
                newLakig.setSzolgid(selectedSzolgaltatas.getId());
                newLakig.setIgeny(Date.from(igenyField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                newLakig.setMennyiseg(Integer.parseInt(mennyisegField.getText()));

                session.save(newLakig);
                session.getTransaction().commit();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mentés sikeres");
                alert.setHeaderText(null);
                alert.setContentText("A rekordot sikeresen mentettük!");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hiba történt");
                alert.setHeaderText(null);
                alert.setContentText("Hibás adatbevitel. Ellenőrizze az adatokat!");
                alert.showAndWait();
            }
        }
    }
}
