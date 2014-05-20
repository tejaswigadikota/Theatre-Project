package com.theatre.entities;

import java.util.List;


public class Movie {
  String id;
  String name;
  String length;
  String info;
  List<Show> shows;
  
  public Movie(){}
public Movie(String id, String name, String length, String info,
		List<Show> shows) {
	super();
	this.id = id;
	this.name = name;
	this.length = length;
	this.info = info;
	this.shows = shows;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getLength() {
	return length;
}
public void setLength(String length) {
	this.length = length;
}
public String getInfo() {
	return info;
}
public void setInfo(String info) {
	this.info = info;
}
public List<Show> getShows() {
	return shows;
}
public void setShows(List<Show> shows) {
	this.shows = shows;
}
@Override
public String toString() {
	return "Movie [id=" + id + ", name=" + name + ", length=" + length
			+ ", info=" + info + ", shows=" + shows + "]";
}
  
    

  
}
