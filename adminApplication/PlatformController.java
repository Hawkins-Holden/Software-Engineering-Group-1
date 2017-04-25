/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class PlatformController implements Initializable {
  
    
    
    public void Visitor(ActionEvent event) throws IOException {
        
        Parent newScene = FXMLLoader.load(getClass().getResource("VisitorView.fxml"));
	Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	new_Stage.setTitle("Visitor Table");
	new_Stage.setScene(new Scene(newScene,1000,1000));
	new_Stage.show();
    }
    
public void Analytics(ActionEvent event) throws IOException {
        
        Parent newScene = FXMLLoader.load(getClass().getResource("analytics.fxml"));
	Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	new_Stage.setTitle("Visitor Table");
	new_Stage.setScene(new Scene(newScene,1000,1000));
	new_Stage.show();
    }
    
      public void LogOut(ActionEvent event) throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("LogInAdmin.fxml"));
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
