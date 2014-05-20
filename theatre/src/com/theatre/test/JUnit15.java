package com.theatre.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.theatre.actions.TheatreDAO;

public class JUnit15 {

	@Test
	public void test() {
		/* Execution without Exception implies a successful test case, otherwise a failed case */
		try{
			  TheatreDAO dao = new TheatreDAO();
			  Integer capacity = dao.getCapacity("101", "1");
			  
			  assertEquals(capacity, (Integer) 80 );
			  
		}catch(Exception e){
			fail("Failed to retrieve Show Capacity details. \n Error Message :" + e);
		}	
	}

}
