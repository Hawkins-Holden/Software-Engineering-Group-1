package adminApplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

@SuppressWarnings("restriction")
public class AnalyticsController implements Initializable {

	//filterControls
	@FXML
	private Button clearButton;
	@FXML
	private Button refreshButton;
	@FXML
	private CheckBox areaCheck;
	@FXML
	private CheckBox dateCheck;
	@FXML
	private CheckBox destinationCheck;
	@FXML
	private CheckBox emailCheck;
	@FXML
	private CheckBox hotelCheck;
	@FXML
	private CheckBox referredCheck;
	@FXML
	private CheckBox repeatCheck;
	@FXML
	private CheckBox travelingCheck;
	@FXML
	private DatePicker endDatePicker;
	@FXML
	private DatePicker startDatePicker;
	@FXML
	private ContextMenu citiesMenu;
	@FXML
	private ContextMenu countriesMenu;
	@FXML
	private ContextMenu destinationMenu;
	@FXML
	private ContextMenu emailMenu;
	@FXML
	private ContextMenu hotelMenu;
	@FXML
	private ContextMenu referenceMenu;
	@FXML
	private ContextMenu repeatMenu;
	@FXML
	private ContextMenu statesMenu;
	@FXML
	private ContextMenu travelingMenu;
	@FXML
	private MenuButton citiesMenuButton;
	@FXML
	private MenuButton countriesMenuButton;
	@FXML
	private MenuButton destinationMenuButton;
	@FXML
	private MenuButton emailMenuButton;
	@FXML
	private MenuButton hotelMenuButton;
	@FXML
	private MenuButton referenceMenuButton;
	@FXML
	private MenuButton repeatMenuButton;
	@FXML
	private MenuButton statesMenuButton;
	@FXML
	private MenuButton travelingMenuButton;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		refreshMenus();
		startDatePicker.setValue(LocalDate.now().minusYears(1));
		endDatePicker.setValue(LocalDate.now());
	}
	
	public void refreshMenus() {
		List<String> cities = AdminJDBC.getCitiesandMetros();
		ObservableList<CheckMenuItem> cityItems = FXCollections.observableArrayList();
		for(String city: cities){
			cityItems.add(new CheckMenuItem(city));
		}
		citiesMenuButton.getItems().addAll(cityItems);
		
		List<String> countries = AdminJDBC.getCountries();
		ObservableList<CheckMenuItem> countryItems = FXCollections.observableArrayList();
		for(String country: countries){
			countryItems.add(new CheckMenuItem(country));
		}
		countriesMenuButton.getItems().addAll(countryItems);
		
		List<String> destinations = AdminJDBC.getDestinations();
		ObservableList<CheckMenuItem> destinationItems = FXCollections.observableArrayList();
		for(String destination: destinations){
			destinationItems.add(new CheckMenuItem(destination));
		}
		destinationMenuButton.getItems().addAll(destinationItems);
		
		ObservableList<CheckMenuItem> emailItems = FXCollections.observableArrayList();
		emailItems.add(new CheckMenuItem("Provided"));
		emailItems.add(new CheckMenuItem("Not Provided"));
		emailMenuButton.getItems().addAll(emailItems);
		
		List<String> hotels = AdminJDBC.getHotels();
		ObservableList<CheckMenuItem> hotelItems = FXCollections.observableArrayList();
		for(String hotel: hotels){
			hotelItems.add(new CheckMenuItem(hotel));
		}
		hotelMenuButton.getItems().addAll(hotelItems);
		
		List<String> references = AdminJDBC.getReferences();
		ObservableList<CheckMenuItem> referenceItems = FXCollections.observableArrayList();
		for(String reference: references){
			referenceItems.add(new CheckMenuItem(reference));
		}
		referenceMenuButton.getItems().addAll(referenceItems);
		
		List<String> states = AdminJDBC.getStates();
		ObservableList<CheckMenuItem> stateItems = FXCollections.observableArrayList();
		for(String state: states){
			stateItems.add(new CheckMenuItem(state));
		}
		statesMenuButton.getItems().addAll(stateItems);
		
		List<String> travelingReasons = AdminJDBC.getReasons();
		ObservableList<CheckMenuItem> reasonItems = FXCollections.observableArrayList();
		for(String reason: travelingReasons){
			reasonItems.add(new CheckMenuItem(reason));
		}
		travelingMenuButton.getItems().addAll(reasonItems);
	}

}
