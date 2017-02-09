package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// w w  w .jav a  2 s . c o  m
public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }
  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(new Group(), 450, 250);

    //make input field
    TextField notification = new TextField ();
    //notification.setText("Input");
    
    //make output field
    TextField output = new TextField ();
    //output.setText("Your ZIP is:");
    
    //make button
    Button btn = new Button();
    btn.setText("Submit");
    
    //object declaration
    
    notification.clear();
    
    GridPane grid = new GridPane();
    grid.setVgap(4);
    grid.setHgap(10);
    grid.setPadding(new Insets(5, 5, 5, 5));
    grid.add(new Label("ZIP Code"), 0, 0);
    grid.add(notification, 1, 0);
    grid.add(btn, 15, 0);
    grid.add(output, 1, 10);
    grid.add(new Label("Your ZIP is:"), 0, 10);
    

    
    Group root = (Group) scene.getRoot();
    root.getChildren().add(grid);
    stage.setScene(scene);
    stage.show();
    
    btn.setOnAction(new EventHandler<ActionEvent>() {
    	 
        @Override
        public void handle(ActionEvent event) {
        	Integer zip = Integer.parseInt(notification.getText());
            System.out.println("Doin it");
            Visitor visitor = new Visitor(zip);
            JDBC.insertIntoDB(visitor.getZip());
            System.out.println("Dun it");
            zip = JDBC.readFromDB(visitor.getZip());
            System.out.println(zip);
            output.setText(zip.toString());
        }
    });
  }
}
