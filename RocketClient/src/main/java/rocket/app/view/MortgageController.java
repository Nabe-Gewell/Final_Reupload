package rocket.app.view;


import java.text.NumberFormat;

import org.springframework.format.number.CurrencyFormatter;


import eNums.eAction;
import exceptions.RateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketBase.RateDAL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	private static NumberFormat cf = NumberFormat.getCurrencyInstance();
	private static NumberFormat pf = NumberFormat.getPercentInstance();

	@FXML
	private TextField txtIncome;
	
	@FXML
	private TextField txtExpenses;

	@FXML
	private TextField txtCreditScore;
	
	@FXML
	private TextField txtHouseCost;
	
	@FXML
	private TextField txtDownPayment;

	ObservableList<String> termList = FXCollections.observableArrayList("15 Years", "30 Years") ;
	
	@FXML
	private ComboBox cmbTerm;

	@FXML
	private TextField txtMortgagePayment;

	@FXML
	private TextField txtRate;
	
	@FXML
	private Button btnCalcPayment;
	
	@FXML
	private Button btnExit;
	
	@FXML
	private Button btnClear;
	
	@FXML
	private void initialize() {
		cmbTerm.setItems(termList);
		cmbTerm.setValue(termList.get(1));
		
	}

	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();

		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()) - Double.parseDouble(txtDownPayment.getText()));
		lq.setIncome(Double.parseDouble(txtIncome.getText()));
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		try {
			lq.setdRate(RateBLL.getRate(Integer.parseInt(txtCreditScore.getText())));
		} catch (RateException e) {
			lq.setdRate(-1.0);
		}
		
		if(cmbTerm.getValue() == "15 Years"){
			lq.setiTerm(15);
		}
		else{
			lq.setiTerm(30);
		}
		a.setLoanRequest(lq);
	
		mainApp.messageSend(lq);
	}
	
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		
		RateBLL RateBLL = new RateBLL();
		
		if (lRequest.getdRate() == -1.0) {
			txtRate.setText("Sorry your credit score does not qualify.");
			txtMortgagePayment.setText("Sorry your credit score does not qualify.");	
		
		} else if(rocketBase.RateBLL.IncomeCheck(lRequest) == false){
			
			txtMortgagePayment.setText("House cost too high.");
			txtRate.setText("House cost too high.");
			
		} else {
			txtMortgagePayment.setText(cf.format(lRequest.getdPayment()));
			pf.setMaximumFractionDigits(3);
			txtRate.setText(pf.format(lRequest.getdRate()/100));
		}
		
		
	}
	
	@FXML
	public void btnExit(ActionEvent event)
	{
		System.exit(0);
		
	}
	
	@FXML
	public void btnClear(ActionEvent event)
	{
		txtIncome.setText("");
		txtExpenses.setText("");
		txtCreditScore.setText("");
		txtHouseCost.setText("");
		txtDownPayment.setText("");
		txtMortgagePayment.setText("");
		txtRate.setText("");
		
	}
}