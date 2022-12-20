/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import static Controllers.FXMLControllerGameField1.p1;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
   
public class FXMLControllerGameOver1 implements Initializable{

    @FXML private Button menuBt;
    @FXML private Text playerName;
    @FXML private Text score;

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
        score.setText("Final Score: " + String.valueOf(p1.getScore()));
        playerName.setText(p1.getName());
    }

}