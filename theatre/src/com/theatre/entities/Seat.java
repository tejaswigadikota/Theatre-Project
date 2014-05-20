package com.theatre.entities;

public class Seat {
	Float price;
	Integer no;
	public Seat(Float price, Integer no) {
		super();
		this.price = price;
		this.no = no;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	@Override
	public String toString() {
		return "Seat [price=" + price + ", no=" + no + "]";
	}
	 
}
