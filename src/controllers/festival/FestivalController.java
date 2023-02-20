package controllers.festival;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import utils.CreateNode;
import utils.MatchingData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FestivalController extends LayoutController implements Initializable, ListPage<Festival<Person>> {

    private List<Festival<Person>> festivals = new ArrayList<>();

    private static final Type FES_LIST_TYPE = new TypeToken<List<Festival<String>>>(){}.getType();

    public void setFestivals(List<Festival<Person>> festivals) {
        this.festivals = festivals;
    }


    // This method is used to get data from json file.
    @Override
    public void getData() {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("src/data/lehoi.json");
            List<Festival<String>> festivals1 = gson.fromJson(reader, FES_LIST_TYPE);
            List<Festival<Person>> festivals2 = new ArrayList<>();
            for (Festival<String> festival : festivals1) {
                List<Person> relatedPerson = MatchingData.getRelatedPerson(festival.getNhanVat());
                Festival<Person> fesItem = new Festival<>(festival.getTenLeHoi(), festival.getNgayBatDau(),
                        festival.getLanDauToChuc(), festival.getDiaDiem(), relatedPerson);
                festivals2.add(fesItem);
            }
            setFestivals(festivals2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    // A method to show list of festivals.
    public void showList(List<Festival<Person>> festivalList) {
        int col = 0;
        int row = 1;
        try {
            for (Festival<Person> festival : festivalList) {
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
        List<Festival<Person>> searchList = new ArrayList<>();
        for (Festival<Person> festival : festivals) {
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
