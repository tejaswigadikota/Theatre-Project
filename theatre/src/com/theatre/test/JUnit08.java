package com.theatre.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.theatre.actions.TheatreDAO;
import com.theatre.entities.Movie;
import com.theatre.entities.Show;

public class JUnit08 {

	@Test
	public void test() {
		/* Execution without Exception implies a successful test case, otherwise a failed case */
		try{
			  TheatreDAO dao = new TheatreDAO();
			  
			  List<Show> shows = new ArrayList<Show>();
			  
			  shows.add( 
					     new Show(
					    		 null,
					    		 60,
					    		 56.4F,
					    		 new Date()
					    		 )
					     );
			  
			  Movie movie = new Movie(
					  				 null,
					  				 "Ice Age",
					  				 "1.34",
					  				 "Award winning Movie",
					  				 shows
					  				);
			  dao.addMovie(movie);
			  
		}catch(Exception e){
			fail("Failed to Add Movie. \n Error Message :" + e);
		}
	}

}
