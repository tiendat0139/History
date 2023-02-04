package controllers.site;

import com.google.gson.Gson;
import controllers.LayoutController;
import controllers.ListPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Site;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class SiteController extends LayoutController implements Initializable, ListPage<Site> {

    private List<Site> sites = new ArrayList<>();

    public void setPersons(List<Site> sites) {
        this.sites = sites;
    }

    @Override
    // This method is used to get data from json file.
    public void getData() {
        Gson gson = new Gson();
        Site[] list;
        try {
            FileReader reader = new FileReader("src/data/ditich.json");
            list = gson.fromJson(reader, Site[].class);
            List<Site> siteList = Arrays.asList(list);
            setPersons(siteList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    // A method to show list of sites.
    public void showList(List<Site> siteList) {
        int col = 0;
        int row = 1;
        try {
            for (Site site : siteList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/site-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                SiteItemController siteItemController = fxmlLoader.getController();
                siteItemController.setData(site);

                if (col == 3) {
                    col = 0;
                    row++;
                }
                gridPane.add(anchorPane, col++, row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleClickSearchButton(MouseEvent event) {
        String textInput = searchInput.getText().toLowerCase();
        List<Site> searchList = new ArrayList<>();
        for (Site site : sites) {
            if (site.getTenDiTich().toLowerCase().contains(textInput.toLowerCase())) {
                searchList.add(site);
            }
        }
        gridPane.getChildren().clear();
        if (searchList.size() == 0) {
            Label noFound = new Label();
            noFound.setMinWidth(500);
            noFound.setAlignment(Pos.CENTER);
            noFound.setText("Không có kết quả tìm thấy");
            noFound.setStyle("-fx-text-fill: #823a17; -fx-font-size: 16;");
            gridPane.add(noFound,2,1);
        } else {
            showList(searchList);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
        showList(this.sites);
    }
}
