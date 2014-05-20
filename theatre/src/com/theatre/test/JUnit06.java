package com.theatre.test;

import static org.junit.Assert.*;

import org.junit.Test;
import com.theatre.actions.TheatreDAO;


public class JUnit06 {

	@Test
	public void test() {
		/* Execution without Exception implies a successful test case, otherwise a failed case */
		try{
			  TheatreDAO dao = new TheatreDAO();
			  
		       String msg = dao.deleteShow("101", "2");
			   assertEquals(msg, "deleted");
			  
		}catch(Exception e){
			fail("Failed to delete Show. \n Error Message :" + e);
	
		}	}

}
