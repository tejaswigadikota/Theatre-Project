package com.theatre.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.theatre.actions.TheatreDAO;
import com.theatre.entities.Seat;

public class JUnit11 {

	@Test
	public void test() {
		/* Execution without Exception implies a successful test case, otherwise a failed case */
		try{
			  TheatreDAO dao = new TheatreDAO();
			  List<Seat> seats = new ArrayList<Seat>();
			  seats.add (new Seat(null, 4) );
			  seats.add( new Seat(null, 5) );
			  
			  dao.checkSeats(seats, "101", "1");
			  
		}catch(Exception e){
			fail("Requested Seat(s) not available. \n Error Message :" + e);
		}	}

}
