package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class HomeworkController implements Initializable {
	public CheckBox imageCheckBox;
	public CheckBox textCheckBox;
	public CheckBox codeCheckBox;
	public CheckBox printToFileCheckBox;
	public ComboBox<String> qualityBox;
	public RadioButton selectionButton;
	public RadioButton allButton;
	public RadioButton appletButton;
	
	public Button okButton;
	public Button cancelButton;
	public Button setupButton;
	public Button helpButton;
	
	public ToggleGroup radioGroup;
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		System.out.println("Initializing Homework Parent");
		
		ObservableList<String> qualityOptions =
				FXCollections.observableArrayList(
						"High", "Medium", "Low");
		
		qualityBox.setItems(qualityOptions);
		
	}
	
	public void handleImageCheckBoxAction(ActionEvent event){
		System.out.println("You clicked the Image checkbox!");
	}
	
	public void handleTextCheckBoxAction(ActionEvent event){
		System.out.println("You clicked the Text checkbox!");
	}
	
	public void handleCodeCheckBoxAction(ActionEvent event){
		System.out.println("You clicked the Code checkbox!");
	}
	
	public void handleSelectionRadioButtonAction(ActionEvent event){
		System.out.println("You clicked the Selection RadioButton!");
	}
	
	public void handleAllRadioButtonAction(ActionEvent event){
		System.out.println("You clicked the All RadioButton!");
	}
	
	public void handleAppletRadioButtonAction(ActionEvent event){
		System.out.println("You clicked the Applet RadioButton!");
	}
	
	public void handleQualityComboAction(ActionEvent event){
		System.out.println("You selected " + qualityBox.getValue() + " from the quality combo box.");
	}
	
	public void handleOkButtonAction(ActionEvent event){
		System.out.println("You clicked the Ok Button!");
	}
	
	public void handleCancelButtonAction(ActionEvent event){
		System.out.println("You clicked the Cancel Button!");
	}
	
	public void handleHelpButtonAction(ActionEvent event){
		System.out.println("You clicked the Help Button!");
	}
	
	public void handleSetupButtonAction(ActionEvent event){
		System.out.println("You clicked the Setup Button!");
	}
	
	public void handlePrintToFileCheckBoxAction(ActionEvent event){
		System.out.println("You clicked the Print to File CheckBox!");
	}
}
