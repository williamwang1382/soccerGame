/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class FXMLControllerGameMenu extends Main{

    @FXML private Button player1bt;

    @FXML private Button player2bt;

    @FXML private Button info;

    @FXML private Button exitbt;

    @FXML private Text gameNameText;

    @FXML void onClickExit(ActionEvent event) {
        System.exit(0);

    }

    @FXML void onClickPlayer1(ActionEvent event) throws Exception{
       FXMLLoader loader = new FXMLLoader(new File("./src/main/Resources/fxml/characterSelection1.fxml").toURI().toURL());
       Parent root = loader.load();
       Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
       Scene scene = new Scene(root);
       stage.setTitle("HeadSoccer");
       stage.setScene(scene);
       stage.show();
    }

    @FXML void onClickPlayer2(ActionEvent event) throws Exception{
       FXMLLoader loader = new FXMLLoader(new File("./src/main/Resources/fxml/characterSelection2.fxml").toURI().toURL());
       Parent root = loader.load();
       Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
       Scene scene = new Scene(root);
       stage.setTitle("HeadSoccer");
       stage.setScene(scene);
       stage.show();
    }

    @FXML void onClickInfo(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(new File("./src/main/Resources/fxml/info.fxml").toURI().toURL());
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("HeadSoccer");
        Pane pane = new Pane();
        pane.getChildren().addAll(root);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
    
    
}

