package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class Controller implements Initializable {

    @FXML
    private WebView Webview; // Reference to the WebView component in Scene FXML file

    @FXML
    private TextField SearchBar; // Reference to the search bar (TextField) in Scene FXML file

    @FXML
    private AnchorPane Anchor; // Reference to the AnchorPane containing your UI components

    private double scale = 1; // Initial zoom scale
    private WebEngine engine; // WebEngine for controlling WebView
    private String homepage = "www.google.com"; // Default homepage URL
    private WebHistory history; // WebHistory for navigation history

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Called when the FXML is loaded and the controller is initialized

        engine = Webview.getEngine(); // Initialize the WebEngine
        SearchBar.setText(homepage); // Set the default URL in the search bar
        LoadPage(); // Load the default page
    }

    public void LoadPage() {
        // Load a web page based on the URL entered in the search bar
        engine.load("https://" + SearchBar.getText());
    }

    public void reload() {
        // Reload the current web page
        engine.reload();
    }

    public void zoomin() {
        // Zoom in on the web page
        scale += 0.25;
        scale = scale > 2 ? 2 : scale; // Limit the maximum zoom
        Webview.setZoom(scale);
    }

    public void zoomout() {
        // Zoom out from the web page
        scale -= 0.25;
        scale = scale < 0.5 ? 0.5 : scale; // Limit the minimum zoom
        Webview.setZoom(scale);
    }

    public void back() {
        // Navigate back in the web page history
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(-1); // Go back one step in history
        SearchBar.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    public void forward() {
        // Navigate forward in the web page history
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(1); // Go forward one step in history
        SearchBar.setText(entries.get(history.getCurrentIndex()).getUrl());
    }
}
