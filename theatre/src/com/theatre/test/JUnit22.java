package com.theatre.test;

import static org.junit.Assert.*;

import org.junit.Test;
import com.theatre.utills.Mail;

public class JUnit22 {

	@Test
	public void test() {
		/* Execution without Exception implies a successful test case, otherwise a failed case */
		try{

			Mail gmail = new Mail();
			gmail.to( "theatre.javaproject@gmail.com");
			gmail.subject("Hello.");
			gmail.message( "Hi, \n Its a sample mail to check. \n Thanks,\nTeja.");
			gmail.send();

		}catch(Exception e){
			fail("Failed to Send mail. \n Error Message :" + e);
		}
	}

}
