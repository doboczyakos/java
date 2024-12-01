package com.example.eloadas;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelController {
    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    private boolean running = true;

    private ExecutorService executorService;

    @FXML
    private void onStartButtonClicked() {
        startButton.setVisible(false);
        running = true;
        executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            int counter = 0;
            while (running) {
                int finalCounter = counter;
                Platform.runLater(() -> label1.setText("Label 1: " + finalCounter));
                counter++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        executorService.submit(() -> {
            int counter = 0;
            while (running) {
                int finalCounter = counter;
                Platform.runLater(() -> label2.setText("Label 2: " + finalCounter));
                counter++;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        stopButton.setVisible(true);
    }

    @FXML
    public void onStopButtonClicked() {
        stopButton.setVisible(false);
        running = false;
        executorService.shutdown();
        startButton.setVisible(true);
    }
}
