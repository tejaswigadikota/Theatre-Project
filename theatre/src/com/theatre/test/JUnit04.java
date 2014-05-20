package com.theatre.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.theatre.actions.TheatreDAO;
import com.theatre.entities.Booking;
import com.theatre.entities.Movie;
import com.theatre.entities.PaymentInfo;
import com.theatre.entities.Seat;
import com.theatre.entities.Show;
import com.theatre.entities.Viewer;

public class JUnit04 {

	@Test
	public void test() throws Exception{
		
		List<Seat> seats = new ArrayList<Seat>();
		seats.add (new Seat(80F, 2));
		
		Booking booking = new Booking(
				(Integer)0,
				new Date(),
				new Viewer(
							"Alex",
							"Marvel",
							"California",
							"yahudinesh@gmail.com",
							"+1 33445529"
						),
				new Movie(
							"101",
							null,
							null,
							null,
							null
						 ),
				new Show(	
							1,
							null,
							null,
							null
						),
				new PaymentInfo(
								"active",
								"cash",
								100.0F
						        ),
				seats
				);
		
		try{
			
			String bookingId = new TheatreDAO().newTicket(booking);
			Integer.parseInt(bookingId);
		      
		}catch(Exception e){
			fail(e.getMessage());
		}
	}

}
