/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author wwang
 */
public class Main extends Application{
    
    @Override
    public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(new File("./src/main/Resources/fxml/gameMenu.fxml").toURI().toURL());
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stage.setTitle("HeadSoccer");
    stage.setResizable(false);
    stage.setOnCloseRequest(e -> {
        System.exit(0);
    });
    stage.setScene(scene);
    stage.show();
  }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        // TODO code application logic here
    }
    
}
