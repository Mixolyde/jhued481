package application;

import java.text.DecimalFormat;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class MortgagePayment {
	private final SimpleStringProperty monthNo;
	private final SimpleDoubleProperty payment;
	private final SimpleDoubleProperty principlePaid;
	private final SimpleDoubleProperty interestPaid;
	private final SimpleDoubleProperty totalInterestPaid;
	private final SimpleDoubleProperty remainingValue;
	
	DecimalFormat df = new DecimalFormat("#.00");
	
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
	
	public String getPayment(){
		return df.format(payment.get());
	}
	
	public String getPrinciplePaid(){
		return df.format(principlePaid.get());
	}
	
	public String getInterestPaid(){
		return df.format(interestPaid.get());
	}
	
	public String getTotalInterestPaid(){
		return df.format(totalInterestPaid.get());
	}
	
	public String getRemainingValue(){
		return df.format(remainingValue.get());
	}

}
