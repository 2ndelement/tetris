package org.second.tetris;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.second.tetris.entity.User;
import org.second.tetris.utils.UserUtils;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 主界面
 *
 * @author 柴福林
 * @version 1.0
 *
 */
public class HelloApplication extends Application {
    private static Stage pristage;
    private static User user;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 660);
        stage.setTitle("test俄罗斯方块");
        stage.setScene(scene);
        stage.setResizable(false);//禁止用户修改窗口大小
        stage.show();
        pristage = stage;
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        //如果没有该用户即创建一个文件存储信息。

        try{
            File newfile = new File("user.txt");
            //查看该路径是否有该文件。
            if (!newfile.exists()) {
                newfile.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(newfile, true));
                String string = UserUtils.generateName();
                user = new User(string);

                writer.write(string + "\n");

                writer.flush();
                writer.close();
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(newfile));
                String string = reader.readLine();
                user = new User(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(){
        return user;
    }

    /**
     * 增加close方法使得界面可以关闭。
     */
    public static void close() {
        pristage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}