/**
 * 
 */
package JUnitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Model.DisplayMenuAndErrorMsg;

/**
 * @author Arion
 *
 */
public class DisplayMenuAndErrorMsgTest {
	
	int input, listSize;

	/**
	 * Test inputValidation_Main method for acceptable input.
	 */
	@Test
	public void testInputValidation_Main1() {
		input = 3;
		assertTrue(DisplayMenuAndErrorMsg.inputValidation_Main(input));
	}
	
	/**
	 * Test inputValidation_Main method for negative input.
	 */
	@Test
	public void testInputValidation_Main2() {
		input = -3;
		assertFalse(DisplayMenuAndErrorMsg.inputValidation_Main(input));
	}

	/**
	 * Test inputValidation_Main method for out of bounds positive input.
	 */
	@Test
	public void testInputValidation_Main3() {
		input = 10;
		assertFalse(DisplayMenuAndErrorMsg.inputValidation_Main(input));
	}
	
	/**
	 * Test inputValidation_Sub method for acceptable input.
	 */
	@Test
	public void testInputValidation_Sub1() {
		input = 2;
		assertTrue(DisplayMenuAndErrorMsg.inputValidation_Sub(input));
	}
	
	/**
	 * Test inputValidation_Sub method for negative input.
	 */
	@Test
	public void testInputValidation_Sub2() {
		input = -7;
		assertFalse(DisplayMenuAndErrorMsg.inputValidation_Sub(input));
	}
	
	/**
	 * Test inputValidation_Sub method for out of bounds positive input.
	 */
	@Test
	public void testInputValidation_Sub3() {
		input = 9;
		assertFalse(DisplayMenuAndErrorMsg.inputValidation_Sub(input));
	}
	
	/**
	 * Test inputValidation_Sub method for acceptable input and listSize.
	 */
	@Test
	public void testInputValidation_Sub4() {
		input = 2;
		listSize = 6;
		assertTrue(DisplayMenuAndErrorMsg.inputValidation_Sub(input, listSize));
	}
	
	/**
	 * Test inputValidation_Sub method for negative input and listSize.
	 */
	@Test
	public void testInputValidation_Sub5() {
		input = -2;
		listSize = -6;
		assertFalse(DisplayMenuAndErrorMsg.inputValidation_Sub(input, listSize));
	}
	
	/**
	 * Test inputValidation_Sub method for input > listSize.
	 */
	@Test
	public void testInputValidation_Sub6() {
		input = 6;
		listSize = 4;
		assertFalse(DisplayMenuAndErrorMsg.inputValidation_Sub(input, listSize));
	}

}
