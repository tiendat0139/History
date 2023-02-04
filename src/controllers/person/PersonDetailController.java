package controllers.person;

import controllers.DetailController;
import javafx.application.Platform;

import javafx.fxml.Initializable;
import models.Person;
import utils.CreateNode;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonDetailController extends DetailController implements Initializable {

    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void showDetail() {
        if (person.getTen() != null){
            boxDetail.getChildren().add(CreateNode.createHBox("Tên", person.getTen()));
        }
        if (person.getSinh() != null){
            boxDetail.getChildren().add(CreateNode.createHBox("Sinh", person.getSinh()));
        }
        if (person.getMat()!= null) {
            boxDetail.getChildren().add(CreateNode.createHBox("Mất", person.getMat()));
        }
        if (person.getAnTang() != null) {
            boxDetail.getChildren().add(CreateNode.createHBox("An táng", person.getAnTang()));
        }
        if (person.getTenDayDu() != null){
            boxDetail.getChildren().add(CreateNode.createHBox("Tên đầy đủ", person.getTenDayDu()));
        }
        if (person.getTenHuy() != null){
            boxDetail.getChildren().add(CreateNode.createHBox("Tên húy", person.getTenHuy()));
        }
        if (person.getNienHieu() != null){
            boxDetail.getChildren().add(CreateNode.createHBox("Niên hiệu", person.getNienHieu()));
        }
        if (person.getMieuHieu() != null){
            boxDetail.getChildren().add(CreateNode.createHBox("Miếu hiệu", person.getMieuHieu()));
        }
        if (person.getTrieuDai() != null) {
            boxDetail.getChildren().add(CreateNode.createHBox("Triều đại", person.getTrieuDai()));
        }
        if (person.getTienNhiem() != null) {
            boxDetail.getChildren().add(CreateNode.createHBox("Tiền nhiệm", person.getTienNhiem()));
        }
        if (person.getKeNhiem() != null) {
            boxDetail.getChildren().add(CreateNode.createHBox("Kế nhiệm", person.getKeNhiem()));
        }
        if (person.getTaiVi() != null) {
            boxDetail.getChildren().add(CreateNode.createHBox("Tại vị", person.getTaiVi()));
        }
        if (person.getThanMau() != null) {
            boxDetail.getChildren().add(CreateNode.createHBox("Thân mẫu", person.getThanMau()));
        }
        if (person.getThanPhu() != null) {
            boxDetail.getChildren().add(CreateNode.createHBox("Phụ mẫu", person.getThanPhu()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            setBackLink("../../views/person.fxml");
            showDetail();
        });
    }


}
