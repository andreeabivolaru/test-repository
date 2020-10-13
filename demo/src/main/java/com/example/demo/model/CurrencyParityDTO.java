package com.example.demo.model;

import java.util.Date;

public class CurrencyParityDTO {
	private String from;
	private String to;
	private Double parity;
	
	public CurrencyParityDTO(String from, String to, Double parity) {
		this.from = from;
		this.to = to;
		this.parity = parity;
	}
	
	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}
	
	public Double getParity() {
		return parity;
	}
	
	public Date getDate() {
		return new Date();
	}
}
