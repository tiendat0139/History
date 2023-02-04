package controllers.event;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Event;

import java.io.IOException;

public class EventItemController {


    @FXML
    private Label nameLabel;

    private Event event;

    public void setData(Event event) {
        this.event = event;
        nameLabel.setText(event.getTenSuKien());
    }

    public Event getEvent() {
        return event;
    }

    @FXML
    public void handleItemClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/event-detail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);

        EventDetailController eventDetailController = fxmlLoader.getController();
        eventDetailController.setEvent(this.getEvent());

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
