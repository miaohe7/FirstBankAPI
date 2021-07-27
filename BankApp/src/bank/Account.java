package bank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Account {
	//Class Variables
	String accountNumber;
	double balance;
	double previousTransaction;
	String customeremail;
	String phonenumber;
	
	//Class constructor
	Account(String cnumber, double cbalance, String cemail, String cphone) {
		accountNumber = cnumber;
		balance = cbalance;
		customeremail = cemail;
		phonenumber = cphone;
	}
	
	//Function for getting balance
	double getBalance() {
		return balance;
	}
	//Function for email update
	void updateemail(String nemail) {
		this.customeremail = nemail;
	}
	
	//Function for phone number update
	void updatephone(String nphone) {
		this.phonenumber = nphone;
	}
	
	//Function for Depositing money
	void deposit(double amount) throws IOException {
		if (amount != 0) {
			balance = balance + amount;
			previousTransaction = amount;
		}else {
			System.out.println("Please enter an amount not zero.");
		}
		System.out.println("New Balance: $" + String.format( "%.2f", balance));	
	}
	
	
	//Function for Withdrawing money
	void withdraw(double amount) {
		if (amount != 0) {
			if (balance >= amount) {
			balance = balance - amount;
			previousTransaction = -amount;
			}else {
				System.out.println("Insufficient fund");
			}
		}else {
			System.out.println("Please enter an amount not zero.");
		}
		
		System.out.println("New Balance: $" + String.format( "%.2f", balance));
	}
	
	//Function showing the previous transaction
	void getPreviousTransaction() {
		if (previousTransaction > 0) {
			System.out.println("Deposited: " + previousTransaction);
		} else if (previousTransaction < 0) {
			System.out.println("Withdrawn: " + Math.abs(previousTransaction));
		} else {
			System.out.println("No transaction occurred");
		}
	}
	
	//Function calculating interest of current funds after a number of years
	void calculateInterest(int years) {
		double interestRate = .0185;
		double newBalance = (balance * interestRate * years) + balance;
		System.out.println("The current interest rate is " + (100 * interestRate) + "%");
		System.out.println("After " + years + " years, you balance will be: " + newBalance);
	}
	
	//Function post the new status to API
	void poststatus() throws IOException {
		final String POST_PARAMS = "{\n" + "\"accountNumber\":"+ accountNumber +",\r\n" +
	            "    \"balance\":"+ balance +",\r\n" +
	            "    \"email\": \" "+ customeremail +"\",\r\n" +
	            "    \"phoneNumber\":\" "+ phonenumber + "\"" + "\n}";
	        //System.out.println(POST_PARAMS);
	        URL obj = new URL("http://localhost:8080/balance?acctnum="+ accountNumber);
	        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	        postConnection.setRequestMethod("POST");
	        postConnection.setRequestProperty("accountNumber", accountNumber);
	        postConnection.addRequestProperty("balance", String.valueOf(balance));
	        postConnection.setRequestProperty("email", customeremail);
	        postConnection.setRequestProperty("phonenumber", phonenumber);
	        postConnection.setRequestProperty("Content-Type", "application/json");


	        postConnection.setDoOutput(true);
	        OutputStream os = postConnection.getOutputStream();
	        os.write(POST_PARAMS.getBytes());
	        os.flush();
	        os.close();


	        int responseCode = postConnection.getResponseCode();
	        //System.out.println("POST Response Code :  " + responseCode);
	        //System.out.println("POST Response Message : " + postConnection.getResponseMessage());

	        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                postConnection.getInputStream()));
	            String inputLine;
	            StringBuffer response = new StringBuffer();

	            while ((inputLine = in .readLine()) != null) {
	                response.append(inputLine);
	            } in .close();

	            // print result
	            System.out.println(response.toString());
	        } else {
	            System.out.println("Account update unsuccessful, please check your internet settings.");
	        }
	}
	
	//Function showing the main menu
	void showMenu(Scanner scanner) throws IOException {
		char option = '\0';
		//Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome, Your ID is: " + accountNumber);
		System.out.println("email: " + customeremail);
		System.out.println("phonenumber: " + phonenumber);
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println();
		System.out.println("A. Check your balance");
		System.out.println("B. Update your email");
		System.out.println("C. Update your phoneNumber");
		System.out.println("D. Make a deposit");
		System.out.println("W. Make a withdrawal");
		System.out.println("V. View previous transaction");
		System.out.println("I. Calculate interest");
		System.out.println("E. Exit");
		
		do {
			System.out.println();
			System.out.println("Enter an option: ");
			char option1 = scanner.next().charAt(0);
			option = Character.toUpperCase(option1);
			System.out.println();
			
			switch(option) {
			//Case 'A' allows the user to check their account balance
			case 'A':
				System.out.println("====================================");
				System.out.println("Balance = $" + String.format( "%.2f", balance));
				System.out.println("====================================");
				System.out.println();
				break;
				
			//Case 'B' allows user to check their email address
			case 'B':
				System.out.println("Enter a new email: ");
				String nemail = scanner.next();
				updateemail(nemail);
				System.out.println("====================================");
				System.out.println("Your Updated email is " + customeremail);
				System.out.println("====================================");
				System.out.println();
				break;
				
			//Case 'C' allows user to check their phone number
			case 'C':
				System.out.println("Enter a new phonenumber: ");
				String nphone = scanner.next();
				updatephone(nphone);
				System.out.println("====================================");
				System.out.println("Your Updated phonenumber is " + phonenumber);
				System.out.println("====================================");
				System.out.println();
				break;
				//Case 'D' allows the user to deposit money into their account using the 'deposit' function
			case 'D':
				System.out.println("Enter an amount to deposit: ");
				double amount = scanner.nextDouble();
				deposit(amount);
				System.out.println();
				break;
			//Case 'W' allows the user to withdraw money from their account using the 'withdraw' function
			case 'W':
				System.out.println("Enter an amount to withdraw: ");
				double amount2 = scanner.nextDouble();
				withdraw(amount2);
				System.out.println();
				break;
			//Case 'V' allows the user to view their most recent transaction using the 'getPreviousTransaction' function
			case 'V':
				System.out.println("====================================");
				getPreviousTransaction();
				System.out.println("====================================");
				System.out.println();
				break;
			//Case 'I' calculates the accrued interest on the account after a number of years specified by the user
			case 'I':
				System.out.println("Enter how many years of accrued interest: ");
				int years = scanner.nextInt();
				calculateInterest(years);
				break;
			//Case 'E' exits the user from their account
			case 'E':
				System.out.println("====================================");
				poststatus();
				System.out.println("====================================");
				break;
			//The default case let's the user know that they entered an invalid character and how to enter a valid one
			default:
				System.out.println("Error: invalid option. Please enter A, B, C, D, W, V, I or E to access services.");
				break;
			}
		} while(option != 'E');
		System.out.println("Thank you for banking with us!");
		System.out.println("====================================");
		System.out.println();
	}
	
}