package db;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
	public PersonType personType = PersonType.Customer;
	public SimpleStringProperty firstName = new SimpleStringProperty();
	public SimpleStringProperty lastName = new SimpleStringProperty();
	public SimpleStringProperty address = new SimpleStringProperty();
	public SimpleStringProperty city = new SimpleStringProperty();
	public SimpleStringProperty state = new SimpleStringProperty();
	public SimpleStringProperty zip = new SimpleStringProperty();
	public SimpleBooleanProperty gender = new SimpleBooleanProperty();

	public String getFirstName(){
		return firstName.get();
	}
	
	public String getLastName(){
		return lastName.get();
	}
	
	public String getAddress(){
		return address.get();
	}
	
	public String getCity(){
		return city.get();
	}
	
	public String getState(){
		return state.get();
	}
	
	public String getZip(){
		return zip.get();
	}
	
	public String getGender(){
		if(gender.get()){
			return "Male";
		} else {
			return "Female";
		}
	}
}
