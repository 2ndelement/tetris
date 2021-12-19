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
    private static Stage pristage;
    private User user;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 660);
        stage.setTitle("俄罗斯方块");
        stage.setScene(scene);
        stage.setResizable(false);//禁止用户修改窗口大小
        stage.show();
        pristage = stage;
        //如果没有该用户即创建一个文件存储信息。
        try{
            File newfile = new File("src\\user.txt");
            //查看该路径是否有该文件。
            if(!newfile.exists()){
                newfile.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(newfile));
                String string = UserUtils.generateName();
                user = new User(string);
                writer.write(string);
                writer.flush();
                writer.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public User getUser(){
        return this.user;
    }
    /**
     * 增加close方法使得界面可以关闭。
     */
    public static void close(){
        pristage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}