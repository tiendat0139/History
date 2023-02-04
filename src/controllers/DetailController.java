package controllers;

import controllers.person.PersonItemController;
import controllers.place.PlaceItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Person;
import models.Place;

import java.io.IOException;
import java.util.List;

public abstract class DetailController extends LayoutController{

    @FXML
    protected HBox backButton;

    @FXML
    protected VBox boxDetail;

    @FXML
    private GridPane personGridPane;

    @FXML
    private GridPane placeGridPane;

    protected String backLink;

    public void setBackLink(String backLink) {
        this.backLink = backLink;
    }

    @FXML
    // A method that is called when the back button is clicked. It loads the previous scene.
    public void handleBack(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(backLink));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public abstract void showDetail();

    /**
     * It takes a list of Person objects, creates a new AnchorPane for each Person object, and adds the AnchorPane to the
     * GridPane
     *
     * @param relatedPerson List of Person objects
     */
    public void showRelatedPersonList(List<Person> relatedPerson, int numberPersonInRow) {
        int col = 0;
        int row = 1;

        try {
            for (Person person : relatedPerson) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/person-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PersonItemController personItemController = fxmlLoader.getController();
                personItemController.setData(person);

                if (col == numberPersonInRow) {
                    col = 0;
                    row++;
                }
                personGridPane.add(anchorPane, col++, row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * It loads the place-item.fxml file, sets the data for the controller, and adds the loaded fxml file to the
     * placeGridPane
     *
     * @param place The place object that contains the data to be displayed.
     */
    public void showRelatedPlaceList(Place place) {
        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/place-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PlaceItemController placeItemController = fxmlLoader.getController();
                placeItemController.setData(place);

                placeGridPane.add(anchorPane, 1, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
