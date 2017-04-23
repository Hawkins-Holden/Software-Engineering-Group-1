/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitortable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author anjan
 */
public class Visitor {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty city;
    private final SimpleStringProperty country; 
    

public Visitor(int id, 
        String name, String city, 
        String country) {
    
    this.id = new SimpleIntegerProperty(id);
    this.name = new SimpleStringProperty(name);
    this.city = new SimpleStringProperty(city);
    this.country = new SimpleStringProperty(country); 
    
    
}    

    public Integer getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getCity() {
        return city.get();
    }

    public String getCountry() {
        return country.get();
    }
    
    public void setCity(String city) {
        this.city.set(city);
    }
    
    public StringProperty CityProperty(){
        return city;
    }
    
}
