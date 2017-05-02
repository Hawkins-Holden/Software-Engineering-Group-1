/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author admin
 */
@SuppressWarnings("restriction")
public class BeginFormController implements Initializable {

	// ScreensController myController;

	@FXML
	private BorderPane mainBody;
	@FXML
	private Label Textbox_Error;// originally Fname_error.
	@FXML
	private Label empty_field;
	@FXML
	private Label welcomeLabel;
	@FXML
	private Label address_label;
	@FXML
	private TextField State;
	@FXML
	private Button rightAddress;
	@FXML
	private Button wrongAddress;
	@FXML
	private Label title_label1;
	@FXML
	private Button nxt_btn;
	@FXML
	private Button home_btn;

	@FXML
	private TextField CIty; // This must be a TextField, not a Label, or it WILL
							// NOT WORK
	@FXML
	private TextField Country; // This must be a TextField, not a Label, or it
								// WILL NOT WORK
	@FXML
	private TextField ZipC; // This must be a TextField, not a Label, or it WILL
							// NOT WORK

	private String firstName;
	private String lastName;
	ObservableList<String> state_list = FXCollections.observableArrayList("AL", "AK", "AZ", "AR", "CA", "CO", "CT",
			"DC", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS",
			"MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN",
			"TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");
	private Visitor visitor;
	private boolean error;

	/**
	 * Initializes the controller class.
	 */

	/*
	 * public void setScreenParent(ScreensController screenParent) {
	 * //myController = screenParent; }
	 */

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		PathTransition transition = new PathTransition();

		Line line = new Line();
		line.setStartX(10);
		line.setEndX(1000);
		line.setStartY(60);
		line.setEndY(60);
		transition.setDuration(Duration.seconds(15));
		transition.setNode(welcomeLabel);
		transition.setPath(line);
		transition.setAutoReverse(true);
		transition.setCycleCount(TranslateTransition.INDEFINITE);
		transition.play();

		visitor = VisitorContext.getInstance().currentVisitor();
		visitor.generateNewID();
		visitor.clearData();
		// State.setItems(state_list);
		// ----------------------------------------------
		String[] locationInfo = new String[25];
		int i = 0;

		try {

			// Get visitor location to perform analytics on it
			System.out.println("file read attempted");
			Scanner scan = new Scanner(new File("LocationOfVisitor.txt"));
			while (scan.hasNext() && i < 15) {
				locationInfo[i] = (scan.nextLine());
				i++;
			}

			System.out.println("This is begin controller " + locationInfo[0] + ",  " + locationInfo[1]);
			i = 0;

			visitor.setLatitude(locationInfo[7]);
			visitor.setLongitude(locationInfo[8]);
			System.out.println("Lat: " + locationInfo[7]);
			System.out.println("Lon: " + locationInfo[8]);
			scan.close();
			// Check if location is inside the US
			if (locationInfo[3].equals("USA")) {

				// If so, perform analytics
				String city = locationInfo[1];
				String zip = locationInfo[5];
				System.out.print(zip);
				String state = locationInfo[2];
				String metro = locationInfo[6];

				System.out.println("this is city: " + city);

				visitor.setCity(city);
				if (zip != null && !zip.isEmpty()) {
					Scanner scanner = new Scanner(zip);
					int newZip = scanner.nextInt();
					System.out.println(zip);
					visitor.setZip(newZip);
				}
				visitor.setState(state);
				visitor.setMetro(metro);
				visitor.setCountry("USA");

				System.out.println("Begin Form Lat: " + locationInfo[7]);
				System.out.println("Begin Form Lat: " + locationInfo[8]);

				CIty.setText(city);
				Country.setText("USA");
				ZipC.setText(zip);
				State.setText(state);

				int count = JDBC.getNumberofVisitors(city, metro);
				String numberVisitor = "" + count;
				String suffix;
				if (numberVisitor.charAt(numberVisitor.length() - 1) == '1') {
					suffix = "st";
				} else if (numberVisitor.charAt(numberVisitor.length() - 1) == '2') {
					suffix = "nd";
				} else {
					suffix = "th";
				}
				numberVisitor += suffix;
				String area = (metro == null || metro.isEmpty()) ? city : metro;

				if (!area.isEmpty()) {
					welcomeLabel.textProperty().setValue("Welcome to the Monroe - West Monroe area! Y'all are the "
							+ numberVisitor + " group to visit from the " + area + " area!");
				}
				// State.setAccessibleText(state);

				// String message = checkDataBase(city, state);//
				// messageLabel.setText(message);

			}

		} catch (Exception e) {
			// cry
			System.out.println("file n");
		}
		// -------------------------------------------------

