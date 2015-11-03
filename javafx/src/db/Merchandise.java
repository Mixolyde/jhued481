package db;

import java.text.DecimalFormat;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class Merchandise {
	public SimpleStringProperty name = new SimpleStringProperty();
	public SimpleFloatProperty price = new SimpleFloatProperty();
	public SimpleStringProperty description = new SimpleStringProperty();
	
	DecimalFormat df = new DecimalFormat("#.00");

	public String getName(){
		return name.get();
	}
	
	public String getPrice(){
		return "$" + df.format(price.get());
	}
	
	public String getDescription(){
		return description.get();
	}
}
