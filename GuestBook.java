package src.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*; 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import javax.print.DocFlavor.URL;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;



public class GuestBook extends Application {
	
	public static void setLatLong(String nums){
		System.out.println(GeoCode.setLatLong(nums));
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Form.fxml"));
		Parent root = loader.load();
		
		Controller controls = loader.getController(); 
	
		primaryStage.setTitle("Guest Book");
		primaryStage.setScene(new Scene(root,1820,980));
		primaryStage.show();
		
		
		controls.setData(); 
		
		Scene scene = new Scene(new Group(), 1366, 900);
		
		

		// make input field
		TextField latlong = new TextField();

		// make browser
		Browser ViewPort = new Browser();

		// make button
		Button btn = new Button();
		btn.setText("Get Latlong");

		// Fill in the grid with the declared elements and labels for them.
		GridPane grid = new GridPane();
		grid.setVgap(4);
		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(new Label("Your Lat Long is:"), 0, 0);
		grid.add(latlong, 1, 0);
		grid.add(btn, 15, 0);
		grid.add(ViewPort, 1, 15);

		Group root2 = (Group) scene.getRoot();
		root2.getChildren().add(grid);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args)
	{
		launch(args); 
	}
	
}

//////////////////////////////////////////////////
//Browser Object
//////////////////////////////////////////////////
class Browser extends Region {

private HBox toolBar;

final ImageView selectedImage = new ImageView();

final WebView browser = new WebView();
final WebEngine webEngine = browser.getEngine();
private boolean needDocumentationButton = true;

public Browser() {
// apply the styles
getStyleClass().add("browser");

// create the toolbar
toolBar = new HBox();
toolBar.setAlignment(Pos.CENTER);
toolBar.getStyleClass().add("browser-toolbar");
// toolBar.getChildren().addAll(hpls);
toolBar.getChildren().add(createSpacer());

// process page loading
/*
* webEngine.getLoadWorker().stateProperty().addListener(new
* ChangeListener<State>() {
* 
* @Override public void changed(ObservableValue<? extends State> ov,
* State oldState, State newState) {
* toolBar.getChildren().remove(showPrevDoc); if (newState ==
* State.SUCCEEDED) { if (needDocumentationButton) {
* toolBar.getChildren().add(showPrevDoc); } } } });
*/

// --------------------------------------------------------------------------
webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {

@Override
public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
if (newState == State.SUCCEEDED) {
JSObject window = (JSObject) webEngine.executeScript("window");
window.setMember("app", new JavaApplication());
}
}
});

JSObject window = (JSObject) webEngine.executeScript("window");
window.setMember("app", new JavaApplication());

// --------------------------------------------------------------------------

// load the home page

java.net.URL num = getClass().getResource("hello.html");
browser.getEngine().load(num.toString());

// webEngine.load("https://www.google.com/maps/@32.3652278,-91.8513473,14z");

// add components
getChildren().add(toolBar);
getChildren().add(browser);

}

private Node createSpacer() {
Region spacer = new Region();
HBox.setHgrow(spacer, Priority.ALWAYS);
return spacer;
}

@Override
protected void layoutChildren() {
double w = getWidth();
double h = getHeight();
double tbHeight = toolBar.prefHeight(w);
layoutInArea(browser, 0, 0, w, h - tbHeight, 0, HPos.CENTER, VPos.CENTER);
layoutInArea(toolBar, 0, h - tbHeight, w, tbHeight, 0, HPos.CENTER, VPos.CENTER);
}

@Override
protected double computePrefWidth(double height) {
return 750;
}

@Override
protected double computePrefHeight(double width) {
return 600;
}

public class JavaApplication {
public void callFromJavascript(String coords) {
GuestBook.setLatLong(coords);
}
}
}