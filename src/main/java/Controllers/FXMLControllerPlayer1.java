/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.FXMLControllerGameField1.p1;
import GameObjects.Player;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.Initializable;


public class FXMLControllerPlayer1 implements Initializable{

    @FXML private Text gameNameText;
    @FXML private Button btConfirm;
    @FXML private Button btBack;
    @FXML private RadioButton rbcharacter1;
    @FXML private RadioButton rbcharacter2;
    @FXML private RadioButton rbcharacter3;
    @FXML private Pane imagePane;
    @FXML private Text p1Name;
    
    private ToggleGroup group;

    @FXML void onClickBack(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(new File("./src/main/Resources/fxml/gameMenu.fxml").toURI().toURL());
        Parent root = loader.load();
        Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("HeadSoccer");
        stage.setScene(scene);
        stage.show();
    }

    @FXML synchronized void onClickConfirm(ActionEvent event) throws Exception{
        if(rbcharacter1.isSelected()||rbcharacter2.isSelected()||rbcharacter3.isSelected()){
            //p1.setName(p1Name.getText());
            FXMLLoader loader = new FXMLLoader(new File("./src/main/Resources/fxml/gameReady1.fxml").toURI().toURL());
            Parent root = loader.load();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("HeadSoccer");
            stage.setScene(scene);
            stage.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        group=new ToggleGroup(); 
        rbcharacter1.setToggleGroup(group);
        rbcharacter2.setToggleGroup(group);
        rbcharacter3.setToggleGroup(group);
        
        rbcharacter1.setOnAction(e -> {
            if (rbcharacter1.isSelected()){
                Image image = new Image(new File("./src/main/Resources/images/player1_1.png").toURI().toString());
                imagePane.getChildren().clear();
                imagePane.getChildren().add(new ImageView(image));
                p1.setPlayerID(Player.playerID.PLAYER1);
            }
        });
        rbcharacter2.setOnAction(e -> {
            if (rbcharacter2.isSelected()){
                Image image = new Image(new File("./src/main/Resources/images/player2_1.png").toURI().toString());
                imagePane.getChildren().clear();
                imagePane.getChildren().add(new ImageView(image));
                p1.setPlayerID(Player.playerID.PLAYER2);
            }
        });
        rbcharacter3.setOnAction(e -> {
            if (rbcharacter3.isSelected()){
                Image image = new Image(new File("./src/main/Resources/images/player3_1.png").toURI().toString());
                imagePane.getChildren().clear();
                imagePane.getChildren().add(new ImageView(image));
                p1.setPlayerID(Player.playerID.PLAYER3);
            }
        });
    }

}


