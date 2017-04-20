package adminApplication;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings("restriction")
public class AnalyticsController implements Initializable {

	// filterControls
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
	@FXML
	private CheckMenuItem providedMenuItem;
	@FXML
	private CheckMenuItem notProvidedMenuItem;
	@FXML
	private TableView<VisitorDetails> visitorTable;
	@FXML
	private TableColumn<VisitorDetails, String> fnameColumn;
	@FXML
	private TableColumn<VisitorDetails, String> lnameColumn;
	@FXML
	private TableColumn<VisitorDetails, String> emailColumn;
	@FXML
	private TableColumn<VisitorDetails, String> metroColumn;
	@FXML
	private TableColumn<VisitorDetails, String> cityColumn;
	@FXML
	private TableColumn<VisitorDetails, String> stateColumn;
	@FXML
	private TableColumn<VisitorDetails, String> countryColumn;
	@FXML
	private TableColumn<VisitorDetails, Integer> partyColumn;
	@FXML
	private TableColumn<VisitorDetails, String> heardColumn;
	@FXML
	private TableColumn<VisitorDetails, String> destinationColumn;
	@FXML
	private TableColumn<VisitorDetails, String> hotelColumn;
	@FXML
	private TableColumn<VisitorDetails, String> repeatColumn;
	@FXML
	private TableColumn<VisitorDetails, String> reasonColumn;
	@FXML
	private TableColumn<VisitorDetails, Date> dateColumn;

