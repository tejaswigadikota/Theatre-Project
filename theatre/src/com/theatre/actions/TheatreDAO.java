package com.theatre.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.theatre.entities.Booking;
import com.theatre.entities.Movie;
import com.theatre.entities.PaymentInfo;
import com.theatre.entities.Seat;
import com.theatre.entities.Show;
import com.theatre.entities.Viewer;
import com.theatre.utills.XMLDOM;

public class TheatreDAO {
     public TheatreDAO(){
    	 
     }
     
     /******************Show ********************/
     public void addShow(String movieId, Show show) throws Exception{
    	 
    	 XMLDOM xml = new XMLDOM();
    	 
    	 Node  shows = xml.query("//theatre/movies/movie[@id='"+ movieId +"']/shows" ).item(0);
    	 
    	 if(shows == null)
    		 throw new Exception("Movie not found");
    	 
    	 
    	 Node showtag = xml.getDoc().createElement("show");
    	 
    	//starts_at
    	 String time = new SimpleDateFormat("dd-MM-yyyy-HH:mm").format(show.getStartsAt());
 
    	 Attr attr =  xml.getDoc().createAttribute("starts_at");
    	 attr.setValue(time);
    	 
    	 showtag.getAttributes().setNamedItem(attr);
    	 
     	//price
    	 String price = "" + show.getPrice();
 
    	 attr =  xml.getDoc().createAttribute("price");
    	 attr.setValue(price);
    	 
    	 showtag.getAttributes().setNamedItem(attr);
    	 
      	//capacity
     	 String capacity = "" + show.getCapacity();
  
     	 attr =  xml.getDoc().createAttribute("capacity");
     	 attr.setValue(capacity);
     	 
     	 showtag.getAttributes().setNamedItem(attr);
    	 
       	//showingId
      	 int sid = Integer.parseInt( shows.getAttributes().getNamedItem("last_show").getNodeValue()  );
         sid++;
         String showingId = "" + sid;
         
         shows.getAttributes().getNamedItem("last_show").setNodeValue(showingId); //write back
         
         
      	 attr =  xml.getDoc().createAttribute("showing_id");
      	 attr.setValue(showingId);
      	 
      	 showtag.getAttributes().setNamedItem(attr);

      	 
     	 shows.appendChild(showtag);
    	 xml.writeDoc();
    	 
     }
     public String deleteShow(String movieId,String showingId) throws Exception{

      	 XMLDOM xml = new XMLDOM();    	  
     	 Node  shows   = xml.query("//theatre/movies/movie[@id='"+ movieId +"']/shows" ).item(0);
      	 Node  showtag = xml.query("//theatre/movies/movie[@id='"+ movieId +"']/shows/show[@showing_id='"+ showingId +"']" ).item(0);

      	 if(shows==null)
      		 throw new Exception("Movie Not found");
      	 if(showtag==null)
      		 throw new Exception("Show Not found");
      	 
    	 shows.removeChild(showtag);
    	 
    	 xml.writeDoc();
    	 return "deleted";
      	 
      }
     public Show getShow(String movieId,String showingId)throws Exception{
    	 String price,capacity,startsAt;

    	 XMLDOM xml = new XMLDOM();
      	 Node  showtag = xml.query("//theatre/movies/movie[@id='"+ movieId +"']/shows/show[@showing_id='"+ showingId +"']" ).item(0);

      	 price =    showtag.getAttributes().getNamedItem("price").getNodeValue();
      	 capacity = showtag.getAttributes().getNamedItem("capacity").getNodeValue();
      	 startsAt = showtag.getAttributes().getNamedItem("starts_at").getNodeValue();
      	 
      	 Date d = new SimpleDateFormat("dd-MM-yyyy-HH:mm", Locale.ENGLISH).parse(startsAt);
      	 Show show = new Show(
      			 				Integer.parseInt(showingId),
      			 				Integer.parseInt(capacity),
      			 				Float.parseFloat(price),
      			 				d
      			 			 );
      	 
    	 return show;
     }

     
     
