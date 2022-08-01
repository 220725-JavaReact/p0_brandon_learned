package com.revature.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BusinessLogicTest {

	@Test
	void test_Proper_Lower_Case_Input() {
		String outputName = "Brandon";
		String inputName = BusinessLogic.verifyName("brandon");
		
		Assertions.assertEquals(outputName, inputName);
	}
	
	@Test
	void test_Sarcasm_Case_Input() {
		String outputName = "Brandon";
		String inputName = BusinessLogic.verifyName("bRaNdOn");
		
		Assertions.assertEquals(outputName, inputName);
	}
	
	@Test
	void test_Invalid_With_Spaces_In_Middle() {
		String outputName = "";
		String inputName = BusinessLogic.verifyName("Bran don");
		
		Assertions.assertEquals(outputName, inputName);
	}
	
	@Test
	void test_Invalid_With_Spaces_At_Beginning() {
		String outputName = "";
		String inputName = BusinessLogic.verifyName(" Brandon");
		
		Assertions.assertEquals(outputName, inputName);
	}
	
	@Test
	void test_Invalid_With_Spaces_At_End() {
		String outputName = "";
		String inputName = BusinessLogic.verifyName("Brandon ");
		
		Assertions.assertEquals(outputName, inputName);
	}
	
	@Test
	void test_Invalid_No_Input_Given() {
		String outputName = "";
		String inputName = BusinessLogic.verifyName("");
		
		Assertions.assertEquals(outputName, inputName);
	}
    
	@Test
	void test_Valid_Email_Dot_Com() {
		String outputName = "brandon@email.com";
		String inputName = BusinessLogic.verifyEmail("brandon@email.com");
		
		Assertions.assertEquals(outputName, inputName);
	}
	
	@Test
	void test_Valid_Email_Dot_Net() {
		String outputName = "brandon@email.net";
		String inputName = BusinessLogic.verifyEmail("brandon@email.net");
		
		Assertions.assertEquals(outputName, inputName);
	}
	
	@Test
	void test_Invalid_Email_No_At() {
		String outputName = "";
		String inputName = BusinessLogic.verifyEmail("brandonemail.com");
		
		Assertions.assertEquals(outputName, inputName);
	}
	
	@Test
	void test_Invalid_Email_Too_Many_At() {
		String outputName = "";
		String inputName = BusinessLogic.verifyEmail("brandon@@email.com");
		
		Assertions.assertEquals(outputName, inputName);
	}
	
	@Test
	void test_Invalid_Email_At_At_Beginning() {
		String outputName = "";
		String inputName = BusinessLogic.verifyEmail("@brandonemail.com");
		
		Assertions.assertEquals(outputName, inputName);
	}
	
	@Test
	void test_Invalid_Email_At_At_End() {
		String outputName = "";
		String inputName = BusinessLogic.verifyEmail("brandonemail@.com");
		
		Assertions.assertEquals(outputName, inputName);
	}
	
	@Test
	void test_Invalid_Email_No_Suffix() {
		String outputName = "";
		String inputName = BusinessLogic.verifyEmail("brandon@email");
		
		Assertions.assertEquals(outputName, inputName);
	}
	
	@Test
	void test_Invalid_Email_Invalid_Suffix() {
		String outputName = "";
		String inputName = BusinessLogic.verifyEmail("brandon@email");
		
		Assertions.assertEquals(outputName, inputName);
	}
	
	@Test
	void test_Invalid_Email_Includes_Space_In_Main() {
		String outputName = "";
		String inputName = BusinessLogic.verifyEmail("bran don@email.com");
		
		Assertions.assertEquals(outputName, inputName);
	}
	
	@Test
	void test_Invalid_Email_Includes_Space_In_Suffix() {
		String outputName = "";
		String inputName = BusinessLogic.verifyEmail("brandon@email.c om");
		
		Assertions.assertEquals(outputName, inputName);
	}
	 
}
