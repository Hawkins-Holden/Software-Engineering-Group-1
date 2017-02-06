import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
	Scene sceneA, scene; 

	
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
		
		Label welcomeLabel = new Label("Welcome to Monroe-West Monroe Convention Center");
		welcomeLabel.getStyleClass().add("welcomelabel"); //CSS for label
		
		Button welcomeButton = new Button();
		welcomeButton.setText("START");
		welcomeButton.setCenterShape(true);
		
		welcomeButton.setAlignment(Pos.CENTER);
		welcomeButton.getStyleClass().add("welcomeButton"); //Custom CSS to the welcome button 
		
		welcomeButton.setOnAction(e -> window.setScene(scene));
		
		//Layout for first scene
		VBox layout1 = new VBox(90);
		layout1.getChildren().addAll(welcomeLabel, welcomeButton);
		sceneA = new Scene(layout1, 700, 700); 
		sceneA.getStylesheets().add("style.css");
		
		
		//Name label
		Label nameLabel = new Label("Full Name:");
		GridPane.setConstraints(nameLabel, 0, 0);
		nameLabel.getStyleClass().add("welcomelabel"); //CSS for label
		
		//Name input
		TextField nameInput = new TextField();
		nameInput.setPromptText("your name here");
		GridPane.setConstraints(nameInput, 1, 0);
		
		//Email label
		Label emailLabel = new Label("Email:");
		GridPane.setConstraints(emailLabel, 0, 1);
		emailLabel.getStyleClass().add("welcomelabel"); //CSS for label
				
		//Email Input
		TextField emailInput = new TextField();
		emailInput.setPromptText("....@....com");
		GridPane.setConstraints(emailInput, 1, 1);
		
		//Traveler Label
		 Label travelLabel = new Label("How many people are traveling with you?");
		 travelLabel.getStyleClass().add("welcomelabel"); //CSS for label
		 GridPane.setConstraints(travelLabel, 0, 2);
		 
		//Traveler input
		TextField travelInput = new TextField();
		travelInput.setPromptText("number here");
		GridPane.setConstraints(travelInput, 1, 2);
		
		//Name label
		Label listLabel = new Label("What is the purpose of your visit?");
		GridPane.setConstraints(listLabel, 0, 3);
		listLabel.getStyleClass().add("welcomelabel"); //CSS for label
		
		//observable list
		ChoiceBox<String> choiceBox = new ChoiceBox<>();
		GridPane.setConstraints(choiceBox, 1, 3);
		
		//getItems returns the ObservableList object which you can add items to
		
		choiceBox.getItems().add("Travel");
		choiceBox.getItems().add("Business");
		choiceBox.getItems().add("Education");
		choiceBox.getItems().add("Other");
		//or use addALL to add multiple items at once! 
				
		// Set default value
		choiceBox.setValue("Travel");	//set value that already exist
		
		
		//Radio Label
		Label radioLabel = new Label("Are you staying overnight?");
		GridPane.setConstraints(radioLabel, 0, 4);
		radioLabel.getStyleClass().add("welcomelabel"); //CSS for label
		
		//radio button 
		final ToggleGroup group = new ToggleGroup();

		RadioButton rb1 = new RadioButton("Yes");
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		GridPane.setConstraints(rb1, 1, 4);
		rb1.getStyleClass().add("welcomelabel"); //CSS for label
		
		RadioButton rb2 = new RadioButton("No");
		rb2.setToggleGroup(group);
		GridPane.setConstraints(rb2, 1, 5);
		rb2.getStyleClass().add("welcomelabel"); //CSS for label

		CheckBox box1 = new CheckBox("Opt me in for montly newspaper.");
		GridPane.setConstraints(box1, 0, 6);
		box1.getStyleClass().add("welcomelabel"); //CSS for label
		//check by default
		box1.setSelected(true);
		
		
		Button confirmButton = new Button("Submit");
		GridPane.setConstraints(confirmButton, 1, 7);
		confirmButton.getStyleClass().add("confirmButton"); //Custom CSS to the confirm button
		
		//when you hit confirmButton following things are happening! 
		confirmButton.setOnAction(e -> {
			
			isInt(travelInput,travelInput.getText());
			getChoice(choiceBox);
			handleOptions(box1);
			radioOptions(rb1);
			
		});
		
		Button backButton = new Button("Go Back!"); 
	    GridPane.setConstraints(backButton,  1,  8); 
	    backButton.getStyleClass().add("backButton"); //Custom CSS to the back button
	    
	    
	    backButton.setOnAction(e -> window.setScene(sceneA));
		
		//add everything on the grid which is our layout. 
		grid.getChildren().addAll(nameLabel, nameInput, emailLabel, emailInput,travelLabel, travelInput,listLabel, choiceBox, radioLabel, rb1, rb2, box1, confirmButton, backButton);
		
		scene = new Scene(grid, 700, 700);
		scene.getStylesheets().add("style.css"); //add external CSS
		window.setScene(sceneA);
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
		
		
		//
private void radioOptions(RadioButton rb1){
			
			String message = "You "; 
			
			if (rb1.isSelected()){
				message += "are staying overnight.";
			}
			
			else {
				message += "are not staying overnight.";
			}
		
			System.out.println(message);
		}
		
		
		//To get the value of selected item
		
		private void getChoice(ChoiceBox<String> choiceBox){
			
			String choice = choiceBox.getValue();
			
			System.out.println(choice+"\n");
		}

}