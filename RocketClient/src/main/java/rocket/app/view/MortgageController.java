package rocket.app.view;

import eNums.eAction;
import exceptions.RateException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	@FXML
	private TextField income;
	@FXML
	private TextField expenses;
	@FXML
	private TextField creditScore;
	@FXML
	private TextField houseCost;
	@FXML
	private ComboBox<String> term;
	@FXML
	private Label mortgageDetails;
	@FXML
	private Button calculatePayment;
	@FXML
	private Label interestRateLabel;
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event) throws RateException
	{
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		lq.setiCreditScore(Integer.parseInt(creditScore.getText()));
		lq.setiTerm(Integer.parseInt(term.getValue()));
		lq.setdAmount(Double.parseDouble(houseCost.getText()));
		lq.setdRate(RateBLL.getRate(lq.getiCreditScore()));
		lq.setdPayment(RateBLL.getPayment(lq.getdRate(), lq.getiTerm()*12, lq.getdAmount(), 0, false));
		a.setLoanRequest(lq);
		Object message = lq;
		mainApp.messageSend(message);
		String rate = "";
		rate += lq.getdRate();
		
		interestRateLabel.setText(rate);
		mortgageDetails.setText("Value to be displayed");
		
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		double payment = lRequest.getdPayment();
		double Income = Double.parseDouble(income.getText());
		double Expenses = Double.parseDouble(expenses.getText());
		double max = Income*28/100;
		if ((Income*36/100)-Expenses < max){
			max = (Income*36/100)-Expenses;
		}
		String message = "";
		if (max > payment){
			message += "You do not have enough moeny to afford this house, \n";
			message += "try finding a cheaper one.";
		}else{
			message += "Your monthly payment is: $";
			message += payment;
			message += ".";
		}
		mortgageDetails.setText(message);
		
	}
}