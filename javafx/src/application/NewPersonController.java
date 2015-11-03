package application;

import java.net.URL;
import java.util.ResourceBundle;

import db.DerbyManager;
import db.Person;
import db.PersonType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class NewPersonController implements Initializable {
	public Label errorLabel;
	public Button okButton;
	public Button cancelButton;
	public TextField firstNameField;
	public TextField lastNameField;
	public TextField addressField;
	public TextField cityField;
	public ComboBox<String> stateBox;
	public TextField zipField;
	public RadioButton maleButton;
	public RadioButton femaleButton;
	
	final ToggleGroup genderGroup = new ToggleGroup();
	
	private PersonType personType;

	@Override
	public void initialize(URL location, ResourceBundle resources){
		System.out.println("Initializing New Person Contoller");
		
		maleButton.setToggleGroup(genderGroup);
		maleButton.setSelected(true);
		femaleButton.setToggleGroup(genderGroup);
		
		ObservableList<String> stateOptions =
				FXCollections.observableArrayList(
						"AK", "AL", "AR");
		
		stateBox.setItems(stateOptions);
		stateBox.getSelectionModel().selectFirst();
		
		errorLabel.setText("");
	}
	
	public void setPersonType(PersonType personType){
		System.out.println("Setting New Person Contoller PersonType: " + personType.toString());
		this.personType = personType;
		
	}
	
	public void handleOkButtonAction(ActionEvent event){
		System.out.println("You clicked the Ok Button!");
		
		Person person = new Person();
		person.personType = this.personType;
		person.firstName.set(firstNameField.getText().trim());
		person.lastName.set(lastNameField.getText().trim());
		person.address.set(addressField.getText().trim());
		person.city.set(cityField.getText().trim());
		person.state.set(stateBox.getSelectionModel().getSelectedItem());
		person.zip.set(zipField.getText().trim());
		
		if(maleButton.isSelected()){
			person.gender.set(true);
		} else {
			person.gender.set(false);
		}
		
		try{
			DerbyManager.getInstance().insertPerson(person);
			closeDialog();
		} catch (Exception e){
			errorLabel.setText(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void handleCancelButtonAction(ActionEvent event){
		System.out.println("You clicked the Cancel Button!");
	    closeDialog();
	}
	
	private void closeDialog(){
	    // get a handle to the stage
	    Stage stage = (Stage) cancelButton.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
}
