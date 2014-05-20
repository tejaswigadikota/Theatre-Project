package com.theatre.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.theatre.actions.TheatreDAO;
import com.theatre.entities.Show;

public class JUnit13 {

	@Test
	public void test() {
		/* Execution without Exception implies a successful test case, otherwise a failed case */
		try{
			  TheatreDAO dao = new TheatreDAO();
			  
			   List<Show> shows = dao.getShows("101");
			   
			   if( shows.size() <= 0 )
				   throw new Exception ("No shows found.");
			  
		}catch(Exception e){
			fail("Failed to fetch Show(s). \n Error Message :" + e);
		}	
	}

}
