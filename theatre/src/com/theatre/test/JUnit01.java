package com.theatre.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.theatre.actions.TheatreDAO;

public class JUnit01 {

	@Test
	public void test() throws Exception {
		TheatreDAO dao = new TheatreDAO();
		String name = dao.getMovie("101").getName();
		
		assertEquals("Born Identity", name);
	
	}

}
