package bank;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirstBank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char option = '\0';
		Scanner scanner = new Scanner(System.in);
		//do {
		System.out.println("Welcome, please enter your customerID: ");
		String cid = scanner.next();
		double balance = 0.0;
		String email = "dummyemail";
		String phoneNumber = "00000000";
		try {

            URL url = new URL("http://localhost:8080/balance?acctnum="+ cid );//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                extracted(conn);
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
            	if(output.contains("balance")) {
            		balance = Double.parseDouble(output.substring(13,output.length()-1));
            		//System.out.println(output.substring(13,output.length()-1));
            		
            	}else if(output.contains("email")) {
            		email = output.substring(12,output.length()-3);
            		//System.out.println(email);
            	}else if(output.contains("phoneNumber")) {
            		phoneNumber = output.substring(18,output.length()-4);
            		//System.out.println(phoneNumber);
            	}
            }
            conn.disconnect();
            Account newcustomer = new Account(cid, balance, email, phoneNumber);
    		newcustomer.showMenu(scanner);
    		
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
		
		//}while( option != 'E');
		//System.out.println("Thank you for banking with us!");
	}

	private static void extracted(HttpURLConnection conn) throws IOException {
		throw new RuntimeException("Failed : HTTP Error code : "
		        + conn.getResponseCode());
	}
}
