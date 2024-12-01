package com.example.eloadas;

import com.example.eloadas.hulladekSzallitas.Lakig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteController {
    private final SessionFactory sessionFactory;

    @FXML
    private ComboBox<Lakig> recordComboBox;

    public DeleteController() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @FXML
    public void initialize() {
        try (Session session = sessionFactory.openSession()) {
            ObservableList<Lakig> records = FXCollections.observableArrayList(session.createQuery("from Lakig", Lakig.class).list());
            recordComboBox.setItems(records);
        }

        recordComboBox.setCellFactory(param -> recordComboBoxSetup());
        recordComboBox.setButtonCell(recordComboBoxSetup());
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

    @FXML
    private void deleteRecord() {
        Lakig selected = recordComboBox.getValue();
        if (selected != null) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.delete(selected);
                session.getTransaction().commit();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mentés sikeres");
                alert.setHeaderText(null);
                alert.setContentText("A rekordot sikeresen töröltük!");
                alert.showAndWait();

                recordComboBox.getItems().remove(selected);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hiba történt");
                alert.setHeaderText(null);
                alert.setContentText("Hibás történt a törlés közben!");
                alert.showAndWait();
            }
        }
    }
}
