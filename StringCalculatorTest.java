package practice;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;


public class StringCalculatorTest {
	
	@Test
	public void emptyStringShouldReturnNull(){
		StringCalculator testString = new StringCalculator();
		int result = testString.Add("");
		assertEquals(0,result);
		
	}
	
	@Test 
	public void oneValueStringShouldReturnSameValue() {
		StringCalculator testString = new StringCalculator();
		int result = testString.Add("3"); 
		assertEquals(3,result);
	}
	
	@Test 
	public void twoStringValueShouldReturnSum() {
		StringCalculator testString = new StringCalculator();
		int result = testString.Add("3,4"); 
		assertEquals(7,result);
	}
	
	@Test 
	public void threeStringValueShouldReturnSum() {
		StringCalculator testString = new StringCalculator();
		int result = testString.Add("3,4,5"); 
		assertEquals(12,result);
	}

	@Test 
	public void sixStringValueShouldReturnSum() {
		StringCalculator testString = new StringCalculator();
		int result = testString.Add("1,2,3,4,5,6,7,8,9"); 
		assertEquals(45,result);
	}

	
	
}
