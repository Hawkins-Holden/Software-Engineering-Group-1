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
@SuppressWarnings("restriction")
public class Main extends Application
{
	
	public Visitor getVisitor()
	{
        int VisitorID = (int) Math.ceil((Math.random()*10000));
        Visitor visitor = new Visitor(VisitorID, "Hawkins", "W", "Holden", "hholdengold@gmail.com", true, "Pleasure", "32", "-92");//just something random to use for testing
		return visitor;
	}
	
  public static void main(String[] args) 
  {
    launch(args);
  }
  @Override
  public void start(Stage stage) 
  {
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
    grid.add(new Label("ID:"), 0, 0);
    grid.add(notification, 1, 0);
    grid.add(btn, 15, 0);
    grid.add(output, 1, 10);
    grid.add(new Label("Your ID is:"), 0, 10);
    

    
    Group root = (Group) scene.getRoot();
    root.getChildren().add(grid);
    stage.setScene(scene);
    stage.show();
    
    btn.setOnAction(new EventHandler<ActionEvent>() 
    {
    	 
        @Override
        public void handle(ActionEvent event) 
        {
        	Integer id = Integer.parseInt(notification.getText());
            System.out.println("testing 1...");
           
            Visitor x = getVisitor();

            JDBC.insertIntoDB(x.getId());
            System.out.println("testing 2...");
            id = JDBC.readFromDB(x.getId());
            System.out.println(id);
            output.setText(id.toString());
        }
    });
  }
}
