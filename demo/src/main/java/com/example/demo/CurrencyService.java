package com.example.demo;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cache.CurrencyParitiesCache;
import com.example.demo.model.CurrencyParityDTO;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
@RequestMapping("/parities")
class CurrencyService {
	 private static final String DEFAULT_CURRENCY_RON = "RON";
	 
	 private CurrencyParitiesCache cache;	
	
	 public CurrencyService() throws UnirestException {
		    cache = new CurrencyParitiesCache();
	    }
 
    @GetMapping(value = "/{currency1}")
    public CurrencyParityDTO findById(@PathVariable("currency1") String currency1, 
    		@RequestParam(required=false) String transform) throws IOException {
    	transform = transform == null ? DEFAULT_CURRENCY_RON : transform;
        Double parity = cache.getCachedObject(currency1, transform);
        
        return new CurrencyParityDTO(currency1, transform, parity);
    }
}