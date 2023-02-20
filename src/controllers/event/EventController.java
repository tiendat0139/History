package controllers.event;

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
import models.Person;
import models.Place;
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

public class EventController extends LayoutController implements Initializable, ListPage<Event<Person, Place>> {

    private List<Event<Person, Place>> events = new ArrayList<>();

    private static final Type EVENT_LIST_TYPE = new TypeToken<List<Event<String, String>>>(){}.getType();


    public void setEvents(List<Event<Person, Place>> events) {
        this.events = events;
    }

    @Override
    public void getData() {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("src/data/sukien.json");
            List<Event<String, String>> events1 = gson.fromJson(reader, EVENT_LIST_TYPE);
            List<Event<Person, Place>> events2 = new ArrayList<>();
            for (Event<String, String> event : events1) {
                List<Person> relatedPerson = MatchingData.getRelatedPerson(event.getNhanVat());
                Place place = MatchingData.getRelatedPlace(event.getDiaDiem());
                Event<Person, Place> eventItem = new Event<>(event.getTenSuKien(), event.getThoiGian(),
                        place, event.getDienBien(),relatedPerson);
                events2.add(eventItem);
            }
            setEvents(events2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showList(List<Event<Person, Place>> eventList) {
        int col = 0;
        int row = 1;
        try {
            for (Event<Person, Place> event: eventList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/event-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                EventItemController eventItemController = fxmlLoader.getController();
                eventItemController.setData(event);

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
    public void handleClickSearchButton(MouseEvent e) {
        String textInput = searchInput.getText().toLowerCase();
        List<Event<Person, Place>> searchList = new ArrayList<>();
        for (Event<Person, Place> event : events) {
            if (event.getTenSuKien().toLowerCase().contains(textInput.toLowerCase())) {
                searchList.add(event);
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
        showList(this.events);
    }
}