     /******************Movie ********************/
     public void addMovie(Movie movie) throws Exception{
    	 
    	 XMLDOM xml = new XMLDOM();
    	 
    	 Node  movies = xml.query("//theatre/movies" ).item(0);
    	 
    	 if(movies == null)
    		 throw new Exception("Criterical Error.");
    	 
    	 
    	 Node movietag = xml.getDoc().createElement("movie");
    	 
    	//movie_id
    	 String lastmovie = movies.getAttributes().getNamedItem("last_movie").getNodeValue();
         int last = Integer.parseInt(lastmovie);
         last = last + 1;
  
         lastmovie = ""+last;
         movies.getAttributes().getNamedItem("last_movie").setNodeValue(lastmovie);

         Attr attr =  xml.getDoc().createAttribute("id");
    	 attr.setValue(lastmovie);
    	 
    	 movietag.getAttributes().setNamedItem(attr);
    	 
     	 //name
    	 Node nametag = xml.getDoc().createElement("name");
         nametag.setTextContent(movie.getName());    	 
         movietag.appendChild(nametag);
         
     	 //length
    	 Node lengthtag = xml.getDoc().createElement("length");
         lengthtag.setTextContent(movie.getLength());    	 
         movietag.appendChild(lengthtag);

     	 //info
    	 Node infotag = xml.getDoc().createElement("info");
         infotag.setTextContent(movie.getInfo());    	 
         movietag.appendChild(infotag);
         

     	 //shows
    	 Node showstag = xml.getDoc().createElement("shows");

         
     	 attr =  xml.getDoc().createAttribute("last_show");
     	 attr.setValue("0");     	 
     	 showstag.getAttributes().setNamedItem(attr);
    	 
     	 movietag.appendChild(showstag);
     	 
     	 movies.appendChild(movietag);
     	 
     	 xml.writeDoc();
     	 
         for(Show show : movie.getShows())
        	 addShow(lastmovie, show);
     	 
     }
     public String deleteMovie(String movieId,String showingId) throws Exception{

      	 XMLDOM xml = new XMLDOM();    	  
     	 Node  shows   = xml.query("//theatre/movies/movie[@id='"+ movieId +"']/shows" ).item(0);
      	 Node  showtag = xml.query("//theatre/movies/movie[@id='"+ movieId +"']/shows/show[@showing_id='"+ showingId +"']" ).item(0);

      	 if(shows==null)
      		 throw new Exception("Movie Not found");
      	 if(showtag==null)
      		 throw new Exception("Show Not found");
      	 
    	 shows.removeChild(showtag);
    	 
    	 xml.writeDoc();
      	 return "deleted";
      }
     public Movie getMovie(String movieId)throws Exception{
    	 
    	 
    	 XMLDOM xml = new XMLDOM();

    	 Node  nametag   = xml.query("//theatre/movies/movie[@id='"+ movieId +"']/name"   ).item(0);
     	 Node  lengthtag = xml.query("//theatre/movies/movie[@id='"+ movieId +"']/length" ).item(0);
     	 Node  infotag   = xml.query("//theatre/movies/movie[@id='"+ movieId +"']/info"   ).item(0);
     	 
      	 NodeList showtags = xml.query("//theatre/movies/movie[@id='"+ movieId +"']/shows/show" );
   
      	 List<Show> shows = new ArrayList<Show>();
      	 
      	 for (int i=0;i< showtags.getLength() ;i++) {
      	     shows.add (
      	    		new Show(
      	    			   	  Integer.parseInt(showtags.item(i).getAttributes().getNamedItem("showing_id").getNodeValue()),
      	    		      	  Integer.parseInt(showtags.item(i).getAttributes().getNamedItem("capacity").getNodeValue()),
      	    		          Float.parseFloat(showtags.item(i).getAttributes().getNamedItem("price").getNodeValue()),
      	    		          new SimpleDateFormat("dd-MM-yyyy-HH:mm", Locale.ENGLISH).parse(showtags.item(i).getAttributes().getNamedItem("starts_at").getNodeValue())
      	      	    	    )
      	    		 );	 
      		 
      	 }
      	 
      	 Movie movie = new Movie(
      			  				 movieId,
      			  				 nametag.getTextContent(),
      			  				 lengthtag.getTextContent(),
      			  				 infotag.getTextContent(),
      			  				 shows
      			  				);
      	 
      	 return movie;
     }
         
     
     /******************Booking ********************/
     public String newTicket(Booking booking) throws Exception{
    	 
    	 checkSeats(booking.getSeats(),booking.getMovie().getId()+"", booking.getShow().getShowingId()+"");
    	 
    	 XMLDOM xml = new XMLDOM();
    	 
    	 Node  bookings = xml.query( "//theatre/bookings" ).item(0);
    	 

    	 if(bookings == null)
    		 throw new Exception("Critical Error.");
    	 
    	 ////

    	 Node bookingtag = xml.getDoc().createElement("booking");
    	 
    	//date
    	 String date = new SimpleDateFormat("dd-MM-yyyy-HH:mm").format(booking.getDate());
 
    	 Attr attr =  xml.getDoc().createAttribute("date");
    	 attr.setValue(date);
    	 
    	 bookingtag.getAttributes().setNamedItem(attr);
    	 
     	//movie_id
    	 String movieId = "" + booking.getMovie().getId();
 
    	 attr =  xml.getDoc().createAttribute("movie_id");
    	 attr.setValue(movieId);
    	 
    	 bookingtag.getAttributes().setNamedItem(attr);
    	 
      	//show_id
     	 String showId = "" + booking.getShow().getShowingId();
  
     	 attr =  xml.getDoc().createAttribute("show_id");
     	 attr.setValue(showId);
     	 
     	 bookingtag.getAttributes().setNamedItem(attr);
    	 
       	//Id
      	 int bid = Integer.parseInt( bookings.getAttributes().getNamedItem("last_booking").getNodeValue()  );
         bid = bid + 1 ;
         String bookingId = "" + bid;
         
         bookings.getAttributes().getNamedItem("last_booking").setNodeValue(bookingId); //write back
         booking.setId(Integer.parseInt(bookingId));
         
      	 attr =  xml.getDoc().createAttribute("id");
      	 attr.setValue(bookingId);
      	 
      	 bookingtag.getAttributes().setNamedItem(attr);

      	       	 
            /*** Viewer ***/	 
    	 
    	 Node viewertag = xml.getDoc().createElement("viewer");
    	 
    	  Node firstnametag = xml.getDoc().createElement("firstname");
    	  firstnametag.setTextContent(booking.getViewer().getFirstname());
    	  viewertag.appendChild(firstnametag);
    	  
    	  Node lastnametag = xml.getDoc().createElement("lastname");
    	  lastnametag.setTextContent(booking.getViewer().getLastname());
    	  viewertag.appendChild(lastnametag);
    	  
    	  Node addresstag = xml.getDoc().createElement("address");
    	  addresstag.setTextContent(booking.getViewer().getAddress());
    	  viewertag.appendChild(addresstag);
    	  
    	  Node emailtag = xml.getDoc().createElement("email");
    	  emailtag.setTextContent(booking.getViewer().getEmail());
    	  viewertag.appendChild(emailtag);
    	  
    	  Node phonetag = xml.getDoc().createElement("phone");
    	  phonetag.setTextContent(booking.getViewer().getPhone());
    	  viewertag.appendChild(phonetag);
    	  
    	  bookingtag.appendChild(viewertag);
    	  
    	  
    ////////////payment	  

      Node paymentinfotag = xml.getDoc().createElement("payment-info");
    	 
   	  Node statustag = xml.getDoc().createElement("status");
   	  statustag.setTextContent(booking.getPaymentInfo().getStatus());
   	  paymentinfotag.appendChild(statustag);
   	  
   	  Node modetag = xml.getDoc().createElement("mode");
   	  modetag.setTextContent(booking.getPaymentInfo().getMode());
   	  paymentinfotag.appendChild(modetag);
   	  
   	  Node totaltag = xml.getDoc().createElement("total");
   	  totaltag.setTextContent(""+booking.getPaymentInfo().getTotal());
   	  paymentinfotag.appendChild(totaltag);
   	  
   	  
   	  bookingtag.appendChild(paymentinfotag);
   	      	  
      Node seatstag = xml.getDoc().createElement("seats");
 	 attr =  xml.getDoc().createAttribute("total");
 	 attr.setValue(""+booking.getSeats().size());
 	 
 	 seatstag.getAttributes().setNamedItem(attr);
 	 

 	 //Seats
 	Node seattag; 
  
   	for(Seat seat : booking.getSeats()) {
  	 seattag = xml.getDoc().createElement("seat");
	 
 	//price
 	 String price = "" + seat.getPrice();

 	 attr =  xml.getDoc().createAttribute("price");
 	 attr.setValue(price);
 	 
 	 seattag.getAttributes().setNamedItem(attr);
 	 
  	//no
 	 String no = "" + seat.getNo();

 	 attr =  xml.getDoc().createAttribute("no");
 	 attr.setValue(no);
 	 
 	 seattag.getAttributes().setNamedItem(attr);

 	 seatstag.appendChild(seattag);
   	 }
   	
   	 bookingtag.appendChild(seatstag);
   	  /////////////////
     
   	
      bookings.appendChild(bookingtag);
      xml.writeDoc();
      
       return bookingId;
     	 
     }
     public boolean cancelTicket(String bookingId) throws Exception{

      	 XMLDOM xml = new XMLDOM();    	  
     	 Node  bookings   = xml.query("//theatre/bookings" ).item(0);
      	 Node  booking = xml.query("//theatre/bookings/booking[@id='"+ bookingId +"']" ).item(0);

      	 if(bookings==null)
      		 throw new Exception("Critical Error: Bookings");
      	 if(booking==null)
      		 throw new Exception("Ticket Not found");
      	 
    	 bookings.removeChild(booking);
    	 
    	 xml.writeDoc();
    	 return true;
      }
      
