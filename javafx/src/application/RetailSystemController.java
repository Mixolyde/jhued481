package application;

import java.net.URL;
import java.util.ResourceBundle;

import db.DerbyManager;
import db.Merchandise;
import db.Person;
import db.PersonType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RetailSystemController implements Initializable {
	public BorderPane borderPane;
	public MenuBar menuBar;
	public MenuItem closeMenuItem;
	public MenuItem customerAddMenuItem;
	public MenuItem customerListMenuItem;
	public MenuItem employeeAddMenuItem;
	public MenuItem employeeListMenuItem;
	public MenuItem merchandiseAddMenuItem;
	public MenuItem merchandiseListMenuItem;
	
	//tables
	public TableView<Person> personTable;
	public TableColumn<Person, String> firstNameCol;
	public TableColumn<Person, String> lastNameCol;
	public TableColumn<Person, String> addressCol;
	public TableColumn<Person, String> cityCol;
	public TableColumn<Person, String> stateCol;
	public TableColumn<Person, String> zipCol;
	public TableColumn<Person, String> genderCol;
	
	public TableView<Merchandise> merchandiseTable;
	public TableColumn<Merchandise, String> nameCol;
	public TableColumn<Merchandise, String> priceCol;
	public TableColumn<Merchandise, String> descriptionCol;
	
	private ObservableList<Person> persons;
	private ObservableList<Merchandise> merchandises;
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		System.out.println("Initializing Retail System Contoller");
		firstNameCol = createPersonColumn("First Name", "firstName");
		lastNameCol = createPersonColumn("Last Name", "lastName");
		addressCol = createPersonColumn("Addres", "address");
		cityCol = createPersonColumn("City", "city");
		stateCol = createPersonColumn("State", "state");
		zipCol = createPersonColumn("Zip Code", "zip");
		genderCol = createPersonColumn("Gender", "gender");
		
		personTable = new TableView<Person>();
		personTable.getColumns().add(firstNameCol);
		personTable.getColumns().add(lastNameCol);
		personTable.getColumns().add(addressCol);
		personTable.getColumns().add(cityCol);
		personTable.getColumns().add(stateCol);
		personTable.getColumns().add(zipCol);
		personTable.getColumns().add(genderCol);
		
		persons = FXCollections.observableArrayList();
		personTable.setItems(persons);
		
		nameCol = createMerchandiseColumn("Name", "name");
		priceCol = createMerchandiseColumn("Price", "price");
		descriptionCol = createMerchandiseColumn("Description", "description");
		
		merchandiseTable = new TableView<Merchandise>();
		merchandiseTable.getColumns().add(nameCol);
		merchandiseTable.getColumns().add(priceCol);
		merchandiseTable.getColumns().add(descriptionCol);
		
		merchandises = FXCollections.observableArrayList();
		merchandiseTable.setItems(merchandises);
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
		
		try {
			persons.clear();
			persons.addAll(DerbyManager.getInstance().getPersons(PersonType.Customer));
		} catch (Exception e){
			e.printStackTrace();
		}
		
		borderPane.setCenter(personTable);
		
	}
	
	public void handleEmployeeAddAction(ActionEvent event){
		System.out.println("Handling Employee Add Action");
		
		showPersonDialog(PersonType.Employee);
	}
	
	public void handleEmployeeListAction(ActionEvent event){
		System.out.println("Handling Employee List Action");
		
		try {
			persons.clear();
			persons.addAll(DerbyManager.getInstance().getPersons(PersonType.Employee));
		} catch (Exception e){
			e.printStackTrace();
		}
		
		borderPane.setCenter(personTable);
	}
	
	public void handleMerchandiseAddAction(ActionEvent event){
		System.out.println("Handling Merchandise Add Action");
		
		showMerchandiseDialog();		
	}
	
	public void handleMerchandiseListAction(ActionEvent event){
		System.out.println("Handling Merchandise List Action");
		
		try {
			merchandises.clear();
			merchandises.addAll(DerbyManager.getInstance().getMerchandises());
		} catch (Exception e){
			e.printStackTrace();
		}
		borderPane.setCenter(merchandiseTable);
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
	
	private TableColumn<Person, String> createPersonColumn(String text, String property){
		TableColumn<Person, String> tableCol = new TableColumn<Person, String>();
		tableCol.setText(text);
		tableCol.setEditable(false);
		tableCol.setCellValueFactory(
			new PropertyValueFactory<Person,String>(property)
		);
		
		return tableCol;
	}
	
	private TableColumn<Merchandise, String> createMerchandiseColumn(String text, String property){
		TableColumn<Merchandise, String> tableCol = new TableColumn<Merchandise, String>();
		tableCol.setText(text);
		tableCol.setEditable(false);
		tableCol.setCellValueFactory(
			new PropertyValueFactory<Merchandise,String>(property)
		);
		
		return tableCol;
	}

}
