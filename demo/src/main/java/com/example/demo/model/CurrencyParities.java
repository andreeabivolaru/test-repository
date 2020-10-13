package com.example.demo.model;

import java.util.Date;
import java.util.Map;

public class CurrencyParities {
	private Date date;
	private String base;
	private Map<String, Double> rates;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public Map<String, Double> getRates() {
		return rates;
	}
	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}
}
