package com.theatre.test;

import static org.junit.Assert.*;


import java.util.List;

import org.junit.Test;

import com.theatre.actions.TheatreDAO;
import com.theatre.entities.Movie;

public class JUnit12 {

	@Test
	public void test() {
		/* Execution without Exception implies a successful test case, otherwise a failed case */
		try{
			  TheatreDAO dao = new TheatreDAO();
			  
			   List<Movie> movies = dao.getMovies();
			   
			   if( movies.size() <= 0 )
				   throw new Exception ("No Movies found.");
			  
		}catch(Exception e){
			fail("Failed to Add Movie. \n Error Message :" + e);
		}	
	}

}
