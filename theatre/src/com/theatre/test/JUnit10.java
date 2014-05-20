package com.theatre.test;

import static org.junit.Assert.*;



import org.junit.Test;

import com.theatre.actions.TheatreDAO;
import com.theatre.entities.Booking;


public class JUnit10 {

	@Test
	public void test() {
		/* Execution without Exception implies a successful test case, otherwise a failed case */
		try{
			  TheatreDAO dao = new TheatreDAO();
			  
			  Booking booking = dao.getTicket("1002");
			  
			  if(booking == null || booking.getId() != 1002)
				 throw new Exception("Requested Booking Not found");
			  
		}catch(Exception e){
			fail("Failed to Retrieve Ticket. \n Error Message :" + e);
		}	
		
	}

}
