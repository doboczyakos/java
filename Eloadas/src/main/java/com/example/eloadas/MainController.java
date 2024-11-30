package com.example.eloadas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;


public class MainController {
    @FXML
    private StackPane contentPane;

    @FXML
    private void loadReadView() throws Exception {
        loadView("read.fxml");
    }

    @FXML
    private void loadRead2View() throws Exception {
        loadView("read2.fxml");
    }

    @FXML
    private void loadCreateView() throws Exception {
        loadView("create.fxml");
    }

    @FXML
    private void loadUpdateView() throws Exception {
        loadView("update.fxml");
    }

    @FXML
    private void loadDeleteView() throws Exception {
        loadView("delete.fxml");
    }

    @FXML
    private void loadParallelView() throws Exception {
        loadView("parallel.fxml");
    }

    @FXML
    private void loadDown1View() throws Exception {
        loadView("down1.fxml");
    }

    @FXML
    private void loadDown2View() throws Exception {
        loadView("down2.fxml");
    }

    @FXML
    private void loadGrafView() throws Exception {
        loadView("graf.fxml");
    }

    private void loadView(String fxml) throws Exception {
        Node view = FXMLLoader.load(getClass().getResource(fxml));
        contentPane.getChildren().setAll(view);
    }
}