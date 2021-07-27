package bank;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class FirstBankTest {
	
	@Test
	void testcase1() {
	
		String userinput = "1234567\nA\nE";
		System.setIn(new ByteArrayInputStream(userinput.getBytes()));
		
		FirstBank.main(null);
		
	}
	
	@Test
	void testcase2() {

		String userinput = "1234567\nD\n200\nA\nE";
		System.setIn(new ByteArrayInputStream(userinput.getBytes()));
		
		FirstBank.main(null);
		
	}
}
