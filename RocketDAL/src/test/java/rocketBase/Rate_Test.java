package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	@Test
	public void RateTest() {		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		System.out.println ("Rates size: " + rates.size());
		assert(rates.size() > 0);
		assertEquals(rates.get(1).getdInterestRate(), 7.00,  0.01);
		assertEquals(rates.get(2).getdInterestRate(), 6.00,  0.01);
		assertEquals(rates.get(3).getdInterestRate(), 9.00, 0.01);
		assertEquals(rates.get(4).getdInterestRate(), 5.00,  0.01);			
	}
}