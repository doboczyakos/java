package com.example.eloadas;

import com.example.eloadas.hulladekSzallitas.Lakig;
import com.example.eloadas.hulladekSzallitas.Szolgaltatas;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.SimpleDateFormat;
import java.util.List;

public class Read2Controller {

    private final SessionFactory sessionFactory;
    @FXML
    private TextField filterText;
    @FXML
    private ComboBox<Szolgaltatas> szolgaltatasComboBox;
    @FXML
    private ToggleGroup mennyisegToggleGroup;
    @FXML
    private CheckBox checkBoxMoreThan5;
    @FXML
    private TableView<Lakig> table;

    @FXML
    private TableColumn<Lakig, String> igenyColumn;

    @FXML
    private TableColumn<Lakig, String> szolgColumn;

    @FXML
    private TableColumn<Lakig, Integer> mennyisegColumn;

    public Read2Controller() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @FXML
    public void initialize() {
        igenyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(new SimpleDateFormat().getDateInstance(SimpleDateFormat.SHORT).format(cellData.getValue().getIgeny())));
        szolgColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSzolgaltatas().getJelentes()));
        mennyisegColumn.setCellValueFactory(new PropertyValueFactory<>("mennyiseg"));

        try (Session session = sessionFactory.openSession()) {
            List<Szolgaltatas> szolgaltatasList = session.createQuery("FROM Szolgaltatas", Szolgaltatas.class).getResultList();
            szolgaltatasComboBox.setItems(FXCollections.observableArrayList(szolgaltatasList));
        }

        szolgaltatasComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Szolgaltatas item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getJelentes());
                }
            }
        });

        szolgaltatasComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Szolgaltatas item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getJelentes());
                }
            }
        });
    }

    @FXML
    private void applyFilter() {
        String filter = filterText.getText();
        var szolgaltatasItem = szolgaltatasComboBox.getSelectionModel().getSelectedItem();
        Integer szolgid = szolgaltatasItem != null ? szolgaltatasItem.getId() : null;
        var selectedMennyisegRadio = (RadioButton)mennyisegToggleGroup.getSelectedToggle();

        ObservableList<Lakig> data = FXCollections.observableArrayList();

        boolean isFilterAdded = false;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Lakig l";
            if (filter != null && !filter.isEmpty()) {
                hql += " WHERE ";
                isFilterAdded = true;
                hql += "l.szolgaltatas.jelentes LIKE :filter";
            }
            if (szolgid != null) {
                hql += isFilterAdded ? " AND " : " WHERE ";
                isFilterAdded = true;
                hql += "l.szolgaltatas.id = :szolgid";
            }
            if (checkBoxMoreThan5.isSelected()) {
                hql += isFilterAdded ? " AND " : " WHERE ";
                isFilterAdded = true;
                hql += "l.mennyiseg > 5";
                mennyisegToggleGroup.selectToggle(null);
            } else if (selectedMennyisegRadio != null) {
                hql += isFilterAdded ? " AND " : " WHERE ";
                isFilterAdded = true;
                hql += "l.mennyiseg = :mennyiseg";
            }

            var query = session.createQuery(hql, Lakig.class);
            if (filter != null && !filter.isEmpty())
                query.setParameter("filter", "%" + filter + "%");
            if (szolgid != null)
                query.setParameter("szolgid", szolgid);
            if (!checkBoxMoreThan5.isSelected() && selectedMennyisegRadio != null)
                query.setParameter("mennyiseg", Integer.parseInt(selectedMennyisegRadio.getText()));

            data.addAll(query.getResultList());
        }

        table.setItems(data);
    }
}
