package com.theatre.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.theatre.actions.TheatreDAO;

public class JUnit02 {

	//Capacity
	
	@Test
	public void test() throws Exception {
		TheatreDAO dao = new TheatreDAO();
		Integer capacity = dao.getCapacity("101", "1");
		
		assertEquals(capacity,(Integer)80);
		
	
	}

}
