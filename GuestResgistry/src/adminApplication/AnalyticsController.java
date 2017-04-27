package adminApplication;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import netscape.javascript.JSObject;
import java.util.Set;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.util.HashSet;

@SuppressWarnings("restriction")
public class AnalyticsController implements Initializable {

	final String accessToken = "f90e380a-300b-4a62-822e-5517f9887be3";
	// filterControls
	@FXML
	private TextField citiesMenuText;
	@FXML
	private TextField filterCity;
	@FXML
	private TextField filterCountry;
	@FXML
	private TextField filterState;
	@FXML
	private Button clearButton;
	@FXML
	private Button refreshButton;
	@FXML
	private Button exportButton;
	@FXML
	private Button exportEmailButton;
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
	private TableColumn<VisitorDetails, String> zipColumn;
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
	@FXML
	private LineChart<String, Number> lineChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;

	private static ObservableList<VisitorDetails> data;

	@FXML
	private WebView webView;
	private WebEngine engine;
	JSObject window;

	public void displayWeb() {
		engine = webView.getEngine();
		final String hellohtml = "adminMap.html"; // HTML file to view in web
													// view
		URL urlHello = getClass().getResource(hellohtml);
		engine.load(urlHello.toExternalForm());

		// ----------------------------------------------

		engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {

				if (newState == State.SUCCEEDED) {
					window = (JSObject) engine.executeScript("window");
					window.setMember("app", new JavascriptComm());
					System.out.println("stateChange");
					populateMap();
				}
			}

		});

		// ----------------------------------------------

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		refreshMenus();
		startDatePicker.setValue(LocalDate.now().minusYears(1));
		endDatePicker.setValue(LocalDate.now());
		engine = webView.getEngine();
		refreshData();

		filterCity.textProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				filterVisitorByCity((String) oldValue, (String) newValue);
			}
		});

		filterCountry.textProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				filterVisitorByCountry((String) oldValue, (String) newValue);
			}
		});

		filterState.textProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				filterVisitorByState((String) oldValue, (String) newValue);
			}
		});
	}

	public void filterVisitorByCountry(String oldValue, String newValue) {
		ObservableList<VisitorDetails> filteredList = FXCollections.observableArrayList();
		if ((filterCountry == null) || (newValue.length() < oldValue.length()) || newValue == null) {
			visitorTable.setItems(data);
		} else {
			newValue = newValue.toUpperCase();
			for (VisitorDetails visitors : visitorTable.getItems()) {
				String filterByCountry = visitors.getCountry();
				if (filterByCountry.toUpperCase().contains(newValue)) {
					filteredList.add(visitors);
				}
			}
			visitorTable.setItems(filteredList);
		}

	}

	public void filterVisitorByCity(String oldValue, String newValue) {
		ObservableList<VisitorDetails> filteredList = FXCollections.observableArrayList();
		if ((filterCity == null) || (newValue.length() < oldValue.length()) || newValue == null) {
			visitorTable.setItems(data);
		} else {
			newValue = newValue.toUpperCase();
			for (VisitorDetails visitors : visitorTable.getItems()) {
				String filterByCity = visitors.getCity();
				if (filterByCity.toUpperCase().contains(newValue)) {
					filteredList.add(visitors);
				}
			}
			visitorTable.setItems(filteredList);
		}

	}

	public void filterVisitorByState(String oldValue, String newValue) {
		ObservableList<VisitorDetails> filteredList = FXCollections.observableArrayList();
		if ((filterState == null) || (newValue.length() < oldValue.length()) || newValue == null) {
			visitorTable.setItems(data);
		} else {
			newValue = newValue.toUpperCase();
			for (VisitorDetails visitors : visitorTable.getItems()) {
				String filterByState = visitors.getState();
				if (filterByState.toUpperCase().contains(newValue)) {
					filteredList.add(visitors);
				}
			}
			visitorTable.setItems(filteredList);
		}

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
		refreshChart();
		displayWeb();

	}

	public void refreshTable() {
		fnameColumn.setCellValueFactory(new PropertyValueFactory<>("fname"));
		lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		metroColumn.setCellValueFactory(new PropertyValueFactory<>("metro"));
		cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
		stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
		countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
		zipColumn.setCellValueFactory(new PropertyValueFactory<>("zip"));
		partyColumn.setCellValueFactory(new PropertyValueFactory<>("party"));
		heardColumn.setCellValueFactory(new PropertyValueFactory<>("heard"));
		hotelColumn.setCellValueFactory(new PropertyValueFactory<>("hotel"));
		destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
		repeatColumn.setCellValueFactory(new PropertyValueFactory<>("repeatVisit"));
		reasonColumn.setCellValueFactory(new PropertyValueFactory<>("travelingFor"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("visitingDay"));
		// partyColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		visitorTable.setItems(null);
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
			boolean first = true;
			for (MenuItem cityItem : citiesMenuButton.getItems()) {
				CheckMenuItem city = (CheckMenuItem) cityItem;
				if (city.isSelected()) {
					String cityString = city.getText().split(",")[0];
					if (first) {
						query.append(" AND City = '" + cityString + "' OR Metro = '" + cityString + "'");
						first = false;
					} else {
						query.append(" OR City = '" + cityString + "' OR Metro = '" + cityString + "'");
					}
				}
			}
			for (MenuItem stateItem : statesMenuButton.getItems()) {
				CheckMenuItem state = (CheckMenuItem) stateItem;
				if (state.isSelected()) {
					if (first) {
						query.append(" AND State = '" + state.getText() + "'");
						first = false;
					} else {
						query.append(" OR State = '" + state.getText() + "'");
					}
				}
			}
			for (MenuItem countryItem : countriesMenuButton.getItems()) {
				CheckMenuItem country = (CheckMenuItem) countryItem;
				if (country.isSelected()) {
					if (first) {
						query.append(" AND Country = '" + country.getText() + "'");
						first = false;
					} else {
						query.append(" OR Country = " + country.getText() + "'");
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
						query.append(" AND TravelingFor = '" + reason.getText() + "'");
						first = false;
					} else {
						query.append(" OR TravelingFor = " + reason.getText() + "'");
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
						query.append(" AND Heard = '" + reference.getText() + "'");
						first = false;
					} else {
						query.append(" OR Heard = '" + reference.getText() + "'");
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
						query.append(" AND Hotel = '" + hotel.getText() + "'");
						first = false;
					} else {
						query.append(" OR Hotel = '" + hotel.getText() + "'");
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
						query.append(" AND Destination = '" + destination.getText() + "'");
						first = false;
					} else {
						query.append(" OR Destination = '" + destination.getText() + "'");
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
						query.append(" AND RepeatVisitor = '" + repeat.getText() + "'");
						first = false;
					} else {
						query.append(" OR RepeatVisitor = '" + repeat.getText() + "'");
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

		System.out.println(query);
		ObservableList<VisitorDetails> visitors = FXCollections.observableArrayList();
		for (VisitorDetails v : AdminJDBC.getVisitorDetailsFromQuery(query.toString())) {
			visitors.add(v);
		}
		return visitors;
	}

	
	public ObservableList<VisitorDetails> getFilteredLatLongs() {
		Date startDate = Date.valueOf(startDatePicker.getValue());
		Date endDate = Date.valueOf(endDatePicker.getValue().plusDays(1));
		StringBuilder query = new StringBuilder(
				"SELECT Latitude, Longitude FROM visitors LEFT JOIN visits ON visitors.VisitorID = visits.VisitorID LEFT JOIN visitorlocations on visitors.VisitorID = visitorlocations.VisitorID WHERE visitors.VisitorID IS NOT NULL");
		if (dateCheck.isSelected()) {
			query.append(" AND Visiting_Day >= '" + startDate.toString() + "' AND Visiting_Day <= '"
					+ endDate.toString() + "'");
		}
		if (areaCheck.isSelected()) {
			boolean first = true;
			for (MenuItem cityItem : citiesMenuButton.getItems()) {
				CheckMenuItem city = (CheckMenuItem) cityItem;
				if (city.isSelected()) {
					String cityString = city.getText().split(",")[0];
					if (first) {
						query.append(" AND City = '" + cityString + "' OR Metro = '" + cityString + "'");
						first = false;
					} else {
						query.append(" OR City = '" + cityString + "' OR Metro = '" + cityString + "'");
					}
				}
			}
			for (MenuItem stateItem : statesMenuButton.getItems()) {
				CheckMenuItem state = (CheckMenuItem) stateItem;
				if (state.isSelected()) {
					if (first) {
						query.append(" AND State = '" + state.getText() + "'");
						first = false;
					} else {
						query.append(" OR State = '" + state.getText() + "'");
					}
				}
			}
			for (MenuItem countryItem : countriesMenuButton.getItems()) {
				CheckMenuItem country = (CheckMenuItem) countryItem;
				if (country.isSelected()) {
					if (first) {
						query.append(" AND Country = '" + country.getText() + "'");
						first = false;
					} else {
						query.append(" OR Country = " + country.getText() + "'");
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
						query.append(" AND TravelingFor = '" + reason.getText() + "'");
						first = false;
					} else {
						query.append(" OR TravelingFor = " + reason.getText() + "'");
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
						query.append(" AND Heard = '" + reference.getText() + "'");
						first = false;
					} else {
						query.append(" OR Heard = '" + reference.getText() + "'");
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
						query.append(" AND Hotel = '" + hotel.getText() + "'");
						first = false;
					} else {
						query.append(" OR Hotel = '" + hotel.getText() + "'");
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
						query.append(" AND Destination = '" + destination.getText() + "'");
						first = false;
					} else {
						query.append(" OR Destination = '" + destination.getText() + "'");
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
						query.append(" AND RepeatVisitor = '" + repeat.getText() + "'");
						first = false;
					} else {
						query.append(" OR RepeatVisitor = '" + repeat.getText() + "'");
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
		query.append("AND (Latitude IS NOT NULL AND Latitude != '' AND Latitude != 'null' AND Longitude IS NOT NULL AND Longitude != '' AND Longitude != 'null') ORDER BY Visiting_Day");

		System.out.println(query);
		ObservableList<VisitorDetails> visitors = FXCollections.observableArrayList();
		for (VisitorDetails v : AdminJDBC.getVisitorDetailsFromQuery(query.toString())) {
			visitors.add(v);
		}
		return visitors;
	}

	private void populateMap() {
		engine.executeScript("populateJSMap();");
	}

	public void goBack(ActionEvent e) throws IOException {
		Parent newScene = FXMLLoader.load(getClass().getResource("Platform.fxml"));
		Stage new_Stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		new_Stage.setTitle("Visitor Table");
		new_Stage.setScene(new Scene(newScene, 1000, 1000));
		new_Stage.show();
	}

	@FXML
	private void ExportAction(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		// Set extension filter
		if (!data.isEmpty()) {
			FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
			chooser.getExtensionFilters().add(filter);
			// Show save dialog
			File file = chooser.showSaveDialog(exportButton.getScene().getWindow());
			if (file != null) {
				try {
					saveXLSFile(file);
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {

		}
	}

	@FXML
	private void ExportEmailAction(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		// Set extension filter
		if (!data.isEmpty()) {
			FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
			chooser.getExtensionFilters().add(filter);
			// Show save dialog
			File file = chooser.showSaveDialog(exportButton.getScene().getWindow());
			if (file != null) {
				try {
					saveEmailList(file);
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			/*
			 * Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			 * alert.setTitle("Export to Constant Contact"); alert.
			 * setHeaderText("Would you like to export this mailing list to Constant Contact?"
			 * ); alert.setContentText("");
			 * 
			 * ButtonType buttonTypeOne = new ButtonType("Yes"); ButtonType
			 * buttonTypeCancel = new ButtonType("Cancel",
			 * ButtonBar.ButtonData.CANCEL_CLOSE);
			 * 
			 * alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
			 * 
			 * Optional<ButtonType> result = alert.showAndWait(); if
			 * (result.get() == buttonTypeOne) {
			 * if(exportToConstantContact(file) != 404){
			 * System.out.println("Successfully exported emails."); } } else {
			 * // ... user chose CANCEL or closed the dialog }
			 */

		} else {

		}
	}

	public void ImportAction() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Export to Constant Contact");
		alert.setHeaderText(
				"An import template file must be used for importing visitor information. Would you like to generate a template file?");
		alert.setContentText("");

		ButtonType buttonTypeOne = new ButtonType("Generate Template");
		ButtonType buttonTypeTwo = new ButtonType("Import from File");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			FileChooser chooser = new FileChooser();
			FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
			chooser.getExtensionFilters().add(filter);
			// Show save dialog
			File file = chooser.showSaveDialog(exportButton.getScene().getWindow());
			try {
				generateImportTemplate(file);
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (result.get() == buttonTypeTwo) {
			FileChooser chooser = new FileChooser();
			FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
			chooser.getExtensionFilters().add(filter);
			// Show save dialog

			try {
				File file = chooser.showOpenDialog(exportButton.getScene().getWindow());
				readXLSFile(file);
			} catch (Exception e) {
				Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
				errorAlert.setTitle("Error");
				errorAlert.setHeaderText("A " + e.getClass().getName()
						+ " was thrown. Details are shown below. Please contact an administrator for assistance.");
				errorAlert.setContentText(e.getMessage());
				ButtonType cancelButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);

				errorAlert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, cancelButton);
				errorAlert.showAndWait();
			}
		} else {

		}
		// ... user chose CANCEL or closed the dialog }
		refreshData();
	}

	public void generateImportTemplate(File file) throws IOException, WriteException {
		WritableWorkbook myexcel = Workbook.createWorkbook(file);
		WritableSheet mysheet = myexcel.createSheet("mySheet", 0);

		mysheet.addCell(new Label(0, 0, "First"));
		mysheet.addCell(new Label(1, 0, "Last"));
		mysheet.addCell(new Label(2, 0, "Email"));
		mysheet.addCell(new Label(3, 0, "From (City)"));
		mysheet.addCell(new Label(4, 0, "From (Metro)"));
		mysheet.addCell(new Label(5, 0, "From (State)"));
		mysheet.addCell(new Label(6, 0, "From (Country)"));
		mysheet.addCell(new Label(7, 0, "From (Zip)"));
		mysheet.addCell(new Label(8, 0, "Number in Party"));
		mysheet.addCell(new Label(9, 0, "How Referred"));
		mysheet.addCell(new Label(10, 0, "Stayed in M/WM Hotel"));
		mysheet.addCell(new Label(11, 0, "Destination"));
		mysheet.addCell(new Label(12, 0, "Repeat Visitor?"));
		mysheet.addCell(new Label(13, 0, "Reason For Travelling"));
		mysheet.addCell(new Label(14, 0, "Date of Visit"));

		myexcel.write();
		myexcel.close();
	}

	public void readXLSFile(File file) {
		Workbook w;
		List<VisitorDetails> newData = new ArrayList<VisitorDetails>();
		try {
			w = Workbook.getWorkbook(file);
			Sheet sheet = w.getSheet(0);

			for (int i = 1; i < sheet.getRows(); i++) {
				String firstName = sheet.getCell(0, i).getContents();
				String lastName = sheet.getCell(1, i).getContents();
				String email = sheet.getCell(2, i).getContents();
				String city = sheet.getCell(3, 1).getContents();
				String metro = sheet.getCell(4, i).getContents();
				String state = sheet.getCell(5, i).getContents();
				String country = sheet.getCell(6, i).getContents();
				String zipString = sheet.getCell(7, i).getContents();
				Integer zip = (zipString.isEmpty() ? null : Integer.parseInt(zipString));
				String partyString = sheet.getCell(8, i).getContents();
				Integer party = (partyString.isEmpty() ? null : Integer.parseInt(partyString));
				String referred = sheet.getCell(9, i).getContents();
				if (referred.equalsIgnoreCase("Billboard")) {
					referred = "Billboard";
				} else if (referred.equalsIgnoreCase("Interstate Sign")) {
					referred = "Interstate Sign";
				} else if (referred.equalsIgnoreCase("Other")) {
					referred = "Other";
				} else {
					referred = "No Response";
				}
				String hotel = sheet.getCell(10, i).getContents();
				if (hotel.equalsIgnoreCase("Yes") || hotel.equalsIgnoreCase("Y")) {
					hotel = "Yes";
				} else if (hotel.equalsIgnoreCase("No") || hotel.equalsIgnoreCase("N")) {
					hotel = "No";
				} else {
					hotel = "No Response";
				}
				String destination = sheet.getCell(11, i).getContents();
				String repeatString = sheet.getCell(12, i).getContents();
				boolean repeat = false;
				if (repeatString.equalsIgnoreCase("Yes") || repeatString.equalsIgnoreCase("Y")
						|| repeatString.equalsIgnoreCase("True") || repeatString.equalsIgnoreCase("T")) {
					repeat = true;
				}
				String travelingFor = sheet.getCell(13, i).getContents();
				if (travelingFor.equalsIgnoreCase("Business")) {
					travelingFor = "Business";
				} else if (travelingFor.equalsIgnoreCase("Pleasure")) {
					travelingFor = "Pleasure";
				} else if (travelingFor.equalsIgnoreCase("Convention")) {
					travelingFor = "Convention";
				} else if (travelingFor.equalsIgnoreCase("Other")) {
					travelingFor = "Other";
				} else {
					travelingFor = "No Response";
				}
				String dateString = sheet.getCell(14, i).getContents();
				java.util.Date visitingDay;
				if (!dateString.isEmpty()) {
					DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
					visitingDay = (java.util.Date) formatter.parse(dateString);

					// cal.set(Integer.parseInt(times[2]),
					// Integer.parseInt(times[0]) - 1,
					// Integer.parseInt(times[1]));

					// visitingDay = cal.getTime();
				} else {
					visitingDay = null;
				}
				newData.add(new VisitorDetails(firstName, lastName, email, city, metro, state, country, zip, party,
						referred, hotel, destination, repeat, travelingFor, visitingDay));
			}
			AdminJDBC.addVisitors(newData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveXLSFile(File file) throws IOException, WriteException {
		WritableWorkbook myexcel = Workbook.createWorkbook(file);
		WritableSheet mysheet = myexcel.createSheet("mySheet", 0);

		// TODO: set this up with only the checked columns in the table
		mysheet.addCell(new Label(0, 0, "This report was generated on " + getDate() + " at " + getTime() + "."));
		int x = 0;
		mysheet.addCell(new Label(x, 1, "First"));
		x++;
		mysheet.addCell(new Label(x, 1, "Last"));
		x++;
		mysheet.addCell(new Label(x, 1, "Email"));
		x++;
		mysheet.addCell(new Label(x, 1, "From (City)"));
		x++;
		mysheet.addCell(new Label(x, 1, "From (Metro)"));
		x++;
		mysheet.addCell(new Label(x, 1, "From (State)"));
		x++;
		mysheet.addCell(new Label(x, 1, "From (Country)"));
		x++;
		mysheet.addCell(new Label(x, 1, "From (Zip)"));
		x++;
		mysheet.addCell(new Label(x, 1, "Number in Party"));
		x++;
		mysheet.addCell(new Label(x, 1, "How Referred"));
		x++;
		mysheet.addCell(new Label(x, 1, "Stayed in M/WM Hotel"));
		x++;
		mysheet.addCell(new Label(x, 1, "Destination"));
		x++;
		mysheet.addCell(new Label(x, 1, "Repeat Visitor?"));
		x++;
		mysheet.addCell(new Label(x, 1, "Reason For Travelling"));
		x++;
		mysheet.addCell(new Label(x, 1, "Date of Visit"));

		for (int i = 0; i < data.size(); i++) {
			int j = 0;
			mysheet.addCell(formatData(i, j, data.get(i).getFname()));
			j++;
			mysheet.addCell(formatData(i, j, data.get(i).getLname()));
			j++;
			mysheet.addCell(formatData(i, j, data.get(i).getEmail()));
			j++;
			mysheet.addCell(formatData(i, j, data.get(i).getCity()));
			j++;
			mysheet.addCell(formatData(i, j, data.get(i).getMetro()));
			j++;
			mysheet.addCell(formatData(i, j, data.get(i).getState()));
			j++;
			mysheet.addCell(formatData(i, j, data.get(i).getCountry()));
			j++;
			mysheet.addCell(formatData(i, j, data.get(i).getZip().toString()));
			j++;
			mysheet.addCell(formatData(i, j, data.get(i).getParty().toString()));
			j++;
			mysheet.addCell(formatData(i, j, data.get(i).getHeard()));
			j++;
			mysheet.addCell(formatData(i, j, data.get(i).getHotel()));
			j++;
			mysheet.addCell(formatData(i, j, data.get(i).getDestination()));
			j++;
			mysheet.addCell(formatData(i, j, data.get(i).getRepeatVisit().toString()));
			j++;
			mysheet.addCell(formatData(i, j, data.get(i).getTravelingFor()));
			j++;
			mysheet.addCell(formatData(i, j, data.get(i).getVisitingDay().toString()));
			j++;
		}
		myexcel.write();
		myexcel.close();
	}

	public void saveEmailList(File file) throws IOException, WriteException {
		WritableWorkbook myexcel = Workbook.createWorkbook(file);
		WritableSheet mysheet = myexcel.createSheet("mySheet", 0);

		// TODO: set this up with only the checked columns in the table
		int x = 0;
		mysheet.addCell(new Label(x, 1, "First"));
		x++;
		mysheet.addCell(new Label(x, 1, "Last"));
		x++;
		mysheet.addCell(new Label(x, 0, "Email"));
		x++;
		for (int i = 0; i < data.size() - 1; i++) {
			int j = 0;
			mysheet.addCell(formatData(i - 1, j, data.get(i).getFname()));
			j++;
			mysheet.addCell(formatData(i - 1, j, data.get(i).getLname()));
			j++;
			mysheet.addCell(formatData(i - 1, j, data.get(i).getEmail()));
			j++;
		}
		myexcel.write();
		myexcel.close();
	}

	private Label formatData(int i, int j, String data) {
		return new Label(j, i + 2, data);
	}

	private String getTime() {
		Calendar timestamp = Calendar.getInstance();
		return timestamp.get(Calendar.HOUR) + ":" + timestamp.get(Calendar.MINUTE) + ":"
				+ timestamp.get(Calendar.SECOND) + timestamp.get(Calendar.AM_PM);
	}

	private String getDate() {
		Calendar timestamp = Calendar.getInstance();
		return (timestamp.get(Calendar.MONTH) + 1) + "/" + timestamp.get(Calendar.DATE) + "/"
				+ timestamp.get(Calendar.YEAR);
	}

	@SuppressWarnings("unchecked")
	private void refreshChart() {
		lineChart.getData().clear();
		lineChart.getData().addAll(getChartData());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Series getChartData() {
		XYChart.Series series = new XYChart.Series();
		Set<int[]> months = new HashSet<int[]>();
		Calendar cal = Calendar.getInstance();
		for (VisitorDetails datum : data) {
			int[] monthYear = new int[2];
			Date dat = (Date) datum.getVisitingDay();
			if (dat != null) {
				cal.setTime(dat);
				monthYear[0] = cal.get(Calendar.MONTH);
				monthYear[1] = cal.get(Calendar.YEAR);
				months.add(monthYear);
			}
		}

		for (int[] monthYear : months) {
			int count = 0;
			for (VisitorDetails datum : data) {
				Date dat = (Date) datum.getVisitingDay();
				if (dat != null) {
					cal.setTime(datum.getVisitingDay());
					if (monthYear[0] == cal.get(Calendar.MONTH) && monthYear[1] == cal.get(Calendar.YEAR)) {
						if (datum.getParty() != null || datum.getParty() > 0) {
							count += datum.getParty();
						} else {
							count++;
						}
					}
				}
			}
			String monthString;
			switch (monthYear[0]) {
			case Calendar.JANUARY:
				monthString = "January ";
				break;
			case Calendar.FEBRUARY:
				monthString = "February ";
				break;
			case Calendar.MARCH:
				monthString = "March ";
				break;
			case Calendar.APRIL:
				monthString = "April ";
				break;
			case Calendar.MAY:
				monthString = "May ";
				break;
			case Calendar.JUNE:
				monthString = "June ";
				break;
			case Calendar.JULY:
				monthString = "July ";
				break;
			case Calendar.AUGUST:
				monthString = "August ";
				break;
			case Calendar.SEPTEMBER:
				monthString = "September ";
				break;
			case Calendar.OCTOBER:
				monthString = "October ";
				break;
			case Calendar.NOVEMBER:
				monthString = "November ";
				break;
			case Calendar.DECEMBER:
				monthString = "December ";
				break;
			default:
				monthString = "Invalid month";
				break;
			}
			monthString += monthYear[1];
			series.getData().add(new XYChart.Data(monthString, count));
		}
		return series;
	}

	public int exportToConstantContact(File fileToUse) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		final HttpPost httppost = new HttpPost("https://api.constantcontact.com/v2/activities/addcontacts");

		httppost.addHeader("Authorization", "Bearer " + this.accessToken);
		httppost.addHeader("Accept", "application/json");
		httppost.addHeader("content-type", "multipart/form-data");

		final FileBody data = new FileBody(fileToUse);
		final String listIds = "1";
		StringBody lists = null;
		lists = new StringBody(listIds, ContentType.APPLICATION_JSON);
		StringBody fileName = null;
		fileName = new StringBody(fileToUse.getName(), ContentType.APPLICATION_JSON);
		final MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
		reqEntity.addPart("file_name", fileName);
		reqEntity.addPart("lists", lists);
		reqEntity.addPart("data", data);

		httppost.setEntity(reqEntity.build());

		HttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final HttpEntity resEntity = response.getEntity();

		try {
			EntityUtils.consume(resEntity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int code = response.getStatusLine().getStatusCode();
		try {
			httpclient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

	public static ArrayList<String[]> getLatLongData() {
		ArrayList<String[]> locations = new ArrayList<String[]>();
		
		for (VisitorDetails vd : data) {
			String[] latlng = new String[2];
			if(!(vd.getLatitude()==null || vd.getLatitude().isEmpty() || vd.getLatitude().equals("null"))){
			latlng[0] = vd.getLatitude();
			latlng[1] = vd.getLongitude();
			locations.add(latlng);
			}
		}
		return locations;
	}

	public static ArrayList<Integer> getZipData() {
		ArrayList<Integer> locations = new ArrayList<Integer>();
		for (VisitorDetails vd : data) {
			locations.add(vd.getZip());
		}
		return locations;
	}
}
