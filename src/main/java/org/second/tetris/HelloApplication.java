package org.second.tetris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.second.tetris.entity.User;
import org.second.tetris.utils.UserUtils;

import java.io.*;

/**
 * 主界面
 *
 * @author 柴福林
 * @version 1.0
 */
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 500);
        stage.setTitle("俄罗斯方块");
        stage.setScene(scene);
        stage.show();
        //如果没有该用户即创建一个文件存储信息。
        try{
            File newfile = new File("C:\\user.txt");
            if(!newfile.exists()){
                newfile.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt",true));
                writer.write(UserUtils.generateName());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}