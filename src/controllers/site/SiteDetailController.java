package controllers.site;

import controllers.DetailController;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import models.Site;
import utils.CreateNode;

import java.net.URL;
import java.util.ResourceBundle;

public class SiteDetailController extends DetailController implements Initializable {

    private Site site;

    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public void showDetail() {
        boxDetail.getChildren().add(CreateNode.createHBox("Tên di tích", site.getTenDiTich()));
        boxDetail.getChildren().add(CreateNode.createHBox("Địa điểm", site.getDiaDiem()));
        boxDetail.getChildren().add(CreateNode.createHBox("Ngày công nhận", site.getNgayCongNhan()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            setBackLink("../../views/site.fxml");
            showDetail();
        });
    }
}
