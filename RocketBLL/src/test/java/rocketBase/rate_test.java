package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;
import rocketData.LoanRequest;

public class rate_test {

	@Test
	public void RateTest(){
		try {
			assertEquals(RateBLL.getRate(745), 4.00, 0.01);
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

	@Test
	public void PaymentTest() {
		assertEquals(RateBLL.getPayment(0.04, 360, 300000, 0.0, false), 1432.25, 0.01);
		assertEquals(RateBLL.getPayment(0.03, 360, 1000000, 0.0, false),4216.04, 0.01);
	}
	
	@Test
	public void IncomeCheckTest() {
		LoanRequest lq1 = new LoanRequest();
		lq1.setIncome(80000.00);
		lq1.setdPayment(2000.00);
		lq1.setExpenses(1500.00);
		assertEquals(RateBLL.IncomeCheck(lq1), false);
		
		LoanRequest lq2 = new LoanRequest();
		lq2.setIncome(100000.00);
		lq2.setdPayment(2000.00);
		lq2.setExpenses(1500.00);
		assertEquals(RateBLL.IncomeCheck(lq2), true);
		
		LoanRequest lq3 = new LoanRequest();
		lq3.setIncome(100000.00);
		lq3.setdPayment(2000.00);
		lq3.setExpenses(4000.00);
		assertEquals(RateBLL.IncomeCheck(lq3), false);
	}

}