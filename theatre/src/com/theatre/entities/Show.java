package com.theatre.entities;

import java.util.Date;

public class Show {
  Integer showingId;
  Integer capacity;
  Float price;
  Date startsAt;
  
  public Show(){}
  
  public Show(Integer showingId, Integer capacity, Float price, Date startsAt) {
	super();
	this.showingId = showingId;
	this.capacity = capacity;
	this.price = price;
	this.startsAt = startsAt;
}


public Integer getShowingId() {
	return showingId;
}


public void setShowingId(Integer showingId) {
	this.showingId = showingId;
}


public Integer getCapacity() {
	return capacity;
}


public void setCapacity(Integer capacity) {
	this.capacity = capacity;
}


public Float getPrice() {
	return price;
}


public void setPrice(Float price) {
	this.price = price;
}


public Date getStartsAt() {
	return startsAt;
}


public void setStartsAt(Date startsAt) {
	this.startsAt = startsAt;
}


@Override
public String toString() {
	return "Show [showingId=" + showingId + ", capacity=" + capacity
			+ ", price=" + price + ", startsAt=" + startsAt + "]";
}

  
}
