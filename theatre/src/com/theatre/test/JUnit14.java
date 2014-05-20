package com.theatre.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.theatre.actions.TheatreDAO;
import com.theatre.entities.Seat;

public class JUnit14 {

	@Test
	public void test() {
		/* Execution without Exception implies a successful test case, otherwise a failed case */
		try{
			  TheatreDAO dao = new TheatreDAO();
			  
			   List<Seat> seats = dao.getAvailableSeats("101", "1");
			   
			   if( seats.size() <= 0 )
				   throw new Exception ("No Seats found.");
			  
		}catch(Exception e){
			fail("Failed to retrieve Seats. \n Error Message :" + e);
		}	
	}

}
