package adminApplication;

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

@SuppressWarnings("restriction")
public class PlatformController implements Initializable {
  
    
    
    public void Visitor(ActionEvent event) throws IOException {
        
        Parent newScene = FXMLLoader.load(getClass().getResource("VisitorView.fxml"));
	Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	new_Stage.setTitle("Visitor Table");
	new_Stage.setScene(new Scene(newScene,1920,1080));
	new_Stage.show();
    }
    
public void Analytics(ActionEvent event) throws IOException {
        
        Parent newScene = FXMLLoader.load(getClass().getResource("analytics.fxml"));
	Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	new_Stage.setTitle("Visitor Table");
	new_Stage.setScene(new Scene(newScene,1920,1080));
	new_Stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
