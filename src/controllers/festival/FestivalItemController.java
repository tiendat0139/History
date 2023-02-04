package controllers.festival;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Festival;

import java.io.IOException;

public class FestivalItemController {

    @FXML
    private Label locationLabel;

    @FXML
    private Label nameLabel;

    private Festival festival;

    public void setData(Festival festival) {
        this.festival = festival;
        nameLabel.setText(festival.getTenLeHoi());
        locationLabel.setText(festival.getDiaDiem());
    }

    public Festival getFestival() {
        return festival;
    }

    @FXML
    public void handleItemClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/fes-detail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);

        FestivalDetailController festivalDetailController = fxmlLoader.getController();
        festivalDetailController.setFestival(this.getFestival());

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
