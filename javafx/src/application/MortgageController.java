package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MortgageController implements Initializable {
	public TextField loanAmountField;
	public ComboBox<Double> interestBox;
	public ComboBox<Integer> yearBox;
	public Button calcButton;
	public TextField totalField;
	public TableView paymentTable;
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		System.out.println("Initializing Homework Parent");
		
		ObservableList<Double> interestOptions =
				FXCollections.observableArrayList(
						3.25, 3.5, 3.75, 4.0, 4.25, 4.5, 4.75, 5.0);

		ObservableList<Integer> yearOptions =
				FXCollections.observableArrayList(
						5, 10, 15, 30);
		
		interestBox.setItems(interestOptions);
		yearBox.setItems(yearOptions);

	}

}
