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
	private TableView<Visitor> visitorTable;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		List<Visitor> visitors = AdminJDBC.getVisitors();
		
		TableColumn<Visitor, String> fnameColumn = new TableColumn<>("First Name");
		fnameColumn.setCellValueFactory(new PropertyValueFactory<>("fname"));
		TableColumn<Visitor, String> lnameColumn = new TableColumn<>("Last Name");
		lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
		
		visitorTable = new TableView<>();
		visitorTable.setItems(getVisitors());
		visitorTable.getColumns().addAll(fnameColumn, lnameColumn);

	}
	
	private ObservableList<Visitor> getVisitors()
	{
		ObservableList<Visitor> visitors = FXCollections.observableArrayList();
		for(Visitor v: getTestVisitors())
		{
			visitors.add(v);
		}
		return visitors;
	}
	
	private List<Visitor> getTestVisitors()
	{
		List<Visitor> visitors = new ArrayList<Visitor>();
		visitors.add(new Visitor("Connor", "Dixon", "ccd817@gmail.com", true, "Business", "Interstate Sign", "30.430410", "-97.745597", "ULM"));
		return visitors;
	}

}
