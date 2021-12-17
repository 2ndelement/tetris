package org.second.tetris.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.second.tetris.HelloApplication;
import org.second.tetris.test;

import java.io.IOException;

/**
 * 首页控制类
 *
 * @author 孙士杰
 * @version 1.0
 */
public class HelloController {
    @FXML
    private Label homePage;

    @FXML
    protected void onHelloButtonClick() {
        homePage.setText("欢迎体验俄罗斯方块！");
    }

    /**单人模式按钮 还需要改单机后效果*/
    @FXML
    protected void onSinglePlayerModeClick() throws IOException{
        homePage.setText("进入单机模式");
        HelloApplication.close();//调出单机模式界面关闭主界面。
        test test = new test();
        Stage stage = new Stage();
        test.start(stage);
        //gotoSinglePlayerMode();
    }

    /*得分记录按钮 */
    @FXML
    protected void onScoreRecordClick() {
        homePage.setText("显示得分");}

    /*游戏介绍*/
    @FXML
    protected void onGameIntroductionClick() {
        homePage.setText("这个是游戏介绍");}

    /**
     * 窗口跳转
     *
     */
    /*public static void gotoSinglePlayerMode(){
        Stage singlePlayer = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(HelloApplication.class.getResource("singlePlayer-view.fxml"));
        } catch (IOException e){
            e.printStackTrace();
        }
        singlePlayer.setTitle("单人游戏");
        singlePlayer.setScene(new Scene(root));
        singlePlayer.show();
    }*/
}