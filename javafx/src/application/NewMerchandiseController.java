package application;

import java.net.URL;
import java.util.ResourceBundle;

import db.DerbyManager;
import db.Merchandise;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewMerchandiseController implements Initializable {
	public Label errorLabel;
	public Button okButton;
	public Button cancelButton;
	public TextField nameField;
	public TextField priceField;
	public TextArea descriptionArea;

	@Override
	public void initialize(URL location, ResourceBundle resources){
		System.out.println("Initializing New Merchandise Contoller");
		
		errorLabel.setText("");
	}
	
	public void handleOkButtonAction(ActionEvent event){
		System.out.println("You clicked the Ok Button!");
		
		try{
			Merchandise merchandise = new Merchandise();
			merchandise.name.set(nameField.getText().trim());
			float price = Float.parseFloat(priceField.getText().trim());
			if (price >= 0){
				merchandise.price.set(price);
			} else {
				throw new IllegalArgumentException("Price must be greater than or equal to $0.00");
			}
			merchandise.description.set(descriptionArea.getText().trim());

			DerbyManager.getInstance().insertMerchandise(merchandise);
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
