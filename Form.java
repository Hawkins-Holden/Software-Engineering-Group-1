import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.*; 



public class Form extends Application {
	
	Stage window;
	Scene scene; 

	
	public static void main (String[] args) {
		launch(args); 
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		window = primaryStage; 
		window.setTitle("From");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8); //vertical spacing
		grid.setHgap(10); //horizontal spacing 
		
		//Name label
		Label nameLabel = new Label("Full Name:");
		GridPane.setConstraints(nameLabel, 0, 0);
		
		//Name input
		TextField nameInput = new TextField();
		nameInput.setPromptText("your name here");
		GridPane.setConstraints(nameInput, 1, 0);
		
		//Email label
		Label emailLabel = new Label("Email:");
		GridPane.setConstraints(emailLabel, 0, 1);
				
		//Email Input
		TextField emailInput = new TextField();
		emailInput.setPromptText("....@....com");
		GridPane.setConstraints(emailInput, 1, 1);
		
		//Traveler Label
		 Label travelLabel = new Label("How many people are traveling with you?");
		 GridPane.setConstraints(travelLabel, 0, 2);
		 
		//Traveler input
		TextField travelInput = new TextField();
		nameInput.setPromptText("number here");
		GridPane.setConstraints(travelInput, 1, 2);
		
		//Name label
		Label listLabel = new Label("What is the purpose of your visit?");
		GridPane.setConstraints(listLabel, 0, 3);
		
		//observable list
		ChoiceBox<String> choiceBox = new ChoiceBox<>();
		GridPane.setConstraints(choiceBox, 1, 3);
		
		//getItems returns the ObservableList object which you can add items to
		
		choiceBox.getItems().add("Travel");
		choiceBox.getItems().add("Business");
		choiceBox.getItems().add("Education");
		choiceBox.getItems().add("Other");
		//or use addALL to multiple items at once! 
				
		// Set default value
		choiceBox.setValue("Travel");	//set value that already exist
		

		CheckBox box1 = new CheckBox("Opt me in for montly newspaper.");
		GridPane.setConstraints(box1, 0, 4);
		
		Button confirmButton = new Button("Submit");
		GridPane.setConstraints(confirmButton, 1, 5);
		
		
		//when you hot confirmButton following things are happening! 
		confirmButton.setOnAction(e -> {
			
			isInt(travelInput,travelInput.getText());
			getChoice(choiceBox);
			handleOptions(box1);
			
		});
		

		//check by default
		box1.setSelected(true);
		
		
		grid.getChildren().addAll(nameLabel, nameInput, emailLabel, emailInput,travelLabel, travelInput,listLabel, choiceBox, box1, confirmButton);
		
		scene = new Scene(grid, 300, 200);
		window.setScene(scene);
		window.show(); 
	}
	
	//validate integer
		private boolean isInt(TextField input, String message){
			
			try {
				int num = Integer.parseInt(input.getText());
				System.out.println("User is traveling with: "+num+" people."); 
				return true; 
				
			}catch(NumberFormatException e){
				System.out.println("Error: "+message+" is not a number");
				return false; 
			}
			
		}
	
	//Handle CheckBox Option
		private void handleOptions(CheckBox box1){
			
			String message = "You "; 
			
			if (box1.isSelected()){
				message += "Opt-In for montly newspaper.";
			}
			
			else {
				message += "Opt-Out for montly newspaper.";
			}
		
			System.out.println(message);
		}
		
		//To get the value of selected item
		
		private void getChoice(ChoiceBox<String> choiceBox){
			
			String choice = choiceBox.getValue();
			
			System.out.println(choice+"\n");
		}

}
