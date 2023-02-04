package controllers.period;

import controllers.DetailController;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import models.Period;
import utils.CreateNode;

import java.net.URL;
import java.util.ResourceBundle;

public class PeriodDetailController extends DetailController implements Initializable {

    private Period period;

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public void showDetail() {
        boxDetail.getChildren().add(CreateNode.createHBox("Tên triều đại", period.getTenTrieuDai()));
        boxDetail.getChildren().add(CreateNode.createHBox("Ngày bắt đầu", period.getThoiGianTonTai()));
        showRelatedPersonList(period.getNhanVat(), 3);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            setBackLink("../../views/period.fxml");
            showDetail();
        });
    }


}
