package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
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
import netscape.javascript.JSObject;

public class MapController implements Initializable {

	@FXML
	private WebView webView;
	private WebEngine engine;
	JSObject window;

	public void displayWeb() {
		engine = webView.getEngine();
		final String hellohtml = "MapTest.html"; // HTML file to view in web view
		URL urlHello = getClass().getResource(hellohtml);
		engine.load(urlHello.toExternalForm());

		// ----------------------------------------------

		engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {

				if (newState == State.SUCCEEDED) {
					window = (JSObject) engine.executeScript("window");
					window.setMember("app", new JavascriptComm());
					System.out.println("stateChange");
					populateMap();
				}
			}

		});

		// ----------------------------------------------

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		engine = webView.getEngine();
		displayWeb();
	}

	public void NextButton(ActionEvent event) throws IOException {

		// Get necessary stuff from Javascript
		// ----------------------------------------------

		engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {

				if (newState == State.SUCCEEDED) {
					window = (JSObject) engine.executeScript("window");
					window.setMember("app", new JavascriptComm());
					System.out.println("stateChange");
				}
			}

		});

		window = (JSObject) engine.executeScript("window");
		window.setMember("app", new JavascriptComm());
		//engine.executeScript("giveInfo();");

		// ----------------------------------------------

		Parent newScene = FXMLLoader.load(getClass().getResource("Name.fxml"));
		Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		new_Stage.setTitle("Your Information");
		new_Stage.setScene(new Scene(newScene, 1680, 1200));
		new_Stage.show();

	}

	/**
	 * Initiates the process of populating the javascript run Google map
	 */
	private void populateMap() {
		engine.executeScript("populateJSMap();");
	}
}
