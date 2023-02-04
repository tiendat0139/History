package controllers.site;

import controllers.site.SiteDetailController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Site;

import java.io.IOException;

public class SiteItemController {
    @FXML
    private Label nameLabel;

    private Site site;

    public void setData(Site site) {
        this.site = site;
        nameLabel.setText(site.getTenDiTich());
    }

    public Site getSite() {
        return site;
    }

    @FXML
    void handleItemClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/site-detail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);

        SiteDetailController siteDetailController = fxmlLoader.getController();
        siteDetailController.setSite(this.getSite());

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}


