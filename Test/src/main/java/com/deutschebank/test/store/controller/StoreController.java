package com.deutschebank.test.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deutschebank.test.store.model.TradeDTO;
import com.deutschebank.test.store.service.StoreService;


@RestController
public class StoreController {

	@Autowired
	StoreService service;

	@RequestMapping(value = "/saveTrade" , method = RequestMethod.POST)
	public ResponseEntity<String> saveTrade(@RequestBody TradeDTO trade) {
		try {
			if(service.isValidTrade(trade)) {
				
				String s = service.saveTrade(trade);
				return new ResponseEntity<String>(s,HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	
		return new ResponseEntity<String>("Invalid Trade received",HttpStatus.BAD_REQUEST);
	}

}
