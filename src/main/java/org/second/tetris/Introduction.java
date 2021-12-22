package org.second.tetris;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.second.tetris.*;

import java.io.*;

/**
 * description   TODO
 *
 * @author LongRongtao
 */
public class Introduction extends Application {
	public int WIDTH = 1300;
	public int HEIGHT = 660;
	private static Stage pristage;

	public void start(Stage stage)  {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(Introduction.class.getResource("introduction-view.fxml"));
			Scene scene = new Scene(fxmlLoader.load(),WIDTH,HEIGHT);
			stage.setTitle("游戏介绍");
			stage.setScene(scene);
			stage.setResizable(false);//禁止用户修改窗口大小
			stage.show();

			pristage = stage;
		}catch (Exception e){
			e.printStackTrace();
		}


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