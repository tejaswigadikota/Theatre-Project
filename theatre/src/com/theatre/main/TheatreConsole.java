package com.theatre.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.theatre.entities.Booking;
import com.theatre.entities.Movie;
import com.theatre.entities.PaymentInfo;
import com.theatre.entities.Seat;
import com.theatre.entities.Show;
import com.theatre.entities.Viewer;
import com.theatre.utills.Mail;
import com.theatre.actions.TheatreDAO;

public class TheatreConsole {

	static Scanner s = new Scanner(System.in);
	
	public void displayMenu(){
		System.out.println("\n\n--------------MENU-------------");
		System.out.println("1. New Movie");
		System.out.println("2. Add Show");
		System.out.println("3. New Ticket");
		System.out.println("4. Cancel Ticket");
		System.out.println("5. Search Booking");
		System.out.println("6. Exit");
		
		System.out.println("\n Choose Any Option. Input Number and Hit Enter");
		
	}
	
	public void start() {
		
		
		int choice; 
		
		while(true){
			choice = 0;
			displayMenu();
			choice = s.nextInt();
			
			try{
			switch(choice){
				case 1: newMovie();      break;
				case 2: addShow();       break;
				case 3: newTicket();     break;
				case 4: cancelTicket();  break;
				case 5: searchBooking(); break;
				case 6: return;
				default: System.out.println("Invalid Choice. Retry");
			 }
			}catch(Exception e){
				System.out.println("Error:"+e.getMessage());
			}
				
		}//end loop
	}
	
	private void searchBooking() throws Exception{
		
		System.out.println("Search Via:");
		System.out.println("1. Booking Id");
		System.out.println("2. Lastname");
		System.out.println("3. Email Id");
		System.out.println("4. Phone No.");
		System.out.println("5. Show");
		System.out.println("6. Day");
		System.out.println("7. Day Range");
		
		TheatreDAO dao = new TheatreDAO();
		List<Booking> bookings = new ArrayList<Booking>();
		
		
		System.out.println("Enter choice: ");
	
		int choice = s.nextInt();
		s.nextLine();
		
		switch(choice){
		case 1:
		 System.out.println("Enter Booking Id: ");
		 String bookingId = s.nextLine();
		 Booking b = dao.getTicket(bookingId);
		 if(b!=null)
		    bookings.add(b);
		 break;
		
		case 2:
		 System.out.println("Enter Last name: ");
		 String lastname = s.nextLine();
		 bookings = dao.getBookingsWithLastname (lastname);
		 break;
		
		case 3:
		 System.out.println("Enter email id: ");
		 String email = s.nextLine();
		 bookings = dao.getBookingsWithEmail(email);
		 break;
		
		case 4:
	     System.out.println("Enter Phone No: ");
	     String phoneno = s.nextLine();
		 bookings = dao.getBookingsWithPhoneNo(phoneno);
		 break;
		 
		case 5:
		 String movieid = selectMovie().getId();
		 String showid = selectShow(movieid).getShowingId()+"";	
	     bookings = dao.getBookingsWithShow(movieid, showid);
		 break;
		 
		case 6:
		 System.out.println("Enter Date: (dd-MM-yyyy) ");
		 Date date = new SimpleDateFormat("dd-MM-yyyy-HH:mm").parse(s.nextLine()+"-00:00");
		 bookings = dao.getBookingsWithDate(date);
		 break;
		case 7:
			 System.out.println("Enter Start Date: (dd-MM-yyyy) ");
			 Date startdate = new SimpleDateFormat("dd-MM-yyyy-HH:mm").parse(s.nextLine()+"-00:00");
			 System.out.println("Enter End Date: (dd-MM-yyyy) ");
			 Date   enddate = new SimpleDateFormat("dd-MM-yyyy-HH:mm").parse(s.nextLine()+"-00:00");
			 bookings = dao.getBookingsBetweenDates(startdate, enddate);
			 break;
		case 8:
			throw new Exception("Invalid Choice");
			
		}
		
		System.out.println("----------------------------BOOKINGS---------------------------");
		if( bookings ==null || bookings.size()==0)
		  System.out.println("No Booking found for the given criteria.");
		else{
		  System.out.println( bookings.size()+ " Bookings Found:\n");	

		  System.out.print( frmt("ID",5) 			+"- ");
		  System.out.print( frmt("Date/Time",15)	+"- ");
		  System.out.print( frmt("Last name",15)	+"- ");
		  System.out.print( frmt("Seats",5)			+"- ");
		  System.out.print( frmt("Movie name",15)	+"- ");
		  System.out.println("Show Time");

		  for(Booking booking : bookings){
			  DateFormat df = new SimpleDateFormat("dd-MMM/HH:mm");
			  
			  System.out.print( frmt(booking.getId()+"",5) 					+"- ");
			  System.out.print( frmt(df.format(booking.getDate()),15)		+"- ");
			  System.out.print( frmt(booking.getViewer().getLastname(),15)	+"- ");
			  System.out.print( frmt(booking.getSeats().size()+"",5)		+"- ");
			  System.out.print( frmt(booking.getMovie().getName(),15)		+"- ");
			  
			  System.out.println(df.format(booking.getShow().getStartsAt()) );
		  }
		}
	}

	
	private void cancelTicket() throws Exception{
		System.out.println("Enter Booking ID to Cancel: ");
		String bookingId = ""+s.nextInt();
		s.nextLine();
		TheatreDAO dao = new TheatreDAO();
		Booking booking = dao.getTicket(bookingId);
		dao.cancelTicket(bookingId);
		System.out.println("Booking has been cancelled.");
		
		
		//SENDING MAIL
		System.out.print("\nSending mail... ");
		
		Mail gmail = new Mail();
		gmail.to( booking.getViewer().getEmail());
		gmail.subject("Ticked Cancelled. Booking No:" + bookingId);
		gmail.message( "Hi "+ booking.getViewer().getLastname()+ "\n" + "Ticket successfully cancelled. thanks for using services.");
		gmail.send();
		
		System.out.println("Mail sent.\n\n");

	}

