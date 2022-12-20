/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.FXMLControllerGameField2.*;
import GameObjects.Player;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


public class FXMLControllerPlayer2 implements Initializable{

    @FXML private Button exitbt1;
    @FXML private Button exitbt;
    @FXML private RadioButton rbp2c1;
    @FXML private RadioButton rbp2c2;
    @FXML private RadioButton rbp2c3;
    @FXML private RadioButton rbp1c1;
    @FXML private RadioButton rbp1c2;
    @FXML private RadioButton rbp1c3;
    @FXML private GridPane imagePane1;
    @FXML private GridPane imagePane2;
    @FXML private TextField p1Name;
    @FXML private TextField p2Name;
    private ToggleGroup group1;
    private ToggleGroup group2;
    static Scene gameFieldScene;

    @FXML void onClickBack(ActionEvent event) throws Exception{
       FXMLLoader loader = new FXMLLoader(new File("./src/main/Resources/fxml/gameMenu.fxml").toURI().toURL());
       Parent root = loader.load();
       Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
       Scene scene = new Scene(root);
       stage.setTitle("Game");
       stage.setScene(scene);
       stage.show();
    }

    @FXML void onClickConfirm(ActionEvent event) throws Exception{
        if((rbp1c1.isSelected()||rbp1c2.isSelected()||rbp1c3.isSelected())&&(rbp2c1.isSelected()||rbp2c2.isSelected()||rbp2c3.isSelected())){
            p1.setName(p1Name.getText());
            p2.setName(p2Name.getText());
            FXMLLoader loader = new FXMLLoader(new File("./src/main/Resources/fxml/gameReady2.fxml").toURI().toURL());
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
        group1 = new ToggleGroup(); 
        group2 = new ToggleGroup();
        
        rbp1c1.setToggleGroup(group1);
        rbp1c2.setToggleGroup(group1);
        rbp1c3.setToggleGroup(group1);
        
        rbp2c1.setToggleGroup(group2);
        rbp2c2.setToggleGroup(group2);
        rbp2c3.setToggleGroup(group2);
        
        rbp1c1.setOnAction(e -> {
            if (rbp1c1.isSelected()){
                Image image = new Image(new File("./src/main/Resources/images/player1_1.png").toURI().toString());
                imagePane1.getChildren().clear();
                imagePane1.getChildren().add(new ImageView(image));
                p1.setPlayerID(Player.playerID.PLAYER1);
            }
        });
        rbp1c2.setOnAction(e -> {
            if (rbp1c2.isSelected()){
                Image image = new Image(new File("./src/main/Resources/images/player2_1.png").toURI().toString());
                imagePane1.getChildren().clear();
                imagePane1.getChildren().add(new ImageView(image));
                p1.setPlayerID(Player.playerID.PLAYER2);
            }
        });
        rbp1c3.setOnAction(e -> {
            if (rbp1c3.isSelected()){
                Image image = new Image(new File("./src/main/Resources/images/player3_1.png").toURI().toString());
                imagePane1.getChildren().clear();
                imagePane1.getChildren().add(new ImageView(image));
                p1.setPlayerID(Player.playerID.PLAYER3);
            }
        });
        rbp2c1.setOnAction(e -> {
            if (rbp2c1.isSelected()){
                Image image = new Image(new File("./src/main/Resources/images/player1_1.png").toURI().toString());
                imagePane2.getChildren().clear();
                imagePane2.getChildren().add(new ImageView(image));
                p2.setPlayerID(Player.playerID.PLAYER1);
            }
        });
        rbp2c2.setOnAction(e -> {
            if (rbp2c2.isSelected()){
                Image image = new Image(new File("./src/main/Resources/images/player2_1.png").toURI().toString());
                imagePane2.getChildren().clear();
                imagePane2.getChildren().add(new ImageView(image));
                p2.setPlayerID(Player.playerID.PLAYER2);
            }
        });
        rbp2c3.setOnAction(e -> {
            if (rbp2c3.isSelected()){
                Image image = new Image(new File("./src/main/Resources/images/player3_1.png").toURI().toString());
                imagePane2.getChildren().clear();
                imagePane2.getChildren().add(new ImageView(image));
                p2.setPlayerID(Player.playerID.PLAYER3);
            }
        });
        
        
    }

}