     public Booking getTicket(String bookingId)throws Exception{
    	 
    	 XMLDOM xml = new XMLDOM();
      	 Node  bookingtag    = xml.query("//theatre/bookings/booking[@id='"+ bookingId +"']"                 ).item(0);
      	 Node  firstnametag  = xml.query("//theatre/bookings/booking[@id='"+ bookingId +"']/viewer/firstname").item(0);
      	 Node  lastnametag   = xml.query("//theatre/bookings/booking[@id='"+ bookingId +"']/viewer/lastname" ).item(0);
      	 Node  addresstag    = xml.query("//theatre/bookings/booking[@id='"+ bookingId +"']/viewer/address"  ).item(0);
      	 Node  emailtag      = xml.query("//theatre/bookings/booking[@id='"+ bookingId +"']/viewer/email"    ).item(0);
      	 Node  phonetag      = xml.query("//theatre/bookings/booking[@id='"+ bookingId +"']/viewer/phone"    ).item(0);
      	 
      	 Node  statustag = xml.query("//theatre/bookings/booking[@id='"+ bookingId +"']/payment-info/status" ).item(0);
         Node  modetag = xml.query("//theatre/bookings/booking[@id='"+ bookingId +"']/payment-info/mode"   ).item(0);
      	 Node  totaltag = xml.query("//theatre/bookings/booking[@id='"+ bookingId +"']/payment-info/total"  ).item(0);

      	 NodeList  seatstag       = xml.query("//theatre/bookings/booking[@id='"+ bookingId +"']/seats/seat");
      	 
      	 
      	 Integer seatscount =  seatstag.getLength();
      	 
   
      	 
      	 List<Seat> seats = new ArrayList<Seat>();
      	 for(int i=0;i<seatscount;i++) {
      		 seats.add( new Seat(
      				             Float.parseFloat(seatstag.item(i).getAttributes().getNamedItem("price").getNodeValue()),
      				             Integer.parseInt(seatstag.item(i).getAttributes().getNamedItem("no").getNodeValue())  
      				            )
      		          );
      	 }
      	 
      	 Booking booking = new Booking(
      			Integer.parseInt(bookingId),
      			new SimpleDateFormat("dd-MM-yyyy-HH:mm", Locale.ENGLISH).parse(bookingtag.getAttributes().getNamedItem("date").getNodeValue()),
      			new Viewer(
      					   firstnametag.getTextContent(),
      					   lastnametag.getTextContent(),
      					   addresstag.getTextContent(), 
      					   emailtag.getTextContent(), 
      					   phonetag.getTextContent()
      					 ),
      		    getMovie(bookingtag.getAttributes().getNamedItem("movie_id").getNodeValue()),
      		    getShow(
      		    		 bookingtag.getAttributes().getNamedItem("movie_id").getNodeValue(), 
      		    		 bookingtag.getAttributes().getNamedItem("show_id").getNodeValue()
      		    		),
      		    new PaymentInfo(
      		    				 statustag.getTextContent(),
      		    		         modetag.getTextContent(), 
      		    		         Float.parseFloat(totaltag.getTextContent())
      		    		       ),
      		    seats
      		    );
      		    
      	 
    	 return booking;
     }

