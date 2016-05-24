package exceptions;

import rocketData.LoanRequest;
import rocketDomain.RateDomainModel;

public class IncomeException extends Exception {

	private LoanRequest lq = null;

	public IncomeException(LoanRequest lq) {
		this.lq = lq;
	
	}

	public LoanRequest getLoanRequest() {
		return lq;
	}
	
	
	
}