	private void newTicket() throws Exception {
		
		Booking booking = new Booking();
	
			
		booking.setDate(new Date());		
		booking.setMovie( selectMovie() );		
		booking.setShow( selectShow( booking.getMovie().getId() ) );
		
		
		System.out.println("Enter No. of Seats Required: ");
		
		int no = s.nextInt();
		s.nextLine();
		
		displaySeats(booking.getMovie().getId() , ""+booking.getShow().getShowingId());
		
		booking.setSeats(new ArrayList<Seat>());
		
		for(int i=1; i<=no ; i++){
			System.out.println("Enter Seat No: ");
			int num = s.nextInt();
			s.nextLine();
			
			float price = booking.getShow().getPrice();
			
			System.out.println("Price: "+price + ".  Want to modify Price for this seat? (Y/N)");
			if(  "Y".equalsIgnoreCase(s.nextLine())){
				System.out.println("Enter new price for this seat: ");
				price = s.nextFloat();
				s.nextLine();
			}
				
			booking.getSeats().add(new Seat(price, num));
		}
		
		
		float total = 0.0F;
		for(Seat seat : booking.getSeats()){
		   total = total + seat.getPrice();	
		}

		
		System.out.println("\n---------------CUSTOMER DETAILS---------------");

		Viewer viewer = new Viewer();
		
		System.out.print("Firstname: ");
		viewer.setFirstname(s.nextLine());
		
		System.out.print("Lastname: ");
		viewer.setLastname(s.nextLine());
		
		System.out.print("Email: ");
		viewer.setEmail(s.nextLine());
		
		System.out.print("Phone: ");
		viewer.setPhone(s.nextLine());
		 
		System.out.print("Address: ");
		viewer.setAddress(s.nextLine());
		
		
		booking.setViewer(viewer);

		
		////payment info
		System.out.println("\n----------------PAYMENT INFO-------------------");
		
		PaymentInfo paymentinfo = new PaymentInfo();
		
		
		System.out.println("Total Amount : "+total);		

		System.out.println("Enter Payment Mode (cash/card/transfer): ");
		paymentinfo.setMode(s.nextLine());
		
		paymentinfo.setStatus("active");
		paymentinfo.setTotal(total);
		
		booking.setPaymentInfo(paymentinfo);
		
		//System.out.println(booking);
		
		
		TheatreDAO dao = new TheatreDAO();
		String bookingId = dao.newTicket(booking);
		
		System.out.println("Booked. Booking ID: "+bookingId);
		
		
		//SENDING MAIL
		System.out.print("\nSending mail... ");
		
		Mail gmail = new Mail();
		gmail.to( booking.getViewer().getEmail());
		gmail.subject("Movie Ticket Booked. No:" + bookingId);
		gmail.message( "Hi "+ booking.getViewer().getLastname()+ "\n" + "thanks for booking with us. Get ready for movie");
		gmail.send();
		
		System.out.println("Mail sent.\n\n");
	}
	