     public void checkSeats(List<Seat> seats,String movie,String show)throws Exception{
    	 XMLDOM xml = new XMLDOM();
    	 NodeList seattags = xml.query("//theatre/bookings/booking[@movie_id='"+movie+"'][@show_id='"+show+"']/seats/seat");
    	 

    	 for(Seat seat : seats)
    	   for(int i=0; i< seattags.getLength(); i++ )
    		  if(   seattags.item(i).getAttributes().getNamedItem("no").getNodeValue().equals( (""+seat.getNo()) ) )
    				  throw new Exception("Error:: Seat No." + seat.getNo() + " Already booked");
    	 
     }
     
     public List<Movie> getMovies() throws Exception{
    	 
    	 XMLDOM xml = new XMLDOM();
    	 NodeList movietags = xml.query("//theatre/movies/movie");
    	 List<Movie> movies = new ArrayList<Movie>();
    	 
    	 for(int i=0 ; i<movietags.getLength(); i++ )
    		 movies.add(  getMovie(movietags.item(i).getAttributes().getNamedItem("id").getNodeValue())  );
    	 
    	 return movies;
    	 
     }
     
     public List<Show> getShows(String movieId) throws Exception {
    	 
    	 XMLDOM xml = new XMLDOM();
    	 NodeList showstags = xml.query("//theatre/movies/movie[@id='" +movieId+ "']/shows/show");
    	 List<Show> shows = new ArrayList<Show>();
    	 
     	 for( int i=0 ; i< showstags.getLength(); i++)
     		 shows.add( getShow(movieId, showstags.item(i).getAttributes().getNamedItem("showing_id").getNodeValue()  ) );
    	 
    	 return shows;
     }
     
     
     public List<Seat> getAvailableSeats(String movieId,String showId) throws Exception{
    	 XMLDOM xml = new XMLDOM();
    	 NodeList booked = xml.query("//theatre/bookings/booking[@movie_id='"+ movieId+"'][@show_id='"+ showId +"']/seats/seat");
    	 Node available = xml.query("//theatre/movies/movie[@id='"+ movieId +"']/shows/show[@showing_id='"+showId+"']").item(0); 
  
    	 List<Seat> seats = new ArrayList<Seat>();
    	 
  
    	 Integer capacity = Integer.parseInt(available.getAttributes().getNamedItem("capacity").getNodeValue() );
    	 Float price = Float.parseFloat(available.getAttributes().getNamedItem("price").getNodeValue() );
    	 
    	 List<Integer> list = new ArrayList<Integer>();
    	 
    	 for(int i=1;i<=capacity;i++)
    	    list.add(i);
    	 for(int n=0; n<booked.getLength(); n++)
            list.remove( (Integer) Integer.parseInt(booked.item(n).getAttributes().getNamedItem("no").getNodeValue())  );
    	 
    	 for(Integer k : list){

    		 seats.add( new Seat(price, k));
    	 }
    	 
    	 return seats;
    	 
     }

