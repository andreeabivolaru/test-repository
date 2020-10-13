package com.example.demo.model;

public class CurrencyFromTo {
	private String from;
	private String to;
	
	public CurrencyFromTo(String from, String to) {
		this.from = from;
		this.to = to;
	}
	
	public int hashCode() {
		int hashCode = 0;
		for (int i = 0; i < 3; i++) {
			hashCode += from.charAt(i) * to.charAt(i);
		}
		
		return hashCode;
	}
	
	public boolean equals(Object other) {
		if (! (other instanceof CurrencyFromTo)) {
			return false;
		}
		
		CurrencyFromTo otherObject = (CurrencyFromTo) other;
		
		return from.equals(otherObject.getFrom()) && to.equals(otherObject.getTo());
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}
	
	public String toString() {
		return from + "->" + to;
	}
}
