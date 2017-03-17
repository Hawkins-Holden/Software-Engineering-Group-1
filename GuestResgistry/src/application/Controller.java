package application;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class Controller implements Initializable {

	@FXML
	private TextField flabel;
	@FXML
	private TextField mlabel;
	@FXML
	private TextField label;
	@FXML
	private TextField dlabel;
	@FXML
	private TextField elabel;
	@FXML
	private TextField plabel;
	@FXML
	private ComboBox<String> purposeLabel;
	@FXML
	private ComboBox<String> reasonLabel;
	@FXML
	private RadioButton rbYes;
	@FXML
	private RadioButton rbNo;
	@FXML
	private Label fnameError;
	@FXML
	private Label mnameError;
	@FXML
	private Label lnameError;
	@FXML
	private Label destinationError;
	@FXML
	private Label partyError;
	@FXML
	private Label emailError;
	@FXML
	private CheckBox checkLabel;

	private String Reason;
	private String Purpose;
	private String Fname;
	private String Mname;
	private String Lname;
	private String Email;
	private String Destination;
	private boolean choice;
	private String Party;

	ObservableList<String> list = FXCollections.observableArrayList("Business", "Pleasure", "Other");
	ObservableList<String> slist = FXCollections.observableArrayList("Billboard", "Interstate Sign", "Other");

	public void radioSelect(ActionEvent eve) {

		if (rbYes.isSelected()) {

			choice = true;
		}

		else if (rbNo.isSelected()) {
			choice = false;
		}
	}

	public void submitButton(ActionEvent event) throws IOException {

		Fname = flabel.getText();
		Mname = mlabel.getText();
		Lname = label.getText();
		Email = elabel.getText();
		Destination = dlabel.getText();
		Party = plabel.getText();
		Reason = reasonLabel.getValue();
		Purpose = purposeLabel.getValue();

		fnameValidate(flabel, fnameError);
		mnameValidate(mlabel, mnameError);
		lnameValidate(label, lnameError);
		destinationValidate(dlabel, destinationError);
		partyValidate(plabel, partyError);
		emailValidate(elabel, emailError);
		chValidate();

		if (fnameError.getText().isEmpty() && mnameError.getText().isEmpty() && lnameError.getText().isEmpty()
				&& destinationError.getText().isEmpty() && partyError.getText().isEmpty()
				&& emailError.getText().isEmpty()) {
			Parent closeScene = FXMLLoader.load(getClass().getResource("Confirmation.fxml"));
			Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			new_Stage.setTitle("Success!");
			new_Stage.setScene(new Scene(closeScene, 1680, 1200));
			new_Stage.show();
		}

		System.out.println(Fname + " " + Mname + " " + Lname + " " + Email + " " + Destination + ".");

	}

	public void fnameValidate(TextField flabel, Label fnameError) {

		if (flabel.getText() != null && !flabel.getText().matches("[a-zA-Z]+") && !flabel.getText().isEmpty()) {
			fnameError.setText("Please enter a valid First Name!");
		} else {
			fnameError.setText("");
		}

	}

	public void mnameValidate(TextField mlabel, Label mnameError) {

		if (mlabel.getText() != null && !mlabel.getText().matches("[a-zA-Z]+") && !mlabel.getText().isEmpty() || mlabel.getText().length()>1) {
			mnameError.setText("Please enter a valid Middle Initial!");
		} else {
			mnameError.setText("");
		}

	}

	public void lnameValidate(TextField label, Label lnameError) {

		if (label.getText() != null && !label.getText().matches("[a-zA-Z]+") && !label.getText().isEmpty()) {
			lnameError.setText("Please enter a valid Last name!");
		} else {
			lnameError.setText("");
		}

	}

	public void destinationValidate(TextField dlabel, Label destinationError) {

		if (dlabel.getText() != null && !dlabel.getText().matches("[a-zA-Z]+") && !dlabel.getText().isEmpty()) {
			destinationError.setText("Please enter a valid City!");
		} else {
			destinationError.setText("");
		}

	}

	public void partyValidate(TextField plabel, Label partyError) {

		if (plabel.getText() != null && !plabel.getText().matches("[1-9][0-9]+") && !plabel.getText().isEmpty()) {
			partyError.setText("Please enter a valid number!");
		} else {
			partyError.setText("");
		}

	}

	public void checkValidate(ActionEvent checkEvent) {
		if (checkLabel.isSelected() && elabel.getText().isEmpty()) {
			emailError.setText("Please enter your email if you want to opt-in.");
		} else {
			if (elabel.getText() != null
					&& !elabel.getText().matches("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
					&& !elabel.getText().isEmpty()) {
				emailError.setText("Please enter a valid email address!");
			} else {
				emailError.setText("");
			}
		}
	}

	public void chValidate() {
		if (checkLabel.isSelected() && elabel.getText().isEmpty()) {
			emailError.setText("Please enter your email if you want to opt-in.");
		} else {
			if (elabel.getText() != null
					&& !elabel.getText().matches("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
					&& !elabel.getText().isEmpty()) {
				emailError.setText("Please enter a valid email address!");
			} else {
				emailError.setText("");
			}
		}
	}

	public void emailValidate(TextField elabel, Label emailError) {
		if (elabel.getText() != null
				&& !elabel.getText().matches("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
				&& !elabel.getText().isEmpty()) {
			emailError.setText("Please enter a valid email address!");
		} else {
			emailError.setText("");
		}
	}

	public void resetButton(ActionEvent e) {

		flabel.clear();
		mlabel.clear();
		label.clear();
		plabel.clear();
		dlabel.clear();
		elabel.clear();
		fnameError.setText("");
		mnameError.setText("");
		lnameError.setText("");
		destinationError.setText("");
		partyError.setText("");
		emailError.setText("");
		checkLabel.setSelected(false);
		rbYes.setSelected(false);
		rbNo.setSelected(false);
		reasonLabel.valueProperty().set(null);
		purposeLabel.valueProperty().set(null);

	}

	public void exitButtonClicked() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Warning!");
		alert.setContentText("Are you sure you want to exit?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			Platform.exit();
		}

		else {

			alert.close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		purposeLabel.setItems(list);
		reasonLabel.setItems(slist);
	}

}
