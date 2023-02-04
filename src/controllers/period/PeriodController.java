package controllers.period;

import com.google.gson.Gson;
import controllers.LayoutController;
import controllers.ListPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Period;
import models.Person;
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

public class PeriodController extends LayoutController implements Initializable, ListPage<Period> {

    private List<Period> periods = new ArrayList<>();

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    @Override
    public void getData() {
        Gson gson = new Gson();
        Period[] list;
        try {
            FileReader reader = new FileReader("src/data/thoiki.json");
            list = gson.fromJson(reader, Period[].class);
            List<Period> periodList = Arrays.asList(list);
            setPeriods(periodList);
            for (Period period : periodList) {
                List<Person> relatedPerson = MatchingData.getPersonByPeriodName(period.getTenTrieuDai());
                period.setNhanVat(relatedPerson);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showList(List<Period> periodList) {
        int col = 0;
        int row = 1;
        try {
            for (Period period : periodList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/period-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PeriodItemController periodItemController = fxmlLoader.getController();
                periodItemController.setData(period);

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
        List<Period> searchList = new ArrayList<>();
        for (Period period : periods) {
            if (period.getTenTrieuDai().toLowerCase().contains(textInput.toLowerCase())) {
                searchList.add(period);
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
        showList(this.periods);
    }
}
