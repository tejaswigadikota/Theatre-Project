package com.theatre.entities;

public class PaymentInfo {
	String status;
	String mode;
	Float total;
	
	public PaymentInfo(){}
	
	public PaymentInfo(String status, String mode, Float total) {
		super();
		this.status = status;
		this.mode = mode;
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "PaymentInfo [status=" + status + ", mode=" + mode + ", total="
				+ total + "]";
	}
	 
}
