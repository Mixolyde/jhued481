package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MortgageController implements Initializable {
	public TextField loanAmountField;
	public ComboBox<Double> interestBox;
	public ComboBox<Integer> yearBox;
	public Button calcButton;
	public TextField totalField;
	public TableView<MortgagePayment> paymentTable;
	public TableColumn<MortgagePayment, String> monthNoCol;
	public TableColumn<MortgagePayment, Double> paymentCol;
	public TableColumn<MortgagePayment, Double> principlePaidCol;
	public TableColumn<MortgagePayment, Double> interestPaidCol;
	public TableColumn<MortgagePayment, Double> totalInterestPaidCol;
	public TableColumn<MortgagePayment, Double> remainingValueCol;
	
	private ObservableList<MortgagePayment> payments;
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		System.out.println("Initializing Mortgage Contoller");
		
		ObservableList<Double> interestOptions =
				FXCollections.observableArrayList(
						3.25, 3.5, 3.75, 4.0, 4.25, 4.5, 4.75, 5.0);

		ObservableList<Integer> yearOptions =
				FXCollections.observableArrayList(
						5, 10, 15, 30);
		
		interestBox.setItems(interestOptions);
		interestBox.getSelectionModel().selectFirst();
		yearBox.setItems(yearOptions);
		yearBox.getSelectionModel().selectFirst();
		
		//TODO set cell value factories
		
		
		
		
		payments = FXCollections.observableArrayList(new MortgagePayment("Month 1", 1, 2, 3, 4, 5));
		paymentTable.setItems(payments);
		
	}
	
	public void handleCalculateAction(ActionEvent event){
		System.out.println("You clicked the Calculate Button!");
	}


}
