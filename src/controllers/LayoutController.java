package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class LayoutController {
    @FXML
    protected GridPane gridPane;

    @FXML
    protected TextField searchInput;

    @FXML
    void handleLinkToSitePage(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/site.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleLinkToEventPage(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/event.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void handleLinkToFesPage(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/fes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void handleLinkToPersonPage(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/person.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void handleLinkToPeriodPage(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/period.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

}
