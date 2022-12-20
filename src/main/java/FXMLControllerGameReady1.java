/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static Controllers.FXMLControllerGameField1.*;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLControllerGameReady1 {

    @FXML private Button btBack;
    @FXML private Button btGo;
    private FXMLLoader loader;
    private Parent root;
    private Pane pane;
    static Scene gameFieldScene;
    static Scene pauseScene;
    static Stage stage;

    @FXML
    void onClickBack(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(new File("./src/main/Resources/fxml/characterSelection1.fxml").toURI().toURL());
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("HeadSoccer");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onClickGo(ActionEvent event) throws Exception{
        loader = new FXMLLoader(new File("./src/main/Resources/fxml/gameField1.fxml").toURI().toURL());
        
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("HeadSoccer"); 
        pane = new Pane();
        
        pane.getChildren().addAll(root, canvas);
        gameFieldScene = new Scene(pane);
        gameFieldScene.setOnKeyPressed((KeyEvent e) -> {
            //Player 1
            if(e.getCode() == KeyCode.W){
                //Check if player is on the ground. If yes, player can jump
                if(FXMLControllerGameField1.p1.getPosY() == CANVAS_HEIGHT-104){
                    FXMLControllerGameField1.p1.setVelY(-25);
                }
            }
            if(e.getCode() == KeyCode.A){
                FXMLControllerGameField1.p1.setVelX(-5);
            }
            if(e.getCode() == KeyCode.D){
                FXMLControllerGameField1.p1.setVelX(5);
            }
            
            
        });
        gameFieldScene.setOnKeyReleased((KeyEvent e) -> {
            if(e.getCode() == KeyCode.W){
                if (FXMLControllerGameField1.p1.getVelY()>0){
                    FXMLControllerGameField1.p1.setVelY(0);
                }
            }
            if(e.getCode() == KeyCode.A){
                FXMLControllerGameField1.p1.setVelX(0);
            }
            if(e.getCode() == KeyCode.D){
                FXMLControllerGameField1.p1.setVelX(0);
            }
            
    });
        stage.setScene(gameFieldScene);
        stage.show();
    }

}
