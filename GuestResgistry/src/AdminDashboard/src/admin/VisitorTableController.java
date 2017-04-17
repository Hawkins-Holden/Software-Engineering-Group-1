/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class VisitorTableController implements Initializable {

    /**
     * Initializes the controller class.
     */
          public void goBack(ActionEvent event) throws IOException {
          
        Parent newScene = FXMLLoader.load(getClass().getResource("Admin.fxml"));
	Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	new_Stage.setTitle("Visitor Table");
	new_Stage.setScene(new Scene(newScene,1000,1000));
	new_Stage.show();
          }
          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
    

