package adminApplication;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import application.Visitor;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.util.Callback;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.BooleanStringConverter;

@SuppressWarnings("restriction")
public class VisitorViewController implements Initializable {

   @FXML
   private Button addButton;
   @FXML
   private Button deleteButton;
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
   private TableColumn<VisitorDetails, Integer> zipColumn;  
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
      metroColumn.setCellValueFactory(new PropertyValueFactory<>("metro"));
      cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
      stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
      countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
      zipColumn.setCellValueFactory(new PropertyValueFactory<>("zip"));
      partyColumn.setCellValueFactory(new PropertyValueFactory<>("party"));
      heardColumn.setCellValueFactory(new PropertyValueFactory<>("heard"));
      hotelColumn.setCellValueFactory(new PropertyValueFactory<>("hotel"));
      destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
      repeatColumn.setCellValueFactory(new PropertyValueFactory<>("repeatVisitString"));
      reasonColumn.setCellValueFactory(new PropertyValueFactory<>("travelingFor"));
      dateColumn.setCellValueFactory(new PropertyValueFactory<>("visitingDay"));
   
      data = getVisitors(startDatePicker.getValue(), endDatePicker.getValue());
   
      visitorTable.setItems(null);
      visitorTable.setItems(data);
      visitorTable.setEditable(true);
   	
      fnameColumn.setOnEditCommit(e -> fname_OnEditCommit(e));
      fnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
      lnameColumn.setOnEditCommit(e -> lname_OnEditCommit(e));
      lnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
      emailColumn.setOnEditCommit(e -> email_OnEditCommit(e));
      emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
      metroColumn.setOnEditCommit(e -> metro_OnEditCommit(e));
      metroColumn.setCellFactory(TextFieldTableCell.forTableColumn());
      cityColumn.setOnEditCommit(e -> city_OnEditCommit(e));
      cityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
      stateColumn.setOnEditCommit(e -> state_OnEditCommit(e));
      stateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
      countryColumn.setOnEditCommit(e -> country_OnEditCommit(e));
      countryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
      zipColumn.setOnEditCommit(e -> zip_OnEditCommit(e));
      zipColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      partyColumn.setOnEditCommit(e -> party_OnEditCommit(e));
      partyColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      heardColumn.setOnEditCommit(e -> heard_OnEditCommit(e));
      heardColumn.setCellFactory(TextFieldTableCell.forTableColumn());
      destinationColumn.setOnEditCommit(e -> destination_OnEditCommit(e));
      destinationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
      hotelColumn.setOnEditCommit(e -> hotel_OnEditCommit(e));
      hotelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
   	  repeatColumn.setOnEditCommit(e -> repeat_OnEditCommit(e));
   	  repeatColumn.setCellFactory(TextFieldTableCell.forTableColumn());
      reasonColumn.setOnEditCommit(e -> reason_OnEditCommit(e));
      reasonColumn.setCellFactory(TextFieldTableCell.forTableColumn());
   
      visitorTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
   }

   private void fname_OnEditCommit(CellEditEvent<VisitorDetails, String> e) {
      TableColumn.CellEditEvent<VisitorDetails, String> cellEditEvent;
      cellEditEvent = (TableColumn.CellEditEvent<VisitorDetails, String>) e;
      VisitorDetails visitor = cellEditEvent.getRowValue();
      visitor.setFname(cellEditEvent.getNewValue()); 
      AdminJDBC.updateVisitorDetails(visitor);
   }
	
   private void lname_OnEditCommit(CellEditEvent<VisitorDetails, String> e) {
      TableColumn.CellEditEvent<VisitorDetails, String> cellEditEvent;
      cellEditEvent = (TableColumn.CellEditEvent<VisitorDetails, String>) e;
      VisitorDetails visitor = cellEditEvent.getRowValue();
      visitor.setLname(cellEditEvent.getNewValue()); 
      AdminJDBC.updateVisitorDetails(visitor);
   }
	
   private void email_OnEditCommit(CellEditEvent<VisitorDetails, String> e) {
      TableColumn.CellEditEvent<VisitorDetails, String> cellEditEvent;
      cellEditEvent = (TableColumn.CellEditEvent<VisitorDetails, String>) e;
      VisitorDetails visitor = cellEditEvent.getRowValue();
      visitor.setEmail(cellEditEvent.getNewValue()); 
      AdminJDBC.updateVisitorDetails(visitor);
   }
	
   private void metro_OnEditCommit(CellEditEvent<VisitorDetails, String> e) {
      TableColumn.CellEditEvent<VisitorDetails, String> cellEditEvent;
      cellEditEvent = (TableColumn.CellEditEvent<VisitorDetails, String>) e;
      VisitorDetails visitor = cellEditEvent.getRowValue();
      visitor.setMetro(cellEditEvent.getNewValue()); 
      AdminJDBC.updateVisitorDetails(visitor);
   }
	
   private void city_OnEditCommit(CellEditEvent<VisitorDetails, String> e) {
      TableColumn.CellEditEvent<VisitorDetails, String> cellEditEvent;
      cellEditEvent = (TableColumn.CellEditEvent<VisitorDetails, String>) e;
      VisitorDetails visitor = cellEditEvent.getRowValue();
      visitor.setCity(cellEditEvent.getNewValue()); 
      AdminJDBC.updateVisitorDetails(visitor);
   }
	
   private void state_OnEditCommit(CellEditEvent<VisitorDetails, String> e) {
      TableColumn.CellEditEvent<VisitorDetails, String> cellEditEvent;
      cellEditEvent = (TableColumn.CellEditEvent<VisitorDetails, String>) e;
      VisitorDetails visitor = cellEditEvent.getRowValue();
      visitor.setState(cellEditEvent.getNewValue()); 
      AdminJDBC.updateVisitorDetails(visitor);
   }
	
   private void country_OnEditCommit(CellEditEvent<VisitorDetails, String> e) {
      TableColumn.CellEditEvent<VisitorDetails, String> cellEditEvent;
      cellEditEvent = (TableColumn.CellEditEvent<VisitorDetails, String>) e;
      VisitorDetails visitor = cellEditEvent.getRowValue();
      visitor.setCountry(cellEditEvent.getNewValue()); 
      AdminJDBC.updateVisitorDetails(visitor);
   }
   
   private void zip_OnEditCommit(CellEditEvent<VisitorDetails, Integer> e){
	      TableColumn.CellEditEvent<VisitorDetails, Integer> cellEditEvent;
	      cellEditEvent = (TableColumn.CellEditEvent<VisitorDetails, Integer>) e;
	      VisitorDetails visitor = cellEditEvent.getRowValue();
	      visitor.setZip(cellEditEvent.getNewValue());
	      AdminJDBC.updateVisitorDetails(visitor);
	   }
	
	
   private void party_OnEditCommit(CellEditEvent<VisitorDetails, Integer> e){
      TableColumn.CellEditEvent<VisitorDetails, Integer> cellEditEvent;
      cellEditEvent = (TableColumn.CellEditEvent<VisitorDetails, Integer>) e;
      VisitorDetails visitor = cellEditEvent.getRowValue();
      visitor.setParty(cellEditEvent.getNewValue());
      AdminJDBC.updateVisitorDetails(visitor);
   }
	
   private void heard_OnEditCommit(CellEditEvent<VisitorDetails, String> e) {
      TableColumn.CellEditEvent<VisitorDetails, String> cellEditEvent;
      cellEditEvent = (TableColumn.CellEditEvent<VisitorDetails, String>) e;
      VisitorDetails visitor = cellEditEvent.getRowValue();
      visitor.setHeard(cellEditEvent.getNewValue()); 
      AdminJDBC.updateVisitorDetails(visitor);
   }
	
   private void destination_OnEditCommit(CellEditEvent<VisitorDetails, String> e) {
      TableColumn.CellEditEvent<VisitorDetails, String> cellEditEvent;
      cellEditEvent = (TableColumn.CellEditEvent<VisitorDetails, String>) e;
      VisitorDetails visitor = cellEditEvent.getRowValue();
      visitor.setDestination(cellEditEvent.getNewValue()); 
      AdminJDBC.updateVisitorDetails(visitor);
   }
	
   private void hotel_OnEditCommit(CellEditEvent<VisitorDetails, String> e) {
      TableColumn.CellEditEvent<VisitorDetails, String> cellEditEvent;
      cellEditEvent = (TableColumn.CellEditEvent<VisitorDetails, String>) e;
      VisitorDetails visitor = cellEditEvent.getRowValue();
      visitor.setHotel(cellEditEvent.getNewValue()); 
      AdminJDBC.updateVisitorDetails(visitor);
   }
   
   private void repeat_OnEditCommit(CellEditEvent<VisitorDetails, String> e) {
	      TableColumn.CellEditEvent<VisitorDetails, String> cellEditEvent;
	      cellEditEvent = (TableColumn.CellEditEvent<VisitorDetails, String>) e;
	      VisitorDetails visitor = cellEditEvent.getRowValue();
	      visitor.setRepeatVisitString(cellEditEvent.getNewValue()); 
	      AdminJDBC.updateVisitorDetails(visitor);
	   }
	
	
   private void reason_OnEditCommit(CellEditEvent<VisitorDetails, String> e) {
      TableColumn.CellEditEvent<VisitorDetails, String> cellEditEvent;
      cellEditEvent = (TableColumn.CellEditEvent<VisitorDetails, String>) e;
      VisitorDetails visitor = cellEditEvent.getRowValue();
      visitor.setTravelingFor(cellEditEvent.getNewValue()); 
      AdminJDBC.updateVisitorDetails(visitor);
   }
	
   
   public void goBack(ActionEvent e) throws IOException { 
	   Parent newScene = FXMLLoader.load(getClass().getResource("Platform.fxml"));
		Stage new_Stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		new_Stage.setTitle("Visitor Table");
		new_Stage.setScene(new Scene(newScene,1000,1000));
		new_Stage.show();
   }
	

   public void addVisitor(ActionEvent e) throws IOException {
	   Parent newScene = FXMLLoader.load(getClass().getResource("AdminViewForm.fxml"));
	   Stage new_Stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		new_Stage.setTitle("Form");
		new_Stage.setScene(new Scene(newScene,1000,1000));
		new_Stage.show();
   }

	

   public void deleteVisitor() {
      VisitorDetails visitorToDelete = visitorTable.getSelectionModel().getSelectedItem();
   	//if (!data.isEmpty()) {
      System.out.println("Delete Button Clicked!");
      Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "Confirm", ButtonType.OK, ButtonType.CANCEL);
      deleteAlert.setContentText("Are you sure you want to delete this?\n\nTHIS CANNOT BE UNDONE.");
      deleteAlert.showAndWait();
      if (deleteAlert.getResult() == ButtonType.OK) {
         AdminJDBC.deleteVisitor(visitorToDelete);
         data.removeAll(visitorTable.getSelectionModel().getSelectedItems());
         visitorTable.getSelectionModel().clearSelection();
      } 
      else {
         deleteAlert.close();
      }
   	//}
   }

   private ObservableList<VisitorDetails> getVisitors(LocalDate startDate, LocalDate endDate) {
      ObservableList<VisitorDetails> visitors = FXCollections.observableArrayList();
      StringBuilder query = new StringBuilder(
         	"SELECT * FROM visitors LEFT JOIN visits ON visitors.VisitorID = visits.VisitorID LEFT JOIN visitorlocations on visitors.VisitorID = visitorlocations.VisitorID WHERE visitors.VisitorID IS NOT NULL");
      query.append(" AND Visiting_Day >= '" + startDate.toString() + "' AND Visiting_Day <= '"
         		+ endDate.toString() + "'");
      for (VisitorDetails v : AdminJDBC.getVisitorDetailsFromQuery(query.toString())) {
         visitors.add(v);
      }
      return visitors;
   }

}
