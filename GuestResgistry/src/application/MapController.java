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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage; 

public class MapController implements Initializable {
	
	@FXML
	private WebView webView; 
	private WebEngine engine; 
	
	
	public void displayWeb() {
	    WebEngine engine = webView.getEngine();
	    final String hellohtml = "Map.html"; //HTML file to view in web view

	    URL urlHello = getClass().getResource(hellohtml);
	    engine.load(urlHello.toExternalForm());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		engine = webView.getEngine(); 
	    displayWeb();
		
	}
	
	public void NextButton(ActionEvent event) throws IOException {

			Parent newScene = FXMLLoader.load(getClass().getResource("Name.fxml"));
			Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			new_Stage.setTitle("Your Information");
			new_Stage.setScene(new Scene(newScene, 1680, 1200));
			new_Stage.show();

	}
	

}
