package application;

import java.net.URL;
import java.util.ResourceBundle;

import db.DerbyManager;
import db.PersonType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RetailSystemController implements Initializable {
	public MenuBar menuBar;
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
		
		Main.onCloseAction();
	}
	
	public void handleCustomerAddAction(ActionEvent event){
		System.out.println("Handling Customer Add Action");

		showPersonDialog(PersonType.Customer);
	}
	
	public void handleCustomerListAction(ActionEvent event){
		System.out.println("Handling Customer List Action");
		
	}
	
	public void handleEmployeeAddAction(ActionEvent event){
		System.out.println("Handling Employee Add Action");
		
		showPersonDialog(PersonType.Employee);
	}
	
	public void handleEmployeeListAction(ActionEvent event){
		System.out.println("Handling Employee List Action");
		
	}
	
	public void handleMerchandiseAddAction(ActionEvent event){
		System.out.println("Handling Merchandise Add Action");
		
		showMerchandiseDialog();		
	}
	
	public void handleMerchandiseListAction(ActionEvent event){
		System.out.println("Handling Merchandise List Action");
		
	}
	
	private void showPersonDialog(PersonType personType){
		try{
	        Stage modal_dialog = new Stage(StageStyle.DECORATED);
	        modal_dialog.initModality(Modality.WINDOW_MODAL);
	        modal_dialog.initOwner(menuBar.getScene().getWindow());
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewPerson.fxml"));
	        Parent personView = (Parent)loader.load();
	        NewPersonController controller = (NewPersonController) loader.getController();
	        Scene scene = new Scene(personView);
	        modal_dialog.setScene(scene);
	        controller.setPersonType(personType);
	        modal_dialog.setTitle("Add New " + personType.toString());
	        modal_dialog.show();
        
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void showMerchandiseDialog(){
		try{
	        Stage modal_dialog = new Stage(StageStyle.DECORATED);
	        modal_dialog.initModality(Modality.WINDOW_MODAL);
	        modal_dialog.initOwner(menuBar.getScene().getWindow());
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewMerchandise.fxml"));
	        Parent merchView = (Parent)loader.load();
	        Scene scene = new Scene(merchView);
	        modal_dialog.setScene(scene);
	        modal_dialog.setTitle("Add New Merchandise");
	        modal_dialog.show();
        
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
