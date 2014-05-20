package com.theatre.entities;

public class Viewer {
 
    String firstname;
    String lastname;    
    String address;
    String email;
    String phone;
    
    public Viewer(){}
	public Viewer(String firstname, String lastname, String address,
			String email, String phone) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Viewer [firstname=" + firstname + ", lastname=" + lastname
				+ ", address=" + address + ", email=" + email + ", phone="
				+ phone + "]";
	}
    
    
}
