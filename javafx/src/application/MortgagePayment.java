package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class MortgagePayment {
	private final SimpleStringProperty monthNo;
	private final SimpleDoubleProperty payment;
	private final SimpleDoubleProperty principlePaid;
	private final SimpleDoubleProperty interestPaid;
	private final SimpleDoubleProperty totalInterestPaid;
	private final SimpleDoubleProperty remainingValue;
	
	public MortgagePayment(String monthNo, double payment, double principlePaid,
			double interestPaid, double totalInterestPaid, double remainingValue){
		this.monthNo = new SimpleStringProperty(monthNo);
		this.payment = new SimpleDoubleProperty(payment);
		this.principlePaid = new SimpleDoubleProperty(principlePaid);
		this.interestPaid = new SimpleDoubleProperty(interestPaid);
		this.totalInterestPaid = new SimpleDoubleProperty(totalInterestPaid);
		this.remainingValue = new SimpleDoubleProperty(remainingValue);
	}
	
	public String getMonthNo(){
		return monthNo.get();
	}
	
	public double getPayment(){
		return payment.get();
	}
	
	public double getPrinciplePaid(){
		return principlePaid.get();
	}
	
	public double getInterestPaid(){
		return interestPaid.get();
	}
	
	public double getTotalInterestPaid(){
		return totalInterestPaid.get();
	}
	
	public double getRemainingValue(){
		return remainingValue.get();
	}

}
