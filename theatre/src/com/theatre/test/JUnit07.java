package com.theatre.test;

import static org.junit.Assert.*;


import org.junit.Test;

import com.theatre.actions.TheatreDAO;
import com.theatre.entities.Show;

public class JUnit07 {

	@Test
	public void test() {
		/* Execution without any Exception implies a successful test case, otherwise a failed case */
		try{
			  TheatreDAO dao = new TheatreDAO();
			  Show show;
			  
			  show = dao.getShow("101", "1");
			  if(show==null)
				  throw new Exception("Show not found.");
			  
			  
		}catch(Exception e){
			fail("Failed to retreive Show. \n Error Message :" + e);
		}
	}

}
