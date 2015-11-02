package application;

import java.net.URL;
import java.util.ResourceBundle;

import db.PersonType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class NewPersonController implements Initializable {
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
						"Alabama", "Alaska", "Arkansas");
		
		stateBox.setItems(stateOptions);
		stateBox.getSelectionModel().selectFirst();
	}
	
	public void setPersonType(PersonType personType){
		System.out.println("Setting New Person Contoller PersonType: " + personType.toString());
		this.personType = personType;
		
	}
	
	public void handleOkButtonAction(ActionEvent event){
		System.out.println("You clicked the Ok Button!");
	}
	
	public void handleCancelButtonAction(ActionEvent event){
		System.out.println("You clicked the Cancel Button!");
	}
}
