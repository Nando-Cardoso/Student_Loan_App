/**
 * Program Name: FRC_LoanPayable.java
 * Purpose: Interface that converts annual prime interest rate to monthly rate, and declares abstract method to calculate loan monthly payment.
 * Coder: Fernando Rodrigues Cardoso 0909573
 * Date: Jul 24, 2019
 */


public interface F_R_C_LoanPayable {

	double ANNUAL_RATE_TO_MONTHLY_RATE = 1.0/1200.0;
	
	double calculateLoanPayment(double oslOrCsl, double primeInterestRate, int amortizationMonths);
 
}


//end class