	private ObservableList<VisitorDetails> data;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		refreshMenus();
		startDatePicker.setValue(LocalDate.now().minusYears(1));
		endDatePicker.setValue(LocalDate.now());
	}

	public void refreshMenus() {
		List<String> cities = AdminJDBC.getCitiesandMetros();
		ObservableList<CheckMenuItem> cityItems = FXCollections.observableArrayList();
		for (String city : cities) {
			cityItems.add(new CheckMenuItem(city));

		}
		citiesMenuButton.getItems().clear();
		citiesMenuButton.getItems().addAll(cityItems);
		
		List<String> countries = AdminJDBC.getCountries();
		ObservableList<CheckMenuItem> countryItems = FXCollections.observableArrayList();
		for (String country : countries) {
			countryItems.add(new CheckMenuItem(country));
		}
		countriesMenuButton.getItems().clear();
		countriesMenuButton.getItems().addAll(countryItems);

		List<String> destinations = AdminJDBC.getDestinations();
		ObservableList<CheckMenuItem> destinationItems = FXCollections.observableArrayList();
		for (String destination : destinations) {
			destinationItems.add(new CheckMenuItem(destination));
		}
		destinationMenuButton.getItems().clear();
		destinationMenuButton.getItems().addAll(destinationItems);

		List<String> hotels = AdminJDBC.getHotels();
		ObservableList<CheckMenuItem> hotelItems = FXCollections.observableArrayList();
		for (String hotel : hotels) {
			hotelItems.add(new CheckMenuItem(hotel));
		}
		hotelMenuButton.getItems().clear();
		hotelMenuButton.getItems().addAll(hotelItems);

		List<String> references = AdminJDBC.getReferences();
		ObservableList<CheckMenuItem> referenceItems = FXCollections.observableArrayList();
		for (String reference : references) {
			referenceItems.add(new CheckMenuItem(reference));
		}
		referenceMenuButton.getItems().clear();
		referenceMenuButton.getItems().addAll(referenceItems);

		List<String> states = AdminJDBC.getStates();
		ObservableList<CheckMenuItem> stateItems = FXCollections.observableArrayList();
		for (String state : states) {
			stateItems.add(new CheckMenuItem(state));
		}
		statesMenuButton.getItems().clear();
		statesMenuButton.getItems().addAll(stateItems);

		List<String> travelingReasons = AdminJDBC.getReasons();
		ObservableList<CheckMenuItem> reasonItems = FXCollections.observableArrayList();
		for (String reason : travelingReasons) {
			reasonItems.add(new CheckMenuItem(reason));
		}
		travelingMenuButton.getItems().clear();
		travelingMenuButton.getItems().addAll(reasonItems);
	}

	public void refreshData() {
		data = getFilteredVisitorDetails();
		refreshTable();
		refreshMenus();
	}

	public void refreshTable() {
		fnameColumn.setCellValueFactory(new PropertyValueFactory<>("fname"));
		lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		metroColumn.setCellValueFactory(new PropertyValueFactory<>("metro"));
		cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
		stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
		countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
		partyColumn.setCellValueFactory(new PropertyValueFactory<>("party"));
		heardColumn.setCellValueFactory(new PropertyValueFactory<>("heard"));
		hotelColumn.setCellValueFactory(new PropertyValueFactory<>("hotel"));
		destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
		repeatColumn.setCellValueFactory(new PropertyValueFactory<>("repeatVisit"));
		reasonColumn.setCellValueFactory(new PropertyValueFactory<>("travelingFor"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("visitingDay"));

		visitorTable.setItems(data);
	}

	public ObservableList<VisitorDetails> getFilteredVisitorDetails() {
		Date startDate = Date.valueOf(startDatePicker.getValue());
		Date endDate = Date.valueOf(endDatePicker.getValue().plusDays(1));
		StringBuilder query = new StringBuilder(
				"SELECT * FROM visitors LEFT JOIN visits ON visitors.VisitorID = visits.VisitorID LEFT JOIN visitorlocations on visitors.VisitorID = visitorlocations.VisitorID WHERE visitors.VisitorID IS NOT NULL");
		if (dateCheck.isSelected()) {
			query.append(" AND Visiting_Day >= '" + startDate.toString() + "' AND Visiting_Day <= '"
					+ endDate.toString() + "'");
		}
		if (areaCheck.isSelected()) {
			for (MenuItem cityItem : citiesMenuButton.getItems()) {
				CheckMenuItem city = (CheckMenuItem) cityItem;
				boolean first = true;
				if (city.isSelected()) {
					if (first) {
						query.append(" AND City = " + city.getText() + " OR Metro = " + city.getText());
						first = false;
					} else {
						query.append(" OR City = " + city.getText() + " OR Metro = " + city.getText());
					}
				}
			}
			for (MenuItem stateItem : statesMenuButton.getItems()) {
				CheckMenuItem state = (CheckMenuItem) stateItem;
				boolean first = true;
				if (state.isSelected()) {
					if (first) {
						query.append(" AND State = " + state.getText());
						first = false;
					} else {
						query.append(" OR State = " + state.getText());
					}
				}
			}
			for (MenuItem countryItem : countriesMenuButton.getItems()) {
				CheckMenuItem country = (CheckMenuItem) countryItem;
				boolean first = true;
				if (country.isSelected()) {
					if (first) {
						query.append(" AND Country = " + country.getText());
						first = false;
					} else {
						query.append(" OR Country = " + country.getText());
					}
				}
			}
		}
		if (travelingCheck.isSelected()) {
			for (MenuItem reasonItem : travelingMenuButton.getItems()) {
				CheckMenuItem reason = (CheckMenuItem) reasonItem;
				boolean first = true;
				if (reason.isSelected()) {
					if (first) {
						query.append(" AND TravelingFor = " + reason.getText());
						first = false;
					} else {
						query.append(" OR TravelingFor = " + reason.getText());
					}
				}
			}
		}
		if (referredCheck.isSelected()) {
			for (MenuItem referenceItem : referenceMenuButton.getItems()) {
				CheckMenuItem reference = (CheckMenuItem) referenceItem;
				boolean first = true;
				if (reference.isSelected()) {
					if (first) {
						query.append(" AND Heard = " + reference.getText());
						first = false;
					} else {
						query.append(" OR Heard = " + reference.getText());
					}
				}
			}
		}
		if (hotelCheck.isSelected()) {
			for (MenuItem hotelItem : hotelMenuButton.getItems()) {
				CheckMenuItem hotel = (CheckMenuItem) hotelItem;
				boolean first = true;
				if (hotel.isSelected()) {
					if (first) {
						query.append(" AND Hotel = " + hotel.getText());
						first = false;
					} else {
						query.append(" OR Hotel = " + hotel.getText());
					}
				}
			}
		}
		if (destinationCheck.isSelected()) {
			for (MenuItem destinationItem : destinationMenuButton.getItems()) {
				CheckMenuItem destination = (CheckMenuItem) destinationItem;
				boolean first = true;
				if (destination.isSelected()) {
					if (first) {
						query.append(" AND Destination = " + destination.getText());
						first = false;
					} else {
						query.append(" OR Destination = " + destination.getText());
					}
				}
			}
		}
		if (repeatCheck.isSelected()) {
			for (MenuItem repeatItem : repeatMenuButton.getItems()) {
				CheckMenuItem repeat = (CheckMenuItem) repeatItem;
				boolean first = true;
				if (repeat.isSelected()) {
					if (first) {
						query.append(" AND RepeatVisitor = " + repeat.getText());
						first = false;
					} else {
						query.append(" OR RepeatVisitor = " + repeat.getText());
					}
				}
			}
		}
		if (emailCheck.isSelected()) {
			boolean first = true;
			if (providedMenuItem.isSelected()) {
				if (first) {
					query.append(" AND Email IS NOT NULL");
					first = false;
				} else {
					query.append(" OR Email IS NOT NULL");
				}
			}
			if (notProvidedMenuItem.isSelected()) {
				if (first) {
					query.append(" AND Email IS NULL");
					first = false;
				} else {
					query.append(" OR Email IS NULL");
				}
			}
		}
		query.append(" ORDER BY Visiting_Day");

		ObservableList<VisitorDetails> visitors = FXCollections.observableArrayList();
		for (VisitorDetails v : AdminJDBC.getVisitorDetailsFromQuery(query.toString())) {
			visitors.add(v);
		}
		return visitors;
	}

}
