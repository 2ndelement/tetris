package org.second.tetris.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label homePage;

    @FXML
    protected void onHelloButtonClick() {
        homePage.setText("欢迎体验俄罗斯方块！");
    }

    /*单人模式按钮 还需要改单机后效果*/
    @FXML
    protected void onSinglePlayerModeClick() {
        homePage.setText("进入单机模式");}

    /*得分记录按钮 */
    @FXML
    protected void onScoreRecordClick() {
        homePage.setText("显示得分");}

    /*游戏介绍*/
    @FXML
    protected void onGameIntroductionClick() {
        homePage.setText("这个是游戏介绍");}
}