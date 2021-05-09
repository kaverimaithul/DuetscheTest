package com.deutschebank.test.store.service;

import org.springframework.stereotype.Service;

import com.deutschebank.test.store.model.TradeDTO;

@Service
public interface StoreService {

	public boolean isValidTrade(TradeDTO trade) throws Exception;

	public String saveTrade(TradeDTO trade);

}
