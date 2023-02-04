package controllers;


import javafx.scene.input.MouseEvent;

import java.util.List;

public interface ListPage<T> {
    void getData();
    void showList(List<T> list);
    void handleClickSearchButton(MouseEvent event);
}
