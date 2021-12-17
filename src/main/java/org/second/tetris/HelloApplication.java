package org.second.tetris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.second.tetris.utils.UserUtils;

import java.io.*;

public class HelloApplication extends Application {
    private UserUtils user;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 500);
        stage.setTitle("俄罗斯方块");
        stage.setScene(scene);
        stage.show();
        /**
            创建路径，如果没有该文件，则新生成一个。
         */
        File newfile = new File("C:\\user.txt");
        if(!newfile.exists()){
            newfile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\user.txt",true));
            user = new UserUtils();
            writer.write(user.generateName());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}