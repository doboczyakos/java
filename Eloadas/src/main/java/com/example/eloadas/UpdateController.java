package com.example.eloadas;

import com.example.eloadas.hulladekSzallitas.Lakig;
import com.example.eloadas.hulladekSzallitas.Szolgaltatas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.ZoneId;
import java.util.Date;

public class UpdateController {
    private final SessionFactory sessionFactory;

    @FXML
    private ComboBox<Lakig> recordComboBox;

    @FXML
    private ComboBox<Szolgaltatas> szolgaltatasComboBox;

    @FXML
    private DatePicker igenyField;

    @FXML
    private TextField mennyisegField;

    public UpdateController() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @FXML
    public void initialize() {
        try (Session session = sessionFactory.openSession()) {
            ObservableList<Lakig> records = FXCollections.observableArrayList(
                    session.createQuery("from Lakig", Lakig.class).list()
            );
            recordComboBox.setItems(records);
        }

        try (Session session = sessionFactory.openSession()) {
            ObservableList<Szolgaltatas> szolgaltatasok = FXCollections.observableArrayList(
                    session.createQuery("from Szolgaltatas", Szolgaltatas.class).list()
            );
            szolgaltatasComboBox.setItems(szolgaltatasok);
        }

        recordComboBox.valueProperty().addListener((obs, oldRecord, newRecord) -> {
            if (newRecord != null) {
                szolgaltatasComboBox.getSelectionModel().select(
                        szolgaltatasComboBox.getItems().stream()
                                .filter(s -> s.getId().equals(newRecord.getSzolgid()))
                                .findFirst()
                                .orElse(null)
                );
                igenyField.setValue(newRecord.getIgeny().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                mennyisegField.setText(String.valueOf(newRecord.getMennyiseg()));
            }
        });

        recordComboBox.setCellFactory(param -> recordComboBoxSetup());
        recordComboBox.setButtonCell(recordComboBoxSetup());

        szolgaltatasComboBox.setCellFactory(param -> szolgaltatasComboBoxSetup());
        szolgaltatasComboBox.setButtonCell(szolgaltatasComboBoxSetup());
    }

    private static ListCell<Lakig> recordComboBoxSetup() {
        return new ListCell<>() {
            @Override
            protected void updateItem(Lakig item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getAzon().toString());
                }
            }
        };
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
    private void updateRecord() {
        Lakig selected = recordComboBox.getValue();
        Szolgaltatas selectedSzolgaltatas = szolgaltatasComboBox.getValue();

        if (selected != null && selectedSzolgaltatas != null) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                selected.setSzolgid(selectedSzolgaltatas.getId());
                selected.setIgeny(Date.from(igenyField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                selected.setMennyiseg(Integer.parseInt(mennyisegField.getText()));

                session.update(selected);
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
