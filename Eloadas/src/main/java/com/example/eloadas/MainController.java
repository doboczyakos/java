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
    private void loadOandaView() throws Exception {
        loadView("account.fxml");
    }

    @FXML
    private void loadDevizaView() throws Exception {
        loadView("devizapar.fxml");
    }

    @FXML
    private void loadDevizaOldView() throws Exception {
        loadView("devizaold.fxml");
    }

    @FXML
    private void loadOpenView() throws Exception {
        loadView("open.fxml");
    }

    @FXML
    private void loadCloseView() throws Exception {
        loadView("close.fxml");
    }

    @FXML
    private void loadPositionsView() throws Exception {
        loadView("positions.fxml");
    }

    @FXML
    private void loadMnbView() throws Exception {
        loadView("mnb.fxml");
    }

    private void loadView(String fxml) throws Exception {
        Node view = FXMLLoader.load(getClass().getResource(fxml));
        contentPane.getChildren().setAll(view);
    }
}
