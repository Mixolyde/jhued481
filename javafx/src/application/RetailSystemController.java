package application;

import java.net.URL;
import java.util.ResourceBundle;

import db.DerbyManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class RetailSystemController implements Initializable {
	public MenuItem closeMenuItem;
	public MenuItem customerAddMenuItem;
	public MenuItem customerListMenuItem;
	public MenuItem employeeAddMenuItem;
	public MenuItem employeeListMenuItem;
	public MenuItem merchandiseAddMenuItem;
	public MenuItem merchandiseListMenuItem;
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		System.out.println("Initializing Retail System Contoller");
		
	}
	
	public void handleCloseAction(ActionEvent event){
		System.out.println("Closing Retail System Contoller");
		
		System.out.println("Printing database dump.");
		try {
			DerbyManager.getInstance().printData();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		System.exit(0);
	}
	
	public void handleCustomerAddAction(ActionEvent event){
		System.out.println("Handling Customer Add Action");
		
	}
	
	public void handleCustomerListAction(ActionEvent event){
		System.out.println("Handling Customer List Action");
		
	}
	
	public void handleEmployeeAddAction(ActionEvent event){
		System.out.println("Handling Employee Add Action");
		
	}
	
	public void handleEmployeeListAction(ActionEvent event){
		System.out.println("Handling Employee List Action");
		
	}
	
	public void handleMerchandiseAddAction(ActionEvent event){
		System.out.println("Handling Merchandise Add Action");
		
	}
	
	public void handleMerchandiseListAction(ActionEvent event){
		System.out.println("Handling Merchandise List Action");
		
	}

}
