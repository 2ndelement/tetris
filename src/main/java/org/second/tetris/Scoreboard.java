package org.second.tetris;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
/**
 * 实现从设备中的用户信息文档中读取信息生成得分记录面板。
 * @author 柴福林
 * @version 1.0
 */
public class Scoreboard extends Application {

    public static final int width = 1300;
    public static final int height = 660;

    private TableView<scorecord> table = new TableView<scorecord>();
    private final ObservableList<scorecord> data =
            FXCollections.observableArrayList();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("得分记录");
        stage.setWidth(width);
        stage.setHeight(height);
        try{
            //"C:\\AppData\\user.txt"为用户信息文档，主界面时生成，用户每添加一次记录，在文件中添加记录信息。读取信息
            BufferedReader reader = new BufferedReader(new FileReader("src\\user.txt"));
            String user = reader.readLine();
            String record = reader.readLine();
            while(record!=null) {
                StringTokenizer str = new StringTokenizer(record, " ");
                data.add(new scorecord(str.nextToken(),str.nextToken()));
                record = reader.readLine();
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }

        TableColumn firstNameCol = new TableColumn("Score");
        firstNameCol.setMinWidth(width/2);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<scorecord, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Date");
        lastNameCol.setMinWidth(width/2);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<scorecord, String>("lastName"));
        if(data!=null){
            table.setItems(data);
        }

        table.getColumns().addAll(firstNameCol, lastNameCol);

        ((Group) scene.getRoot()).getChildren().addAll(table);

        stage.setScene(scene);
        stage.setResizable(false);//禁止用户修改窗口大小
        stage.show();
    }

    /**
     * 这个类用来实现ObservableList<scorecord>数组。
     */
    public static class scorecord {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;

        private scorecord(String fName, String lName) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

    }
}  