		if (State.getText().isEmpty() || ZipC.getText().isEmpty()) {
			address_label.setText("Where are y'all visiting from?");
			State.setVisible(false);
			ZipC.setVisible(false);
			rightAddress.setVisible(false);
			wrongAddress.setVisible(false);
			nxt_btn.setVisible(true);
			home_btn.setVisible(true);
		}

	}

	public void rightAddress(ActionEvent e) {
		title_label1.setVisible(true);
		nxt_btn.setVisible(true);
		home_btn.setVisible(true);
		wrongAddress.setVisible(false);
		rightAddress.setVisible(false);
		address_label.setText("Ok Great! Y'all are awesome.");
		address_label.setTextFill(Color.web("#0076a3"));
	}

	public void wrongAddress(ActionEvent e) {
		address_label.setText("Feel free to modify your address.");
		address_label.setTextFill(Color.web("#0076a3"));
	}

	@FXML
	public void goToScreen2(ActionEvent event) throws IOException {

		// fnameValidate(Fname, Fname_error);
		// lnameValidate(Lname, Lname_error);

		// visitor.setFname(Fname.getText());
		// visitor.setLname(Lname.getText());
		// System.out.println("Form1 visitor: " + visitor.getFname());

		// myController.setScreen(SoftwareEngineering.screen2ID);

		error = false;
		textBoxValidate(CIty, Country, State, ZipC, Textbox_Error);

		if (!CIty.getText().isEmpty()) {
			visitor.setCity(CIty.getText());
		}
		if (!Country.getText().isEmpty()) {
			visitor.setCountry(Country.getText());
		}
		if (!ZipC.getText().isEmpty()) {
			visitor.setZip(Integer.parseInt(ZipC.getText()));
		}
		if (!State.getText().isEmpty()) {
			visitor.setState(State.getText());
		}

		if (error == false) {
			Parent newScene = FXMLLoader.load(getClass().getResource("MiddleForm.fxml"));
			Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			new_Stage.setTitle("Your Information");
			new_Stage.setScene(new Scene(newScene, 1680, 1200));
			new_Stage.show();
		}
		error = false;// resets error to false
	}

	public void setFields() {

	}

	public void noButton(ActionEvent event) throws IOException {

		System.out.println("No button clicked");
		Alert backAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm", ButtonType.YES, ButtonType.NO);
		backAlert.setContentText("Are you sure you want to go home?\n\nWe would love to know about y'all.");
		backAlert.showAndWait();
		if (backAlert.getResult() == ButtonType.YES) {
			if (!CIty.getText().isEmpty()) {
				visitor.setCity(CIty.getText());
			}
			if (!Country.getText().isEmpty()) {
				visitor.setCountry(Country.getText());
			}
			if (!ZipC.getText().isEmpty()) {
				visitor.setZip(Integer.parseInt(ZipC.getText()));
			}
			if (!State.getText().isEmpty()) {
				visitor.setState(State.getText());
			}
			JDBC.addVisitor(visitor);
			Parent newScene = FXMLLoader.load(getClass().getResource("Map.fxml"));
			Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			new_Stage.setTitle("Welcome to Monroe-West Monroe CVB");
			new_Stage.setScene(new Scene(newScene, 1920, 1080));
			new_Stage.show();
			
			Form.timer.restartIdleTimer();
			
			newScene.setOnMouseClicked(mouseHandler);
		    newScene.setOnMouseDragged(mouseHandler);
		    newScene.setOnMouseEntered(mouseHandler);
		    newScene.setOnMouseExited(mouseHandler);
		    newScene.setOnMouseMoved(mouseHandler);
		    newScene.setOnMousePressed(mouseHandler);
		    newScene.setOnMouseReleased(mouseHandler);	
			
		} else {
			backAlert.close();
		}
	}
	
	/***************************************************************************
	 *********************** Mouse Handler *************************************
	 **************************************************************************/
	
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() 
	{
		 
        @Override
        public void handle(MouseEvent mouseEvent)
        {
        	Form.timer.restartIdleTimer();
        }
    };

	/****************************************************************
	 * Checks to see if any of the text box's have more than 32 characters.
	 ****************************************************************/

	public void textBoxValidate(TextField CIty, TextField Country, TextField State, TextField zip, Label City_Error) {
		if ((CIty.getText().length() > 32) || (Country.getText().length() > 32) || (State.getText().length() > 32)
				|| zip.getText().length() > 32) {
			City_Error.setText("Please use less than 32 characters for each field");
			error = true;
		}
	}
}// end class