	private Show selectShow (String movieId) throws Exception{
		
		TheatreDAO dao = new TheatreDAO();
		
		List<Show> shows = dao.getShows(movieId);
		
		int i=1;
		System.out.println("------------Select Show-------------");
		
		for( Show show : shows)
			System.out.println( (i++) + "-- " +  show.getStartsAt() );

		System.out.println("\nChoose a show :");
		int choice = s.nextInt();
		s.nextLine();
		
		return shows.get(choice-1);
	}

	private Movie selectMovie() throws Exception {
		TheatreDAO dao = new TheatreDAO();
		List<Movie> movies = dao.getMovies();
		int i=1;
		System.out.println("-----------Movies----------");
		for(Movie movie : movies)
			System.out.println( (i++) + "-- " + movie.getName() );
		
		System.out.print("\nEnter your choice: ");
		int choice = s.nextInt();
		s.nextLine();
		
		if( choice <1 || choice > (i-1) )
		  throw new Exception("Invalid Choice!");
		return movies.get(choice-1);
		
	}
	private void displaySeats(String movieId,String showId)throws Exception{
		TheatreDAO dao = new TheatreDAO();
		List<Seat> seats=dao.getAvailableSeats(movieId, showId);
		int capacity = dao.getCapacity(movieId, showId);
		int i=1;

		if(seats.size() == 0 )
			throw new Exception("All "+capacity+ " seat(s) are booked for this show. Kindly choose another.");
		
		System.out.println("--------------Available Seats----------------");
			for(Seat seat : seats){
				while( ! seat.getNo().equals((Integer)i)){
					if(i%10==1)
						System.out.println();
					System.out.print("XX,");
					
					i++; 
				}
				if(i%10==1)
					System.out.println();
				System.out.print( ((i<10)?"0":"") + i+",");
				i++;
			}
			for(; i<= capacity; i++ ){
				if(i%10 ==1 )
				System.out.println();
			   System.out.print("X,");
		    }
	   System.out.println("\n--------------------------------------------");
	}
	private void addShow() throws Exception{

		
		   String movieId = selectMovie().getId();
		
			Show show = new Show();
			System.out.print("Enter Capacity: ");
			show.setCapacity(s.nextInt());
			s.nextLine();
			System.out.print("Enter Price for this Show:");
			show.setPrice(s.nextFloat());
			s.nextLine();
			System.out.print("Enter show date and time in 'dd-MM-yyyy-HH:mm' format :");
			
			try{
			      show.setStartsAt( new SimpleDateFormat("dd-MM-yyyy-HH:mm", Locale.ENGLISH).parse(s.nextLine()) );
			}catch(Exception e){
				 show.setStartsAt(new Date());
				System.out.println("Invalid date/time entered. Default date/time taken.");
			}
			
			TheatreDAO dao = new TheatreDAO();
			dao.addShow(movieId, show);
			System.out.println("Show successfully added.");
	}

	public void newMovie() throws Exception{
		
		Movie movie = new Movie();
		s.nextLine(); //fix
		System.out.print("Enter Movie name: ");
		movie.setName(s.nextLine());
		System.out.print("Enter Movie Length: ");
		movie.setLength(s.nextLine());
		System.out.print("Enter Movie Description: ");
		movie.setInfo(s.nextLine());
		
		movie.setShows(new ArrayList<Show>());
		
		while(true){
			System.out.print("Do you want to Add a Show?");
			if(  ! "y".equalsIgnoreCase(s.nextLine()) )
				break;
			
			
			Show show = new Show();
			System.out.print("Enter Capacity: ");
			show.setCapacity(s.nextInt());
			s.nextLine();
			System.out.print("Enter Price for this Show:");
			show.setPrice(s.nextFloat());
			s.nextLine();
			System.out.print("Enter show date and time in 'dd-MM-yyyy-HH:mm' format :");
			
			try{
			      show.setStartsAt( new SimpleDateFormat("dd-MM-yyyy-HH:mm", Locale.ENGLISH).parse(s.nextLine()) );
			}catch(Exception e){
				 show.setStartsAt(new Date());
				System.out.println("Invalid date/time entered. Default date/time taken.");
			}
			
			movie.getShows().add(show);
			
		}
		 
		TheatreDAO dao = new TheatreDAO();
		dao.addMovie(movie);
		
		System.out.println("Movie Successfully Added.");
		
	}
	
	private static String frmt(String str,int size){
		str = str + "                           ";
		return str.substring(0,size);
	}
	
	public static void main(String args[]){
		new TheatreConsole().start();
	}
}
