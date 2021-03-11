/**
 * Program Name: StudentLoanApp.java
 * Purpose: Store students informations and calculates student loans based on time period, interests and the type of the loan.
 * Author: Fernando Rodrigues Cardoso 0909573
 * Date: Jul 31, 2019
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Locale;
import java.text.NumberFormat;

@SuppressWarnings("serial")
public class StudentLoanApp extends JFrame
{
	//Number of the student displayed on GUI
	public int stdntNumber = 1;
	
	//Students Objects ArrayList
	ArrayList<Student> studentArrayList = new ArrayList<Student>();
	
	JLabel studentNumber;
	boolean isCalcPaymentOpen = false;
	boolean newStudentObject = true;
	
	JTextField studentIDTextField;
	JTextField firstNameTextField;
	JTextField middleNameTextField;
	JTextField surnameTextField;
	JTextField streetNumberTextField;
	JTextField streetNameTextField;
	JTextField cityTextField;
	JComboBox<String>  provinceComboBox;
	JTextField postalTextField;
	JTextField cslTextField;
	JTextField oslTextField;
	
	public StudentLoanApp() 
	{
		//Setting Header of the JFrame
		super("Fernando Cardoso - 0909573");
		
		final int WIDTH = 740;
		final int HEIGHT = 620;
		
		//JFrame basic structure
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(15, 2, 5, 7));
		
		//Creating the components of the JFrame, including, JLabels, JPanels, JButtons and a JComboBox
		JLabel myName = new JLabel("This is Fernando's Student Loan Calculator");
		myName.setFont(myName.getFont().deriveFont(16f));
		
		JPanel nextPreviousStudent = new JPanel();
		studentNumber = new JLabel("Student Number: " + stdntNumber);
		studentNumber.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel blank = new JLabel("Enter Your Personal Informations");
		blank.setFont(blank.getFont().deriveFont(15f));
		
		JButton previousStudent = new JButton("Next Student");
		previousStudent.addActionListener(new buttonIncreaseListener());
		
		JButton nextStudent = new JButton("Previous Student");
		nextStudent.addActionListener(new buttonDecreaseListener());
		
		JLabel studentID = new JLabel("Student ID: ");
		studentIDTextField = new JTextField();		
		
		JLabel firstName = new JLabel("First Name: ");
		firstNameTextField = new JTextField();
		
		JLabel middleName = new JLabel("Middle Name: ");
		middleNameTextField = new JTextField();
		
		JLabel surname = new JLabel("Surname: ");
		surnameTextField = new JTextField();
		
		JLabel streetNumber = new JLabel("Street Number: ");
		streetNumberTextField = new JTextField();
		
		JLabel streetName = new JLabel("Street Name: ");
		streetNameTextField = new JTextField();
		
		JLabel city = new JLabel("City: ");
		cityTextField = new JTextField();
		
		JLabel province = new JLabel("Province: ");
		String x[] = { "Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland and Labrador", 
					  "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario",
					  "Prince Edward Island", "Quebec", "Saskatchewan", "Yukon"};
		provinceComboBox = new JComboBox<String>(x);
        provinceComboBox.setSelectedIndex(-1);
        
		JLabel postalCode = new JLabel("Postal Code: ");
		postalTextField = new JTextField();
		
		JLabel cslAmount = new JLabel("Canadian Student Loan Amount");
		cslTextField = new JTextField();
		
		JLabel oslAmount = new JLabel("Ontario Student Loan Amount");	
		oslTextField = new JTextField();
		
		JButton calcPayment = new JButton("Continue");
		calcPayment.addActionListener(new ContinueButttonPressed());
		
		JButton storeButton = new JButton("Store");
		storeButton.addActionListener(new StoreButtonPressed());
		
		JButton clearPersonal = new JButton("Clear");
		clearPersonal.addActionListener(new ClearButtonPressed());
		
		JPanel clearAndStore = new JPanel();
		clearAndStore.add(storeButton);
		clearAndStore.add(clearPersonal);
		
		JLabel continueInstructions = new JLabel("'Continue' store these informations and opens calculation window");
		continueInstructions.setFont(continueInstructions.getFont().deriveFont(11f));
		
		JLabel clearInstructions = new JLabel("Only store above informations  /  Clear all text fields");
		clearInstructions.setFont(clearInstructions.getFont().deriveFont(11f));
		clearInstructions.setHorizontalAlignment(JTextField.CENTER);
		
		//Adding all the created components above to the JFrame.
		add(myName);
		add(studentNumber);
		add(blank);
		nextPreviousStudent.add(nextStudent);
		nextPreviousStudent.add(previousStudent);
		add(nextPreviousStudent);
		add(studentID);
		add(studentIDTextField);
		add(firstName);
		add(firstNameTextField);
		add(middleName);
		add(middleNameTextField);
		add(surname);
		add(surnameTextField);
		add(streetNumber);
		add(streetNumberTextField);
		add(streetName);
		add(streetNameTextField);
		add(city);
		add(cityTextField);
		add(province);
		add(provinceComboBox);
		add(postalCode);
		add(postalTextField);
		add(cslAmount);
		add(cslTextField);
		add(oslAmount);
		add(oslTextField);
		add(clearAndStore);
		add(calcPayment);			
		add(clearInstructions);
		add(continueInstructions);
		
		//Setting the JFrame visibilty to true
		setVisible(true);
	}
	
//Listener to "Next Student" Button.
private class buttonIncreaseListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			
			//Only go to the next student informations if all the informations for the current student were given.
			if (stdntNumber <= studentArrayList.size()) {
				stdntNumber++;
				studentNumber.setText("Student Number: " + stdntNumber);
				
				//If it is a newly created student, clear all fields text.
				if (stdntNumber - 1 == studentArrayList.size()) {
					studentIDTextField.setText("");
					firstNameTextField.setText("");
					middleNameTextField.setText("");
					surnameTextField.setText("");
					streetNameTextField.setText("");
					streetNumberTextField.setText("");
					cityTextField.setText("");
					provinceComboBox.setSelectedItem(null);
					postalTextField.setText("");
					cslTextField.setText("");
					oslTextField.setText("");
					studentArrayList.get(0);
				}

				//Else if it is not a newly created student, retrieve all the informations for the student stored on the Student Object ArrayList
				else {
					studentIDTextField.setText(studentArrayList.get(stdntNumber - 1).getStudentID());
					firstNameTextField.setText(studentArrayList.get(stdntNumber - 1).getFirstName());
					middleNameTextField.setText(studentArrayList.get(stdntNumber - 1).getMiddleName());
					surnameTextField.setText(studentArrayList.get(stdntNumber - 1).getSurname());
					streetNameTextField.setText(studentArrayList.get(stdntNumber - 1).getStreetName());
					streetNumberTextField.setText(studentArrayList.get(stdntNumber - 1).getStreetNumber());
					cityTextField.setText(studentArrayList.get(stdntNumber - 1).getCity());
					provinceComboBox.setSelectedItem(studentArrayList.get(stdntNumber - 1).getProvince());
					postalTextField.setText(studentArrayList.get(stdntNumber - 1).getPostalCode());
					cslTextField.setText(String.valueOf(studentArrayList.get(stdntNumber - 1).getCslLoanAmount()));
					oslTextField.setText(String.valueOf(studentArrayList.get(stdntNumber - 1).getOslLoanAmount()));
				}
			}
			//Warning the user that the not all informations, for the curretn student, were provided
			else {
				JOptionPane.showMessageDialog(null, "Please store all informations first!", "Form Imcomplete", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
//Listener to "Previous Student" Button.
private class buttonDecreaseListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		//Check if the user is trying to go to a 0 or negative number student. If not, retrieve the informations of the previous student on the Student Object ArrayList
		if (stdntNumber > 1) {
			stdntNumber--;
			studentNumber.setText("Student Number: " + stdntNumber);
			
			studentIDTextField.setText(studentArrayList.get(stdntNumber - 1).getStudentID());
			firstNameTextField.setText(studentArrayList.get(stdntNumber - 1).getFirstName());
			middleNameTextField.setText(studentArrayList.get(stdntNumber - 1).getMiddleName());
			surnameTextField.setText(studentArrayList.get(stdntNumber - 1).getSurname());
			streetNameTextField.setText(studentArrayList.get(stdntNumber - 1).getStreetName());
			streetNumberTextField.setText(studentArrayList.get(stdntNumber - 1).getStreetNumber());
			cityTextField.setText(studentArrayList.get(stdntNumber - 1).getCity());
			if (studentArrayList.get(stdntNumber-1).getProvince().length() == 0) {
				provinceComboBox.setSelectedItem(null);
			}
			else {
				provinceComboBox.setSelectedItem(studentArrayList.get(stdntNumber - 1).getProvince());
			}
			provinceComboBox.setSelectedItem(studentArrayList.get(stdntNumber - 1).getProvince());
			postalTextField.setText(studentArrayList.get(stdntNumber - 1).getPostalCode());
			cslTextField.setText(String.valueOf(studentArrayList.get(stdntNumber - 1).getCslLoanAmount()));
			oslTextField.setText(String.valueOf(studentArrayList.get(stdntNumber - 1).getOslLoanAmount()));		
		}
	}
}	

//Creates and opens a new JFrame for calculations
private class ContinueButttonPressed implements ActionListener {
	
	JLabel heading1;
	JLabel blank1;
	JLabel crrntPrimeInterest;
	JTextField crrntPrimeInterestTextField;
	JLabel amortizationPeriod;
	JTextField amortizationPeriodTextField;
	JLabel heading2;
	JLabel blank2;
	JLabel monthlyCSLpayment;
	JTextField monthlyCSLTextField;
	JLabel monthlyOSLpayment;
	JTextField monthlyOSLTextField;
	JLabel totalMonthlyPayment;
	JTextField totalMonthlyPaymenTextField;
	JLabel totalRepaidLoan;
	JTextField totalRepaidLoanTextField;
	JLabel totalBorrowed;
	JTextField totalBorrowedTextField;
	JLabel totalLoanInterest;
	JTextField totalLoanInterestTextField;
	
	//If "Continue" Button is pressed, creates the JFrame for calculations and opens it.
	@Override
	public void actionPerformed(ActionEvent event) {
					
		//Checking if all input fields were correctly filled
		if (validateInput()) {
			//Store Personal informations on newly created object
			if (stdntNumber - 1 == studentArrayList.size()) {
				studentArrayList.add(new Student(studentIDTextField.getText(),"","","","","","","","", "", 0, 0));
			}
	
			//Store the current student informations on the Student Object ArrayList
			studentArrayList.get(stdntNumber-1).setFirstName(firstNameTextField.getText());
			studentArrayList.get(stdntNumber-1).setMiddleName(middleNameTextField.getText());
			studentArrayList.get(stdntNumber-1).setSurname(surnameTextField.getText());
			studentArrayList.get(stdntNumber-1).setStreetNumber(streetNumberTextField.getText());
			studentArrayList.get(stdntNumber-1).setStreetName(streetNameTextField.getText());
			studentArrayList.get(stdntNumber-1).setCity(cityTextField.getText());
			studentArrayList.get(stdntNumber-1).setProvince(String.valueOf(provinceComboBox.getSelectedItem()));
			studentArrayList.get(stdntNumber-1).setPostalCode(postalTextField.getText());
			studentArrayList.get(stdntNumber-1).setCslLoanAmount(Double.parseDouble(cslTextField.getText()));
			studentArrayList.get(stdntNumber-1).setOslLoanAmount(Double.parseDouble(oslTextField.getText()));
			
			JFrame calcJFrame = new JFrame("Fernando Cardoso's Student Loan Application");		
			
			final int WIDTH2 = 450;
			final int HEIGHT2 = 470;
			
			//JFrame basic structure
			calcJFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			calcJFrame.setSize(WIDTH2, HEIGHT2);
			calcJFrame.setLocationRelativeTo(null);
			calcJFrame.setLayout(new GridLayout(11, 2, 5, 5));
			
			
			//JFrame Components
			heading1 = new JLabel("Enter Loan Informations");
			heading1.setFont(heading1.getFont().deriveFont(16f));
			blank1 = new JLabel("");
			
			crrntPrimeInterest = new JLabel("Current prime interest rate:");
			crrntPrimeInterestTextField = new JTextField();
			
			amortizationPeriod = new JLabel("Amortization period in months");
			amortizationPeriodTextField = new JTextField();
			
			heading2 = new JLabel("Results");
			heading2.setFont(heading2.getFont().deriveFont(16f));
			blank2 = new JLabel("");
			
			monthlyCSLpayment = new JLabel("Monthly CSL payment amount");
			monthlyCSLTextField = new JTextField();
			monthlyCSLTextField.setEditable(false);
			
			monthlyOSLpayment = new JLabel("Monthly OSL payment amount");
			monthlyOSLTextField = new JTextField();
			monthlyOSLTextField.setEditable(false);
			
			totalMonthlyPayment = new JLabel("Total Monthly payment amount");
			totalMonthlyPaymenTextField = new JTextField();
			totalMonthlyPaymenTextField.setEditable(false);
			
			totalRepaidLoan = new JLabel("Total amount of your repaind loan");
			totalRepaidLoanTextField = new JTextField();
			totalRepaidLoanTextField.setEditable(false);
			
			totalBorrowed = new JLabel("Total amount you borrowed");
			totalBorrowedTextField = new JTextField();
			totalBorrowedTextField.setEditable(false);
			
			totalLoanInterest = new JLabel("Total interests on your loans");
			totalLoanInterestTextField = new JTextField();
			totalLoanInterestTextField.setEditable(false);
			
			JButton calcPayment = new JButton("Calculate payments");
			calcPayment.addActionListener(new calcPaymentListener());
			JButton clearFields = new JButton("Clear Fields");
			clearFields.addActionListener(new clearAllFields());
			
			//Adding components to JFrame
			calcJFrame.add(heading1);
			calcJFrame.add(blank1);
			calcJFrame.add(crrntPrimeInterest);
			calcJFrame.add(crrntPrimeInterestTextField);
			calcJFrame.add(amortizationPeriod);
			calcJFrame.add(amortizationPeriodTextField);
			calcJFrame.add(heading2);
			calcJFrame.add(blank2);
			calcJFrame.add(monthlyCSLpayment);
			calcJFrame.add(monthlyCSLTextField);
			calcJFrame.add(monthlyOSLpayment);
			calcJFrame.add(monthlyOSLTextField);
			calcJFrame.add(totalMonthlyPayment);
			calcJFrame.add(totalMonthlyPaymenTextField);
			calcJFrame.add(totalRepaidLoan);
			calcJFrame.add(totalRepaidLoanTextField);
			calcJFrame.add(totalBorrowed);
			calcJFrame.add(totalBorrowedTextField);
			calcJFrame.add(totalLoanInterest);
			calcJFrame.add(totalLoanInterestTextField);
			calcJFrame.add(calcPayment);
			calcJFrame.add(clearFields);
			
			//Setting JFrame visibility to true
			calcJFrame.setVisible(true);
		}			
	}
				
		//Listener to the "Calculate" Button
		private class calcPaymentListener implements ActionListener, F_R_C_LoanPayable {
			
			//If "Calculate" Button was pressed, checks if inputs are correct
			@Override
			public void actionPerformed(ActionEvent event) {							
						
				if (crrntPrimeInterestTextField.getText().matches(".*[a-zA-Z]+.*")) {
					JOptionPane.showMessageDialog(null, "Can not use non numeric symbols!", "Empty Field", JOptionPane.ERROR_MESSAGE);
					crrntPrimeInterestTextField.setText("");
				}
				
				else if (crrntPrimeInterestTextField.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Current Prime Interest Text Field is empty!", "Empty Field", JOptionPane.ERROR_MESSAGE);
				}
				
				else if (crrntPrimeInterestTextField.getText().matches(".*[a-zA-Z]+.*") && crrntPrimeInterestTextField.getText().length() == 0) {
					double fractional = Math.round((Double.parseDouble(crrntPrimeInterestTextField.getText()) % 1) * 100.0) / 100.0;
					if (fractional % 0.25 != 0) {
						JOptionPane.showMessageDialog(null, "Prime Interest Rate must be a quarter percent increment!", "Empty Field", JOptionPane.ERROR_MESSAGE);
						crrntPrimeInterestTextField.setText("");
					}
				}
					
				else if (amortizationPeriodTextField.getText().matches(".*[a-zA-Z]+.*")) {
					JOptionPane.showMessageDialog(null, "Can not use non numeric symbols!", "Empty Field", JOptionPane.ERROR_MESSAGE);
					amortizationPeriodTextField.setText("");
				}
				
				else if (amortizationPeriodTextField.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Amortization Period Text Field is empty!", "Empty Field", JOptionPane.ERROR_MESSAGE);
				}
				
				//If input was accepted, try and catch used to check for negative values:
				else {
				
				try
					{
						if (crrntPrimeInterestTextField.getText().length() != 0) {
							isNegative(Double.parseDouble(crrntPrimeInterestTextField.getText()));
						}			
					}
				
				catch (F_R_C_NegativeValueException negativeException)
					{
						JOptionPane.showMessageDialog(null, "Current Prime Interest Rate input is negative! Program will convert this input to a positive one!", "Negative Value", JOptionPane.WARNING_MESSAGE);
						crrntPrimeInterestTextField.setText(String.valueOf(Double.parseDouble(crrntPrimeInterestTextField.getText()) * -1));
					}
				
				try
				{
					if (amortizationPeriodTextField.getText().length() != 0) {
						isNegative(Double.parseDouble(amortizationPeriodTextField.getText()));	
					}						
				} 
				
				catch (F_R_C_NegativeValueException negativeException)
				{
					JOptionPane.showMessageDialog(null, "Amortization period input is negative! Program will convert this input to a positive one!", "Negative Value", JOptionPane.WARNING_MESSAGE);
					amortizationPeriodTextField.setText(String.valueOf(Integer.parseInt(amortizationPeriodTextField.getText()) * -1));
				}
				
				//Parsing Fields from String to numeric data types to begin doing calculations.
				
				double cslAmount = Double.parseDouble(cslTextField.getText());
				double oslAmount = Double.parseDouble(oslTextField.getText());
				double primeInterest = Double.parseDouble(crrntPrimeInterestTextField.getText());
				int amortizationPeriod = Integer.parseInt(amortizationPeriodTextField.getText());
				
				double totalCSLAmount = calculateLoanPayment(cslAmount, primeInterest + 2.5, amortizationPeriod);
				double totalOSLAmount = calculateLoanPayment(oslAmount, primeInterest + 1.0, amortizationPeriod);
				
				double totalMonthlyPayment = totalCSLAmount + totalOSLAmount;
				double totalRepaindLoan = (totalCSLAmount + totalOSLAmount) * amortizationPeriod;
				double totalBorrowed = cslAmount + oslAmount;
				double totalLoanInterest = totalRepaindLoan - totalBorrowed;
				
				//Setting print format for Canada currency type
		        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
			
		        //Showing all calculations and alligning to the right of the text fields
				monthlyCSLTextField.setText(currencyFormat.format(totalCSLAmount));
				monthlyCSLTextField.setHorizontalAlignment(JTextField.RIGHT);
				monthlyOSLTextField.setText(currencyFormat.format(totalOSLAmount));
				monthlyOSLTextField.setHorizontalAlignment(JTextField.RIGHT);
				totalMonthlyPaymenTextField.setText(currencyFormat.format(totalMonthlyPayment));
				totalMonthlyPaymenTextField.setHorizontalAlignment(JTextField.RIGHT);
				totalRepaidLoanTextField.setText(currencyFormat.format(totalRepaindLoan));
				totalRepaidLoanTextField.setHorizontalAlignment(JTextField.RIGHT);
				totalBorrowedTextField.setText(currencyFormat.format(totalBorrowed));
				totalBorrowedTextField.setHorizontalAlignment(JTextField.RIGHT);
				totalLoanInterestTextField.setText(currencyFormat.format(Math.round(totalLoanInterest * 100) / 100.0));
				totalLoanInterestTextField.setHorizontalAlignment(JTextField.RIGHT);			
				}
			}
				
			//Method to convert annual prime interest rate to monthly rate and calculate monthly loan price 
			public double calculateLoanPayment(double oslOrCsl, double primeInterestRate, int amortizationMonths) {

					primeInterestRate *= ANNUAL_RATE_TO_MONTHLY_RATE;
					
					double totalPaymentAmount = oslOrCsl * primeInterestRate * Math.pow((1 + primeInterestRate), amortizationMonths) / (Math.pow(1 + primeInterestRate, amortizationMonths) - 1);
					return Math.round(totalPaymentAmount * 100.0) / 100.0;
				}
				
			}
		
		//Listener for "Clear" Button
		private class clearAllFields implements ActionListener {
			
			//If "Clear" Button of calculation window is pressed, set all text fields to empty
			@Override
			public void actionPerformed(ActionEvent event) {
				crrntPrimeInterestTextField.setText(null);
				amortizationPeriodTextField.setText(null);
				monthlyCSLTextField.setText(null);
				monthlyOSLTextField.setText(null);
				totalMonthlyPaymenTextField.setText(null);
				totalRepaidLoanTextField.setText(null);
				totalBorrowedTextField.setText(null);
				totalLoanInterestTextField.setText(null);
				
				//Set cursor to the prime interest rate field
				crrntPrimeInterestTextField.requestFocusInWindow();
			}
		}
	}

//Listener for "Store" Button
private class StoreButtonPressed implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		//Store the current student informations on the Student Object ArrayList
		if (validateInput()) {
			//Store Personal informations on newly created object
			if (stdntNumber - 1 == studentArrayList.size()) {
				studentArrayList.add(new Student(studentIDTextField.getText(),"","","","","","","","", "", 0, 0));
			}
			//Store the current student informations on the Student Object ArrayList
			studentArrayList.get(stdntNumber-1).setFirstName(firstNameTextField.getText());
			studentArrayList.get(stdntNumber-1).setMiddleName(middleNameTextField.getText());
			studentArrayList.get(stdntNumber-1).setSurname(surnameTextField.getText());
			studentArrayList.get(stdntNumber-1).setStreetNumber(streetNumberTextField.getText());
			studentArrayList.get(stdntNumber-1).setStreetName(streetNameTextField.getText());
			studentArrayList.get(stdntNumber-1).setCity(cityTextField.getText());
			studentArrayList.get(stdntNumber-1).setProvince(String.valueOf(provinceComboBox.getSelectedItem()));
			studentArrayList.get(stdntNumber-1).setPostalCode(postalTextField.getText());
			studentArrayList.get(stdntNumber-1).setCslLoanAmount(Double.parseDouble(cslTextField.getText()));
			studentArrayList.get(stdntNumber-1).setOslLoanAmount(Double.parseDouble(oslTextField.getText()));
			
			//Opening a pane that says that all informations were successfully stored on the Student Objects ArrayList
			JOptionPane.showMessageDialog(null,"Student informations stored successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		}	
	}
}

//Listener for the "Clear" Button on the main JFrame
private class ClearButtonPressed implements ActionListener {
	
	//Set all text fields of the main JFrame to empty
	@Override
	public void actionPerformed(ActionEvent event) {
		studentIDTextField.setText("");
		firstNameTextField.setText("");
		middleNameTextField.setText("");
		surnameTextField.setText("");
		streetNameTextField.setText("");
		streetNumberTextField.setText("");
		cityTextField.setText("");
		provinceComboBox.setSelectedItem(null);
		postalTextField.setText("");
		cslTextField.setText("");
		oslTextField.setText("");
	}
}

//Method to check if all text fields were filled correctly
public boolean validateInput() {
	if (studentIDTextField.getText().matches(".*[a-zA-Z]+.*")) {
		JOptionPane.showMessageDialog(null, "Can not use non numeric symbols!", "Empty Field", JOptionPane.ERROR_MESSAGE);
		studentIDTextField.setText("");
	}
	
	else if (studentIDTextField.getText().length() == 0) {
		JOptionPane.showMessageDialog(null, "Student ID Text Field is empty!", "Empty Field", JOptionPane.ERROR_MESSAGE);
	}
	
	else if (studentIDTextField.getText().length() != 0 && studentIDTextField.getText().length() != 7) {
		JOptionPane.showMessageDialog(null,"Student ID must have 7 digits!", "Empty Field", JOptionPane.ERROR_MESSAGE);
	}
	
	else if (firstNameTextField.getText().length() == 0) {
		JOptionPane.showMessageDialog(null, "First Name Text Field is empty!", "Empty Field", JOptionPane.ERROR_MESSAGE);
	}
	
	else if (middleNameTextField.getText().length() == 0) {
		JOptionPane.showMessageDialog(null, "Middle Name Text Field is empty!", "Empty Field", JOptionPane.ERROR_MESSAGE);
	}
	
	else if (surnameTextField.getText().length() == 0) {
		JOptionPane.showMessageDialog(null, "Surname Text Field is empty!", "Empty Field", JOptionPane.ERROR_MESSAGE);
	}
	
	else if (streetNumberTextField.getText().matches(".*[a-zA-Z]+.*")) {
		JOptionPane.showMessageDialog(null, "Can not use non numeric symbols!", "Empty Field", JOptionPane.ERROR_MESSAGE);
		streetNumberTextField.setText("");
	}
	
	else if (studentIDTextField.getText().length() == 0) {
		JOptionPane.showMessageDialog(null, "Street Number Text Field is Empty!", "Empty Field", JOptionPane.ERROR_MESSAGE);
	}
	
	else if (streetNameTextField.getText().length() == 0) {
		JOptionPane.showMessageDialog(null, "Street Name Text Field is Empty!", "Empty Field", JOptionPane.ERROR_MESSAGE);
	}
	
	else if (String.valueOf(provinceComboBox.getSelectedItem()) == "null") {
		JOptionPane.showMessageDialog(null, "Province ComboBox is Empty!", "Empty Field", JOptionPane.ERROR_MESSAGE);
	}
	
	else if (postalTextField.getText().length() == 0) {
		JOptionPane.showMessageDialog(null, "Postal Code Text Field is Empty!", "Empty Field", JOptionPane.ERROR_MESSAGE);
	}
	
	else if (cslTextField.getText().matches(".*[a-zA-Z]+.*")) {
		JOptionPane.showMessageDialog(null, "Can not use non numeric symbols!", "Empty Field", JOptionPane.ERROR_MESSAGE);
		cslTextField.setText("");
	}
	
	else if (cslTextField.getText().length() == 0) {
		JOptionPane.showMessageDialog(null, "CSL Amount Text Field is Empty!", "Empty Field", JOptionPane.ERROR_MESSAGE);
	}
	
	else if (oslTextField.getText().matches(".*[a-zA-Z]+.*")) {
		JOptionPane.showMessageDialog(null, "Can not use non numeric symbols!", "Empty Field", JOptionPane.ERROR_MESSAGE);
		oslTextField.setText("");
	}
	
	else if (oslTextField.getText().length() == 0) {
		JOptionPane.showMessageDialog(null, "OSL Amount Text Field is Empty!", "Empty Field", JOptionPane.ERROR_MESSAGE);
	}
	
	else {
		//Checking for negative value inputs, and throwing custom exception if found
		try {
			if (cslTextField.getText().length() != 0) {
				isNegative(Double.parseDouble(cslTextField.getText()));
			}		
		} 
		catch (F_R_C_NegativeValueException negativeException) {
			JOptionPane.showMessageDialog(null, "CSL Amount input is negative! Program will convert this input to a positive one!", "Negative Value", JOptionPane.WARNING_MESSAGE);
			cslTextField.setText(String.valueOf(Double.parseDouble(cslTextField.getText()) * -1));
		}
	
	
		try {
			if (oslTextField.getText().length() != 0) {
				isNegative(Double.parseDouble(oslTextField.getText()));
			}		
		} 
		catch (F_R_C_NegativeValueException negativeException) {
			JOptionPane.showMessageDialog(null, "OSL Amount input is negative! Program will convert this input to a positive one!", "Negative Value", JOptionPane.WARNING_MESSAGE);
			oslTextField.setText(String.valueOf(Double.parseDouble(oslTextField.getText()) * -1));
		}		
		return true;
	}	
	
	return false;
}

//Method to check if input is negative and throw an exception if true
public void isNegative(double number) throws F_R_C_NegativeValueException {
	if (number < 0) {
		throw new F_R_C_NegativeValueException();
	}
}

//Main
	public static void main(String[] args)  
	{
		new StudentLoanApp();
	}
}