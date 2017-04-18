/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 *
 */

public class LogInAdminController implements Initializable {
    
@FXML
private TextField user_txtbx;
@FXML
private TextField pass_txtbx;
@FXML
private Button submit_btn;
@FXML
private Label error_field; 




public void onSubmit(ActionEvent event) throws IOException {
    
    String username = user_txtbx.getText();
    String password = pass_txtbx.getText(); 
    
    if (username.equalsIgnoreCase("groupone") && password.equals("warhawks1")){
        
        Parent newScene = FXMLLoader.load(getClass().getResource("Platform.fxml"));
	Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	new_Stage.setTitle("Admin Platform");
	new_Stage.setScene(new Scene(newScene,1000,1000));
	new_Stage.show();
    }
    
    else if (username.equals("")&&password.equals("")){
        error_field.setText("Please enter the username and password to log-in.");
    }
    
    else {
        error_field.setText("Your username or password is incorrect!");
    }
}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
