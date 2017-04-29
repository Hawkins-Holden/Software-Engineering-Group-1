/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
@SuppressWarnings("restriction")
public class EndFormController implements Initializable, ControlledScreen {

	ScreensController myController;

	@FXML
	private ComboBox<String> Reason;
	@FXML
	private RadioButton rbYes;
	@FXML
	private RadioButton rbNo;
	@FXML
	private TextField Party;
	@FXML
	private Label email_pop_label;
	@FXML
	private TextField Email;
	@FXML
	private Label Party_error;
	@FXML
	private Label Email_error;
	private Visitor visitor;

	ObservableList<String> list = FXCollections.observableArrayList("Business", "Pleasure", "Other");

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		visitor = VisitorContext.getInstance().currentVisitor();
		Reason.setItems(list);

		System.out.println("End Form Lat: " + visitor.getLatitude());
		System.out.println("End Form Lat: " + visitor.getLongitude());
		
	}

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	public void radioSelect(ActionEvent event) {

		if (rbYes.isSelected()) {
			email_pop_label.setVisible(true);
			Email.setVisible(true);
		}

		else if (rbNo.isSelected()) {
			email_pop_label.setVisible(false);
			Email.setVisible(false);
			Email_error.setText("");

		}
	}

	public void partyValidate(TextField Party, Label Party_error) {

		if (Party.getText() != null && !Party.getText().matches("[1-9][0-9]*") && !Party.getText().isEmpty()) {
			Party_error.setText("Please enter a valid number!");
		} else {
			Party_error.setText("");
		}

	}

	public void emailValidate(TextField Email, Label Email_error) {
		if (Email.getText() != null && !Email.getText().matches("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
				&& !Email.getText().isEmpty()) {
			Email_error.setText("Please enter a valid email address!");
		} else {
			Email_error.setText("");
		}
	}
	
	public void goBack(ActionEvent event) throws IOException {
		Parent newScene = FXMLLoader.load(getClass().getResource("MiddleForm.fxml"));
		Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		new_Stage.setTitle("Your Information");
		new_Stage.setScene(new Scene(newScene, 1680, 1200));
		new_Stage.show();

	}

	/*
	 * @FXML private void goToScreen2(ActionEvent event){
	 * myController.setScreen(SoftwareEngineering.screen2ID); }
	 */

	@FXML
	private void onSubmit(ActionEvent event) throws IOException {

		String reason = Reason.getValue();
		reason = (reason==null ? "No Response": reason);
		visitor.setTravelingFor(reason);		
		String partyText = Party.getText();
		Integer party = (partyText.isEmpty() ? 1 : Integer.parseInt(partyText));
		visitor.setParty(party.intValue());

		if (Email.getText() != null) {
			visitor.setEmail(Email.getText());
		}

		// partyValidate(Party, Party_error);
		emailValidate(Email, Email_error);

		if (rbYes.isSelected() && Email.getText().isEmpty()) {
			Email_error.setText("Please Provide your email address.");
		}
		
		

		if (Email_error.getText().isEmpty() || !rbYes.isSelected()) {
			System.out.println(visitor.getZip());
			JDBC.addVisitor(visitor);
			Parent newScene = FXMLLoader.load(getClass().getResource("Gratitude.fxml"));
			Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			new_Stage.setTitle("Your Information");
			new_Stage.setScene(new Scene(newScene, 1680, 1200));
			new_Stage.show();

		}

	}
}
