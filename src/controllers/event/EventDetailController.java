package controllers.event;

import controllers.DetailController;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import models.Event;
import utils.CreateNode;

import java.net.URL;
import java.util.ResourceBundle;

public class EventDetailController extends DetailController implements Initializable {

    private Event event;

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public void showDetail() {
        boxDetail.getChildren().add(CreateNode.createHBox("Tên sự kiện", event.getTenSuKien()));
        boxDetail.getChildren().add(CreateNode.createHBox("Địa điểm", event.getTenDiaDiem()));
        boxDetail.getChildren().add(CreateNode.createHBox("Diễn biến", event.getDienBien()));
        boxDetail.getChildren().add(CreateNode.createHBox("Thời gian", event.getThoiGian()));
        showRelatedPersonList(this.event.getNhanVat(), 1);
        showRelatedPlaceList(this.event.getDiaDiem());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            setBackLink("../../views/event.fxml");
            showDetail();
        });
    }
}
