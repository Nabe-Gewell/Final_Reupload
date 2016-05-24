package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketData.LoanRequest;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException 
	{	
		ArrayList<RateDomainModel> rateList = _RateDAL.getAllRates();
		double Irate = -1.0;
		RateDomainModel RateDomainModel = null;
	
		for(RateDomainModel rate: rateList){
			if(rate.getiMinCreditScore() <= GivenCreditScore){
				Irate = rate.getdInterestRate();
				RateDomainModel = rate;
			}
		}
		
		if ((Irate == -1) || (RateDomainModel == null)){
			throw new RateException(RateDomainModel);
		}
		else{
			return Irate;
		}
		
	}
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r/12, n, p, f, t) * (-1.0);
	}

		public static boolean IncomeCheck(LoanRequest lq)
		{
			boolean ICheck = false;
			if((((lq.getIncome()/12) * .28) > lq.getdPayment()) && ((((lq.getIncome()/12) - lq.getExpenses()) * .36) > lq.getdPayment())){
				ICheck = true;
			}
			return ICheck ;					
		}
}