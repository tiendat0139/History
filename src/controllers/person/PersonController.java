package controllers.person;

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
import models.Person;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PersonController extends LayoutController implements Initializable, ListPage<Person> {

    private List<Person> persons = new ArrayList<>();

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    // This method is used to get data from json file and store it in a list.
    public void getData() {
        Gson gson = new Gson();
        Person[] list;
        try {
            FileReader reader = new FileReader("src/data/nhanvat.json");
            list = gson.fromJson(reader, Person[].class);
            List<Person> personList = Arrays.asList(list);
            setPersons(personList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    // A method to show list of person
    public void showList(List<Person> personList) {
        int col = 0;
        int row = 1;
        try {
            for (Person person : personList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/person-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PersonItemController personItemController = fxmlLoader.getController();
                personItemController.setData(person);

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
        List<Person> searchList = new ArrayList<>();
        for (Person person : persons) {
            if (person.getTen().toLowerCase().contains(textInput.toLowerCase())) {
                searchList.add(person);
            }
        }
        gridPane.getChildren().clear();
        if (searchList.size() == 0) {
            Label noFound = new Label();
            noFound.setMinWidth(1000);
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
        showList(this.persons);
    }
}
