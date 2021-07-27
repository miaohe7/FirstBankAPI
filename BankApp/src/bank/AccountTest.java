package bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccountTest {

	@Test
	void test1() {
		Account account = new Account("1234567", 38.25, "email@email.com", "2345678");
		assertEquals(38.25,account.getBalance());
	}
	
	@Test
	void test2() {
		Account account = new Account("1234567", 38.25, "email@email.com", "2345678");
		account.withdraw(5.00);
		assertEquals(33.25,account.getBalance());
	}

}
