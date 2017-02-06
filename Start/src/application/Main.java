package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.print.DocFlavor.URL;

import backEnd.Basic;
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


// w w  w .jav a  2 s . c o  m
public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group(), 1366, 900);

		// make input field
		TextField notification = new TextField();

		// make output field
		TextField output = new TextField();

		// make browser
		Browser ViewPort = new Browser();

		// make button
		Button btn = new Button();
		btn.setText("Submit");

		// object declaration
		Basic Temp = new Basic();

		// Wipe boxes
		notification.clear();
		output.clear();

		///////////////////
		// create scene
		// stage.setTitle("Web View");
		// scene = new Scene(new Browser(), 750, 500, Color.web("#666970"));
		// stage.setScene(scene);
		// apply CSS style
		// scene.getStylesheets().add("webviewsample/BrowserToolbar.css");
		// show stage
		// stage.show();
		///////////////////

		//Fill in the grid with the declared elements and labels for them.
		GridPane grid = new GridPane();
		grid.setVgap(4);
		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(new Label("ZIP Code"), 0, 0);
		grid.add(notification, 1, 0);
		grid.add(btn, 15, 0);
		grid.add(output, 1, 10);
		grid.add(new Label("Your ZIP is:"), 0, 10);
		grid.add(ViewPort, 1, 15);

		Group root = (Group) scene.getRoot();
		root.getChildren().add(grid);
		stage.setScene(scene);
		stage.show();

		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// System.out.println("Doin it");
				Temp.setZip(notification.getText());
				// System.out.println("Dun it");
				System.out.println(Temp.getZip());
				output.setText(Temp.getZip());
			}
		});
	}
}

//////////////////////////////////////////////////
// Browser Object
//////////////////////////////////////////////////
class Browser extends Region {
	 
    private HBox toolBar;
    private static String[] imageFiles = new String[]{
        "product.png",
        "blog.png",
        "documentation.png",
        "partners.png",
    };
    private static String[] captions = new String[]{
        "Products",
        "Blogs",
        "Documentation",
        "Partners",
    };
    private static String[] urls = new String[]{
        "http://www.oracle.com/products/index.html",
        "http://blogs.oracle.com/",
        "http://docs.oracle.com/javase/index.html",
        "http://www.oracle.com/partners/index.html",
    };
    final ImageView selectedImage = new ImageView();
    final Hyperlink[] hpls = new Hyperlink[captions.length];
    final Image[] images = new Image[imageFiles.length];
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    final Button showPrevDoc = new Button("Toggle Previous Docs");
    private boolean needDocumentationButton = true;
 
    public Browser() {
        //apply the styles
        getStyleClass().add("browser");
 
        for (int i = 0; i < captions.length; i++) {
            // create hyperlinks
            Hyperlink hpl = hpls[i] = new Hyperlink(captions[i]);
         /*   Image image = images[i] =
                    new Image(getClass().getResourceAsStream(imageFiles[i]));
            hpl.setGraphic(new ImageView(image));*/
            final String url = urls[i];
            final boolean addButton = (hpl.getText().equals("Documentation"));
 
            // process event 
            hpl.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    needDocumentationButton = addButton;
                    webEngine.load(url);
                }
            });
        }
 
     
        // create the toolbar
        toolBar = new HBox();
        toolBar.setAlignment(Pos.CENTER);
        toolBar.getStyleClass().add("browser-toolbar");
        toolBar.getChildren().addAll(hpls);
        toolBar.getChildren().add(createSpacer());
 
        //set action for the button
        showPrevDoc.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                webEngine.executeScript("toggleDisplay('PrevRel')");
            }           
        });
       
        // process page loading
        webEngine.getLoadWorker().stateProperty().addListener(
            new ChangeListener<State>() {
                @Override
                public void changed(ObservableValue<? extends State> ov,
                    State oldState, State newState) {
                        toolBar.getChildren().remove(showPrevDoc);
                        if (newState == State.SUCCEEDED) {
                            if (needDocumentationButton) {
                                toolBar.getChildren().add(showPrevDoc);
                            }
                        }
                    }
                }
        );
 
        // load the home page
        
        java.net.URL num = getClass().getResource("example.html");
        browser.getEngine().load(num.toString());
        
        //webEngine.load("https://www.google.com/maps/@32.3652278,-91.8513473,14z");
 
        //add components
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
        layoutInArea(browser,0,0,w,h-tbHeight,0,HPos.CENTER, VPos.CENTER);
        layoutInArea(toolBar,0,h-tbHeight,w,tbHeight,0,HPos.CENTER,VPos.CENTER);
    }
 
    @Override
    protected double computePrefWidth(double height) {
        return 750;
    }
 
    @Override
    protected double computePrefHeight(double width) {
        return 600;
    }
}