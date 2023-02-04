package controllers.place;

import controllers.DetailController;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import models.Place;
import utils.CreateNode;

import java.net.URL;
import java.util.ResourceBundle;

public class PlaceDetailController extends DetailController implements Initializable {

    private Place place;

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public void showDetail() {
        boxDetail.getChildren().add(CreateNode.createHBox("Tên sự kiện", place.getTenDiaDiem()));
        boxDetail.getChildren().add(CreateNode.createHBox("Địa điểm", place.getDienBien()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            setBackLink("../../views/event.fxml");
            showDetail();
        });
    }
}
