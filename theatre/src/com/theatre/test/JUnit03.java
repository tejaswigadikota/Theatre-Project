package com.theatre.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.theatre.actions.TheatreDAO;
import com.theatre.entities.Booking;

public class JUnit03 {

	//Capacity
	
	@Test
	public void test() throws Exception {
		TheatreDAO dao = new TheatreDAO();
	
		Booking booking1 = dao.getTicket("1001");
		Booking booking2 = dao.getBookingsWithPhoneNo("8080808080").get(0);

		assertEquals( booking1.toString(),booking2.toString());
	
	}

}