     public Integer getCapacity(String movieId,String showId) throws Exception{
    	 XMLDOM xml = new XMLDOM();

    	 Node available = xml.query("//theatre/movies/movie[@id='"+ movieId +"']/shows/show[@showing_id='"+showId+"']").item(0); 

    	 Integer capacity = Integer.parseInt(available.getAttributes().getNamedItem("capacity").getNodeValue() );
    	 return capacity;
     }
     
     public List<Booking> getBookingsWithLastname(String lastname)throws Exception {
    	 
    	 XMLDOM xml = new XMLDOM();
      	 NodeList  bookingtags    = xml.query("//theatre/bookings/booking/viewer/lastname");
      	 List<Booking> bookings = new ArrayList<Booking>();

      	 for(int i=0;i< bookingtags.getLength(); i++){
      		 if(   lastname.equalsIgnoreCase( bookingtags.item(i).getTextContent() )  )
      			 bookings.add( getTicket(bookingtags.item(i).getParentNode().getParentNode().getAttributes().getNamedItem("id").getNodeValue()  )  );
      	 }
        return bookings;
     }
     
     public List<Booking> getBookingsWithPhoneNo(String phoneno)throws Exception {
    	 
    	 XMLDOM xml = new XMLDOM();
      	 NodeList  bookingtags    = xml.query("//theatre/bookings/booking/viewer/phone");
      	 List<Booking> bookings = new ArrayList<Booking>();
      	 
      	
      	 for(int i=0;i< bookingtags.getLength(); i++){
      		 if(   phoneno.equalsIgnoreCase( bookingtags.item(i).getTextContent() )  ){ 
      			 bookings.add( getTicket(bookingtags.item(i).getParentNode().getParentNode().getAttributes().getNamedItem("id").getNodeValue()  )  );
      		 }
      	}
        return bookings;
     }
     public List<Booking> getBookingsWithEmail(String email)throws Exception {
    	 
    	 XMLDOM xml = new XMLDOM();
      	 NodeList  bookingtags    = xml.query("//theatre/bookings/booking/viewer/email");
      	 List<Booking> bookings = new ArrayList<Booking>();
      	 
      	
      	 for(int i=0;i< bookingtags.getLength(); i++){
      		 if(   email.equalsIgnoreCase( bookingtags.item(i).getTextContent() )  )
      			 bookings.add( getTicket(bookingtags.item(i).getParentNode().getParentNode().getAttributes().getNamedItem("id").getNodeValue()  )  );
      	 }
        return bookings;
     }
     public List<Booking> getBookingsWithShow(String movie, String show)throws Exception {
    	 
    	 XMLDOM xml = new XMLDOM();
      	 NodeList  bookingtags    = xml.query("//theatre/bookings/booking[@movie_id='"+movie+"'][@show_id='"+show+"']");
      	 List<Booking> bookings = new ArrayList<Booking>();
      	 
      	
      	 for(int i=0;i< bookingtags.getLength(); i++){
      	   bookings.add( getTicket(bookingtags.item(i).getAttributes().getNamedItem("id").getNodeValue()  )  );
      	 }
        return bookings;
     }
     
