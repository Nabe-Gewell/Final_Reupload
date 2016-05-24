package exceptions;

import rocketData.LoanRequest;
import rocketDomain.RateDomainModel;

public class RateException extends Exception {
	
	private RateDomainModel RateDomainModel = null;
	private LoanRequest lq = null;

	public RateException(RateDomainModel RateDomainModel) {
		this.RateDomainModel = RateDomainModel; 
	}
	public RateDomainModel getRateDomainModel() {
		return RateDomainModel;
	}	
}