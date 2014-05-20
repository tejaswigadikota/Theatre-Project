package com.theatre.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.theatre.actions.TheatreDAO;
import com.theatre.entities.Show;

public class JUnit05 {

	@Test
	public void test() {
		
		/* Execution without Exception implies a successful test case, otherwise a failed case */
		try{
			  TheatreDAO dao = new TheatreDAO();
			  
			  dao.addShow(
					  		"101",       		//Movie ID
					  		new Show(			//Show Object
					  				null,
					  				80,
					  				35F,
					  				new Date()
					  			)
					    );
			  
			  
		}catch(Exception e){
			fail("Failed to Add Show. \n Error Message :" + e);
		}
	}

}
