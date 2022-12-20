        package Controllers;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author rodri
 */
public class FXMLControllerInfo {
    
    @FXML private Button back;
    
    @FXML void onClickBack(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(new File("./src/main/Resources/fxml/gameMenu.fxml").toURI().toURL());
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("HeadSoccer");
        stage.setScene(scene);
        stage.show();
    }
}
