package adminApplication;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.util.*;

import application.Visitor;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings("restriction")
public class VisitorViewController implements Initializable {

	@FXML
	private Button addButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button editButton;
	@FXML
	private Button refreshButton;
	@FXML
	private DatePicker startDatePicker;
	@FXML
	private DatePicker endDatePicker;
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
		startDatePicker.setValue(LocalDate.now().minusYears(1));
		endDatePicker.setValue(LocalDate.now());
		refreshTable();
	}

	public void refreshTable() {
		fnameColumn.setCellValueFactory(new PropertyValueFactory<>("fname"));
		lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		metroColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
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

		data = getVisitors(startDatePicker.getValue(), endDatePicker.getValue());

		visitorTable.setItems(null);
		visitorTable.setItems(data);
	}

	public void addVisitor() {
		// TODO: point to Form
	}

	public void editVisitor() {
		VisitorDetails visitorToEdit = visitorTable.getSelectionModel().getSelectedItem();
		// TODO: point to form
	}

	public void deleteVisitor() {
		VisitorDetails visitorToEdit = visitorTable.getSelectionModel().getSelectedItem();
		//todo: point to jdbc
	}

	private ObservableList<VisitorDetails> getVisitors(LocalDate start, LocalDate end) {
		ObservableList<VisitorDetails> visitors = FXCollections.observableArrayList();
		for (VisitorDetails v : AdminJDBC.getVisitorsFromDateRange(start, end)) {
			visitors.add(v);
		}
		return visitors;
	}

	private List<VisitorDetails> getTestVisitors() {
		List<VisitorDetails> visitors = new ArrayList<VisitorDetails>();
		/*
		 * int id, String fname, String lname, String email, String latitude,
		 * String longitude, String city, String state, String country, Integer
		 * party, String heard, String hotel, String destination, Boolean
		 * repeatVisit, String travelingFor, Date visitingDay
		 */
		visitors.add(new VisitorDetails(1, "Connor", "Dixon", "ccd817@gmail.com", "30.430410", "-97.745597", "Austin", "Austin",
				"TX", "United States", (Integer) 1, "Interstate Sign", "Yes", "ULM", false, "Other", new Date()));
		visitors.add(new VisitorDetails(2, "Bonnor", "Nixon", "bcn817@gmail.com", "30.430410", "-97.745597", "Angola",
				"Baton Rouge", "LA", "United States", (Integer) 1, "Interstate Sign", "Yes", "Duck Dynasty", true, "Other", new Date()));
		return visitors;
	}

}