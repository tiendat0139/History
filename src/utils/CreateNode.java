package utils;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CreateNode {
    //create one row in detail table
    public static HBox createHBox (String name, String content) {
        HBox rowDetail = new HBox();
        String labelStyle = "-fx-font-family: \"Cambria\";\n" +
                "-fx-font-size: 16;\n" +
                "-fx-text-fill: #823a17;\n";

        Label nameLabel = new Label();
        nameLabel.setPrefWidth(200);
        nameLabel.setMinWidth(200);
        nameLabel.setWrapText(true);
        nameLabel.setText(name + ":");
        nameLabel.setStyle(labelStyle + "-fx-font-weight: bold;");

        Label contentLabel = new Label();
        contentLabel.setWrapText(true);
        contentLabel.setText(content);
        contentLabel.setStyle(labelStyle);

        rowDetail.getChildren().add(nameLabel);
        rowDetail.getChildren().add(contentLabel);
        return rowDetail;
    }

    public static Label createNoFound() {
        Label noFound = new Label();
        noFound.setMinWidth(1000);
        noFound.setAlignment(Pos.CENTER);
        noFound.setText("Không có kết quả tìm thấy");
        noFound.setStyle("-fx-text-fill: #823a17; -fx-font-size: 16;");
        return noFound;
    }
}
