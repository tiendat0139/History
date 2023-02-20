package controllers.festival;

import controllers.DetailController;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import models.Festival;
import models.Person;
import utils.CreateNode;

import java.net.URL;
import java.util.ResourceBundle;

public class FestivalDetailController extends DetailController implements Initializable {

    private Festival<Person> festival;

    public void setFestival(Festival<Person> festival) {
        this.festival = festival;
    }

    @Override
    public void showDetail() {
        boxDetail.getChildren().add(CreateNode.createHBox("Tên lễ hội", festival.getTenLeHoi()));
        boxDetail.getChildren().add(CreateNode.createHBox("Ngày bắt đầu", festival.getNgayBatDau()));
        boxDetail.getChildren().add(CreateNode.createHBox("Địa điểm", festival.getDiaDiem()));
        boxDetail.getChildren().add(CreateNode.createHBox("Lần đầu tổ chức", festival.getLanDauToChuc()));
        showRelatedPersonList(festival.getNhanVat(), 3);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            setBackLink("../../views/fes.fxml");
            showDetail();
        });
    }


}
