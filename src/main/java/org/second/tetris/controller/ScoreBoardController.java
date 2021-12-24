package org.second.tetris.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.second.tetris.utils.ScoreOfTable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * description   TODO
 *
 * @author 孙士杰
 */
public class ScoreBoardController implements Initializable {
    public TableView<ScoreOfTable> tableview;

    public TableColumn<ScoreOfTable, Integer> score;
    public TableColumn<ScoreOfTable, String> date;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        score.setCellValueFactory(new PropertyValueFactory<>("Score"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tableview.setItems(observableList);
    }

    ObservableList<ScoreOfTable> observableList = FXCollections.observableArrayList(
            new ScoreOfTable(10, "12-22")
    );
}