/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitortable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Window;

/**
 *
 * @author anjan
 */
public class VisitorTableController implements Initializable {
    
    @FXML
    private TableView<Visitor> tableId;
    @FXML
    private TableColumn<Visitor, Integer> id;
    @FXML
    private TableColumn<Visitor, String> name;
    @FXML
    private TableColumn<Visitor, String> city;
    @FXML
    private TableColumn<Visitor, String> country;
    @FXML
    private TextField cityInput;
    @FXML
    private TextField countryInput; 
    
    private int count = 1; 
    
    ObservableList<Visitor> data = FXCollections.observableArrayList(
          
    new Visitor (count++, "James", "Monroe", "United States"),
            new Visitor (count++, "Joy", "San Jose", "United States"),
            new Visitor (count++, "Anjan", "Monroe", "United States"),
            new Visitor (count++, "Anjan", "Kathmandu", "Nepal"),
            new Visitor (count++, "Kaith", "Boston", "United States"),
            new Visitor (count++, "Jeniffer", "London", "England")

    ); 
    
    
    public void filterVisitorByCity(String oldValue, String newValue) {
        
        ObservableList<Visitor> filteredList = FXCollections.observableArrayList(); 
        if ((cityInput == null) || (newValue.length() < oldValue.length() ) || newValue == null) {
            tableId.setItems(data);    
        }
        else {
            newValue = newValue.toUpperCase();
            for (Visitor visitors: tableId.getItems()){
                String filterByCity = visitors.getCity();
                if (filterByCity.toUpperCase().contains(newValue)) {
                    filteredList.add(visitors); 
                }   
            }
            tableId.setItems(filteredList); 
        }   
    }
    
    public void filterVisitorByCountry(String oldValue, String newValue){
             ObservableList<Visitor> filteredList = FXCollections.observableArrayList(); 
        if ((countryInput == null) || (newValue.length() < oldValue.length() ) || newValue == null) {
            tableId.setItems(data);    
        }
        else {
            newValue = newValue.toUpperCase();
            for (Visitor visitors: tableId.getItems()){
                String filterByCountry = visitors.getCountry();
                if (filterByCountry.toUpperCase().contains(newValue)) {
                    filteredList.add(visitors); 
                }   
            }
            tableId.setItems(filteredList); 
        }
        
    }
     public void city_OnEditCommit(Event e){
        TableColumn.CellEditEvent<Visitor, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Visitor, String>) e;
        Visitor visitor = cellEditEvent.getRowValue();
        visitor.setCity(cellEditEvent.getNewValue()); 
    }
     
     public void handleDeleteButton(ActionEvent e) {
         if (!data.isEmpty()){
             System.out.println("Delete Button Clicked!");
             Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "Confirm", ButtonType.OK, ButtonType.CANCEL);
             Window owner = ((Node)e.getTarget()).getScene().getWindow();
             deleteAlert.setContentText("Are you sure you want to delete this?\n\nTHIS CANNOT BE UNDONE.");
             deleteAlert.initModality(Modality.APPLICATION_MODAL);
             deleteAlert.initOwner(owner);
             deleteAlert.showAndWait();
             if (deleteAlert.getResult() == ButtonType.OK) {
                 data.removeAll(tableId.getSelectionModel().getSelectedItems());
                 tableId.getSelectionModel().clearSelection(); 
             }
             else {
                 deleteAlert.close();
             }   
         }
     }
     
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        id.setCellValueFactory(new PropertyValueFactory<Visitor, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Visitor, String>("name"));
        city.setCellValueFactory(cellData -> cellData.getValue().CityProperty());
        //new PropertyValueFactory<Visitor, String>("city")
        country.setCellValueFactory(new PropertyValueFactory<Visitor, String>("country"));
        tableId.setItems(data);
        
        cityInput.textProperty().addListener(new ChangeListener(){
            public void changed(ObservableValue observable, Object oldValue, Object newValue ) {
                filterVisitorByCity((String) oldValue, (String) newValue);
            }   
        });
        
         countryInput.textProperty().addListener(new ChangeListener(){
            public void changed(ObservableValue observable, Object oldValue, Object newValue ) {
                filterVisitorByCountry((String) oldValue, (String) newValue);
            }   
        });
         
         tableId.setEditable(true);
         //id.setOnEditCommit(e -> id_OnEditCommit(e));
         //name.setOnEditCommit(e -> name_OnEditCommit(e));
         city.setOnEditCommit(e -> city_OnEditCommit(e));
         //country.setOnEditCommit(e -> country_OnEditCommit(e));
         city.setCellFactory(TextFieldTableCell.forTableColumn());
         
         tableId.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }  
    
   
    
}
