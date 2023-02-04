package controllers.period;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Period;

import java.io.IOException;

public class PeriodItemController {
    @FXML
    private Label nameLabel;

    private Period period;

    public void setData(Period period) {
        this.period = period;
        nameLabel.setText(period.getTenTrieuDai());
    }

    public Period getPeriod() {
        return period;
    }

    @FXML
    public void handleItemClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/period-detail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);

        PeriodDetailController periodDetailController = fxmlLoader.getController();
        periodDetailController.setPeriod(this.getPeriod());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
