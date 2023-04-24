package com.example.browser;

import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

public class BrowserController {
    @FXML
    private BorderPane root;
    @FXML
    private WebView webView;
    @FXML
    private TextField searchField;
    @FXML
    private TextField urlField;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;

    public void initialize() {
        // Load the Google search page on startup
        webView.getEngine().load("https://www.google.com/");

        // Update the URL text field as the web page changes
        webView.getEngine().locationProperty().addListener((obs, oldValue, newValue) -> {
            urlField.setText(newValue);
        });

        // Update the progress bar as the web page loads
        webView.getEngine().getLoadWorker().progressProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                progressBar.setProgress(newValue.doubleValue());
            }
        });

        // Hide the progress bar when the web page finishes loading
        webView.getEngine().getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                progressBar.setVisible(false);
            }
        });

        // Show the progress bar when the web page starts loading
        webView.getEngine().getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == Worker.State.RUNNING) {
                progressBar.setVisible(true);
            }
        });
    }

    @FXML
    private void search(ActionEvent event) {
        // Load the Google search results page
        String query = searchField.getText();
        String url = "https://www.google.com/search?q=" + query;
        webView.getEngine().load(url);
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Go back to the previous page
        if (webView.getEngine().getHistory().getCurrentIndex() > 0) {
            webView.getEngine().getHistory().go(-1);
        }
    }

    @FXML
    private void goHome(ActionEvent event) {
        // Go back to the Google search page
        webView.getEngine().load("https://www.google.com/");
    }

    @FXML
    private void goToURL(ActionEvent event) {
        // Load the specified
    }
}