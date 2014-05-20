package com.theatre.test;

import static org.junit.Assert.*;


import java.util.List;

import org.junit.Test;

import com.theatre.actions.TheatreDAO;
import com.theatre.entities.Booking;

public class JUnit17 {

	@Test
	public void test() {
		/* Execution without Exception implies a successful test case, otherwise a failed case */
		try{
			  TheatreDAO dao = new TheatreDAO();
			  List<Booking> bookings = dao.getBookingsWithPhoneNo("8080808080");
			  
			  if(bookings.size() <= 0 )
				  throw new Exception("No Bookings found with given details.");
			  

			  
		}catch(Exception e){
			fail("Failed to retrieve bookings. \n Error Message :" + e);
		}
	}

}
