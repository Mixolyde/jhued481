package application;

import java.net.URL;
import java.text.DecimalFormat;
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
import javafx.scene.control.cell.PropertyValueFactory;

public class MortgageController implements Initializable {
	public TextField loanAmountField;
	public ComboBox<Double> interestBox;
	public ComboBox<Integer> yearBox;
	public Button calcButton;
	public TextField totalField;
	public TableView<MortgagePayment> paymentTable;
	public TableColumn<MortgagePayment, String> monthNoCol;
	public TableColumn<MortgagePayment, String> paymentCol;
	public TableColumn<MortgagePayment, String> principlePaidCol;
	public TableColumn<MortgagePayment, String> interestPaidCol;
	public TableColumn<MortgagePayment, String> totalInterestPaidCol;
	public TableColumn<MortgagePayment, String> remainingValueCol;
	
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
		
		monthNoCol.setCellValueFactory(
			new PropertyValueFactory<MortgagePayment,String>("monthNo")
		);
		
		paymentCol.setCellValueFactory(
			new PropertyValueFactory<MortgagePayment,String>("payment")
		);
		
		principlePaidCol.setCellValueFactory(
			new PropertyValueFactory<MortgagePayment,String>("principlePaid")
		);
		
		interestPaidCol.setCellValueFactory(
			new PropertyValueFactory<MortgagePayment,String>("interestPaid")
		);
		
		totalInterestPaidCol.setCellValueFactory(
			new PropertyValueFactory<MortgagePayment,String>("totalInterestPaid")
		);
		
		remainingValueCol.setCellValueFactory(
			new PropertyValueFactory<MortgagePayment,String>("remainingValue")
		);
		
		payments = FXCollections.observableArrayList();
		paymentTable.setItems(payments);
		
	}
	
	public void handleCalculateAction(ActionEvent event){
		System.out.println("You clicked the Calculate Button!");
		double interestMonthly = (interestBox.getValue() / 12) / 100;
		System.out.println("Monthly interest percentage: " + interestMonthly);
		
		int numberMonths = 12 * yearBox.getValue();
		int index = 1;
		
		payments.clear();
		
		double totalPaid = 0;
		double totalInterestPaid = 0;
		
		try {
			String loanAmountString = loanAmountField.getText();
			double remainingBalance = Double.parseDouble(loanAmountString);
			System.out.println("Initial principal: " + remainingBalance);
			double monthlyPayment = remainingBalance * interestMonthly * 
					Math.pow(1 + interestMonthly, (double) numberMonths) /
					(Math.pow(1 + interestMonthly, (double) numberMonths) - 1);
			DecimalFormat df = new DecimalFormat("#.00");
			monthlyPayment = Double.parseDouble(df.format(monthlyPayment));
			System.out.println("Monthly Payment: " + monthlyPayment);
			
			for (index = 1; index < numberMonths; index++){
				double interestPaid = Double.parseDouble(df.format(remainingBalance * interestMonthly));
				double principlePaid = monthlyPayment - interestPaid;
				remainingBalance = remainingBalance - principlePaid;
				totalInterestPaid += interestPaid;
				totalPaid += monthlyPayment;
				payments.add(new MortgagePayment("Month " + index, monthlyPayment,
						principlePaid, interestPaid,
						totalInterestPaid, remainingBalance));
				
			}
			
			double interestPaid = remainingBalance * interestMonthly;
			double principlePaid = monthlyPayment - interestPaid;
			remainingBalance = remainingBalance - monthlyPayment;
			totalInterestPaid += interestPaid;
			totalPaid += monthlyPayment;
			
			payments.add(new MortgagePayment("Month " + index, interestPaid + principlePaid,
					principlePaid, interestPaid,
					totalInterestPaid, 0));
			
			
			totalField.setText(df.format(totalPaid));
			
		} catch(Exception e){
			System.out.println("Exception thrown while calculating: " + e.getMessage());
			e.printStackTrace();
			totalField.setText("");
			payments.clear();
			
		}
		
		
	}


}
