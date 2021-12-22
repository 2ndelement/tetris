package org.second.tetris.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.second.tetris.HelloApplication;
import org.second.tetris.Introduction;
import org.second.tetris.Scoreboard;
import org.second.tetris.Tetris;

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

    /**
     * 单人模式按钮 还需要改单机后效果
     */
    @FXML

    protected void onSinglePlayerModeClick() throws Exception {
        HelloApplication.close();//调出单机模式界面关闭主界面。

        Tetris test = new Tetris();
        Stage stage = new Stage();
        test.start(stage);

    }

    /*
    *多人模式按钮
     */
    @FXML
    protected void onMultiplePlayerModeClick() throws Exception{
        homePage.setText("进入多人模式");
    }

    /*得分记录按钮 */
    @FXML
    protected void onScoreRecordClick() throws Exception {
        homePage.setText("显示得分");
        HelloApplication.close();
        Scoreboard scorebord = new Scoreboard();

        Stage stage = new Stage();
        scorebord.start(stage);
    }

    /*游戏介绍*/
    @FXML
    protected void onGameIntroductionClick() throws Exception {
        HelloApplication.close();

        Introduction test = new Introduction();
        Stage stage = new Stage();
        test.start(stage);
    }
}