package controllers.festival;

import com.google.gson.Gson;
import controllers.LayoutController;
import controllers.ListPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Event;
import models.Festival;
import models.Person;
import models.Place;
import utils.CreateNode;
import utils.MatchingData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class FestivalController extends LayoutController implements Initializable, ListPage<Festival> {

    private List<Festival> festivals = new ArrayList<>();

    public void setFestivals(List<Festival> festivals) {
        this.festivals = festivals;
    }

    @Override
    // This method is used to get data from json file.
    public void getData() {
        Gson gson = new Gson();
        Festival[] list;
        try {
            FileReader reader = new FileReader("src/data/lehoi.json");
            list = gson.fromJson(reader, Festival[].class);
            List<Festival> festivalList = Arrays.asList(list);
            setFestivals(festivalList);
            for (Festival festival : festivalList) {
                List<Person> relatedPerson = MatchingData.getRelatedPerson(festival.getTenNhanVat());
                festival.setNhanVat(relatedPerson);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    // A method to show list of festivals.
    public void showList(List<Festival> festivalList) {
        int col = 0;
        int row = 1;
        try {
            for (Festival festival : festivalList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/fes-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                FestivalItemController festivalItemController = fxmlLoader.getController();
                festivalItemController.setData(festival);

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
        List<Festival> searchList = new ArrayList<>();
        for (Festival festival : festivals) {
            if (festival.getTenLeHoi().toLowerCase().contains(textInput.toLowerCase())) {
                searchList.add(festival);
            }
        }
        gridPane.getChildren().clear();
        if (searchList.size() == 0) {
            Label noFound = CreateNode.createNoFound();
            gridPane.add(noFound, 2, 1);
        } else {
            showList(searchList);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
        showList(this.festivals);
    }
}
