package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;
import rocketData.LoanRequest;

public class rate_test {
	
	//Interest Rate and Rate Exception

	@Test
	public void RateTest(){
		try {
			assertEquals(RateBLL.getRate(800),3.5,0.001);
		} catch (RateException e) {
			e.printStackTrace();
		}
		try {
			assertEquals(RateBLL.getRate(850), 3.50, 0.01);
		} catch (RateException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = RateException.class)
	public void RateExceptionTest() throws RateException {
			
		assertEquals(RateBLL.getRate(100), 0.00, 0.01);
		assertEquals(RateBLL.getRate(355), 0.00, 0.01);
	}
	
	//Loan Test 
	@Test
	public void IncomeCheckTest() {
		LoanRequest lq_1 = new LoanRequest();
		lq_1.setIncome(100000.00);
		lq_1.setdPayment(2000.00);
		lq_1.setExpenses(1500.00);
		assertEquals(RateBLL.IncomeCheck(lq_1), false);
		
		LoanRequest lq_2 = new LoanRequest();
		lq_2.setIncome(100000.00);
		lq_2.setdPayment(2000.00);
		lq_2.setExpenses(1500.00);
		assertEquals(RateBLL.IncomeCheck(lq_2), true);
	}
}