package com.example.demo.cache;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.demo.model.CurrencyFromTo;
import com.example.demo.model.CurrencyParities;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CurrencyParitiesCache {
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String CURRENCY_PARITIES_LOCATION = "https://api.currencyfreaks.com/latest?apikey=030ec28a91494e2387b0acf8b8314cad";
	
	private HashMap<CurrencyFromTo, Double> currencyParities = new HashMap<CurrencyFromTo, Double>();
	
	public CurrencyParitiesCache() throws UnirestException {
		buildParitiesCache(getCurrencyParities());
		startCacheRefreshThread();
	}
	
	private void buildParitiesCache(CurrencyParities parities) {
		Set<String> keySet = parities.getRates().keySet();
		
		keySet.forEach(key1 -> {
			keySet.forEach(key2 -> {
				if (!key1.equals(key2)) {
					currencyParities.put(new CurrencyFromTo(key1, key2), parities.getRates().get(key2)/parities.getRates().get(key1));
				}
			});
		});
	}
	
	public Double getCachedObject(String from, String to) {		
		return currencyParities.get(new CurrencyFromTo(from, to));
	}
	
	public HashMap<CurrencyFromTo, Double> getMap() {
		return currencyParities;
	}
	
	private CurrencyParities getCurrencyParities() throws UnirestException {
		Unirest.setTimeouts(0, 0);
	    HttpResponse<String> response = Unirest.get(CURRENCY_PARITIES_LOCATION)
	    	        .asString();
	    	
	    Gson g = new GsonBuilder()
	    	   .setDateFormat(DATE_FORMAT).create();
	    
	    return g.fromJson(response.getBody(), CurrencyParities.class);
	}
	
	private void startCacheRefreshThread() {
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(() -> {
		    try {
				buildParitiesCache(getCurrencyParities());
			} catch (UnirestException e) {
				e.printStackTrace();
			}
		}, 24, 24, TimeUnit.HOURS);
	}
}