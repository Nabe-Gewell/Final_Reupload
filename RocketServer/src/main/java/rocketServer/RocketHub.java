package rocketServer;

import java.io.IOException;

import exceptions.IncomeException;
import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
			
			try {
				lq.setdRate(rocketBase.RateBLL.getRate(lq.getiCreditScore()));
			} catch (RateException e) {
				sendToAll(e);
				System.out.println("Invalid credit score, rate not found based on credit score please try again.");
			}
			
			lq.setdPayment(rocketBase.RateBLL.getPayment(lq.getdRate()/100, lq.getiTerm()* 12, lq.getdAmount()-lq.getiDownPayment(), 0.0, false));
			
			if (rocketBase.RateBLL.IncomeCheck(lq) == false){
				try {
					throw new IncomeException(lq);
				} catch (IncomeException e) {
					sendToAll(e);
					System.out.println("Insufficient income, income check failed please try again.");
				}
			}
			
			sendToAll(lq);
		}
	}
}