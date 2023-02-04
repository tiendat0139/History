package controllers.place;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Place;

import java.io.IOException;

public class PlaceItemController {
    @FXML
    private Label nameLabel;

    private Place place;

    public void setData(Place place) {
        this.place = place;
        nameLabel.setText(place.getTenDiaDiem());
    }

    public Place getPlace() {
        return place;
    }

    @FXML
    public void handleItemClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/place-detail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);

        PlaceDetailController placeDetailController = fxmlLoader.getController();
        placeDetailController.setPlace(this.getPlace());

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
