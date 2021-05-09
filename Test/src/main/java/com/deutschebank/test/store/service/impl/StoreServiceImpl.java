package com.deutschebank.test.store.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deutschebank.test.store.model.TradeDTO;
import com.deutschebank.test.store.repository.TradeRepo;
import com.deutschebank.test.store.repository.TradeRepository;
import com.deutschebank.test.store.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	TradeRepository repository;

	@Override
	public boolean isValidTrade(TradeDTO trade) throws Exception {
		
		TradeRepo existingTrade = repository.findTrade(trade.getCreatedDate(), trade.getCounterPtyId(), trade.getBookId());
		
		DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate maturityDate = LocalDate.parse(trade.getMaturityDate(), formatter_1);
		LocalDate createdDate = LocalDate.parse(trade.getCreatedDate(), formatter_1);
		if (existingTrade !=null) {
			int v = trade.getVersion() !=null? Integer.parseInt(trade.getVersion()):0;
			int e = existingTrade.getVersion() !=null? Integer.parseInt(existingTrade.getVersion()):0;
			if(v < e) {
				throw new Exception("Lower version of Trade received.");
			} 
		}			
		if(createdDate.isAfter(maturityDate)) {
			return false;
		}
		return true;
	}

	@Override
	public String saveTrade(TradeDTO trade) {
		// TODO Auto-generated method stub
		TradeRepo existingTrade =  repository.findTrade(trade.getCreatedDate(), trade.getCounterPtyId(), trade.getBookId());
		DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate maturityDate = LocalDate.parse(trade.getMaturityDate(), formatter_1);
		LocalDate today = LocalDate.now();
		if (existingTrade !=null) {
			existingTrade.setVersion(trade.getVersion());
		}else {
			ModelMapper modelMapper = new ModelMapper();
			existingTrade = modelMapper.map(trade, TradeRepo.class);
		}
		if(today.isAfter(maturityDate)) {
			existingTrade.setExpired("Y");
		}
		if(repository.save(existingTrade) !=null) {
			return "Trade saved successfully";
		}
				
		return "Error in saving trade to database";
	}

}
