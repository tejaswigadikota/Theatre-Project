package com.theatre.entities;

import java.util.Date;
import java.util.List;

public class Booking {
  Integer id;
  Date date;
  Viewer viewer;
  Movie movie;
  Show show;
  PaymentInfo paymentInfo;
  List<Seat> seats;
  
  
  public Booking(){}

public Booking(Integer id, Date date, Viewer viewer, Movie movie, Show show,
		PaymentInfo paymentInfo, List<Seat> seats) {
	super();
	this.id = id;
	this.date = date;
	this.viewer = viewer;
	this.movie = movie;
	this.show = show;
	this.paymentInfo = paymentInfo;
	this.seats = seats;
}

 

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Date getDate() {
	return date;
}


public void setDate(Date date) {
	this.date = date;
}


public Viewer getViewer() {
	return viewer;
}

public void setViewer(Viewer viewer) {
	this.viewer = viewer;
}

public Movie getMovie() {
	return movie;
}

public void setMovie(Movie movie) {
	this.movie = movie;
}

public Show getShow() {
	return show;
}

public void setShow(Show show) {
	this.show = show;
}

public PaymentInfo getPaymentInfo() {
	return paymentInfo;
}

public void setPaymentInfo(PaymentInfo paymentInfo) {
	this.paymentInfo = paymentInfo;
}

public List<Seat> getSeats() {
	return seats;
}

public void setSeats(List<Seat> seats) {
	this.seats = seats;
}



@Override
public String toString() {
	return "Booking [id=" + id + ", date=" + date + ", viewer=" + viewer
			+ ", movie=" + movie + ", show=" + show + ", paymentInfo="
			+ paymentInfo + ", seats=" + seats + "]";
}
  	
   

  
}
