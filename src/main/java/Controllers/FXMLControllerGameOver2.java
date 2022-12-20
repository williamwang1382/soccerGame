/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.FXMLControllerGameField2.*;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class FXMLControllerGameOver2 implements Initializable{

    @FXML private Button exitbt;    
    @FXML private GridPane imagePane1;
    @FXML private GridPane imagePane2;
    @FXML private Text player1Score;
    @FXML private Text player2Score;
    @FXML private Text p1Name;
    @FXML private Text p2Name;

    @FXML void onClickMenu(ActionEvent event) throws Exception{
       FXMLLoader loader = new FXMLLoader(new File("./src/main/Resources/fxml/gameMenu.fxml").toURI().toURL());
       Parent root = loader.load();
       Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
       Scene scene = new Scene(root);
       stage.setTitle("HeadSoccer");
       stage.setScene(scene);
       stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        player1Score.setText(String.valueOf(p1.getScore()));
        p1Name.setText(p1.getName());
        player2Score.setText(String.valueOf(p2.getScore()));
        p2Name.setText(p2.getName());
    }
    
    
}
