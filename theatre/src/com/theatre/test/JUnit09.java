package com.theatre.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.theatre.actions.TheatreDAO;


public class JUnit09 {

	@Test
	public void test() {
		/* Execution without Exception implies a successful test case, otherwise a failed case */
		try{
			  TheatreDAO dao = new TheatreDAO();
			  boolean cancelled = dao.cancelTicket("1003");
			  
			  assertEquals(cancelled, true);
			  
		}catch(Exception e){
			fail("Failed to cancel Ticket. \n Error Message :" + e);
		}
	}

}