     public List<Booking> getBookingsWithDate(Date date)throws Exception {
    	 
    	 XMLDOM xml = new XMLDOM();
      	 NodeList  bookingtags    = xml.query("//theatre/bookings/booking");
      	 List<Booking> bookings = new ArrayList<Booking>();
      	 
      	 Date date1;
      	
      	 for(int i=0;i< bookingtags.getLength(); i++){
      		 date1 = new SimpleDateFormat("dd-MM-yyyy-HH:mm").parse( bookingtags.item(i).getAttributes().getNamedItem("date").getNodeValue());
      		 
      		 if( date.compareTo(date1) ==0 )
      	        bookings.add( getTicket(bookingtags.item(i).getAttributes().getNamedItem("id").getNodeValue()  )  );
      	 }
        return bookings;
     }

     public List<Booking> getBookingsBetweenDates(Date lowerdate, Date upperdate)throws Exception {
    	 
    	 XMLDOM xml = new XMLDOM();
      	 NodeList  bookingtags    = xml.query("//theatre/bookings/booking");
      	 List<Booking> bookings = new ArrayList<Booking>();
      	 
      	 Date date;
      	
      	 for(int i=0;i< bookingtags.getLength(); i++){
      		 date = new SimpleDateFormat("dd-MM-yyyy-HH:mm").parse( bookingtags.item(i).getAttributes().getNamedItem("date").getNodeValue());
      		 
      		 if( date.compareTo(lowerdate) >=0 && date.compareTo(upperdate) <=0 )
      	        bookings.add( getTicket(bookingtags.item(i).getAttributes().getNamedItem("id").getNodeValue()  )  );
      	 }
        return bookings;
     }

         
}
