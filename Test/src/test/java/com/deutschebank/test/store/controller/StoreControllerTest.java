package com.deutschebank.test.store.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.deutschebank.test.store.model.TradeDTO;
import com.deutschebank.test.store.service.impl.StoreServiceImpl;

@SpringBootTest
public class StoreControllerTest {

	@InjectMocks
	StoreController controller;
	
	@Mock
	StoreServiceImpl service;
	
	@Test
	public void shouldSaveValidTrade() throws Exception {
		TradeDTO dto = getTestDTO();
		when(service.isValidTrade(dto)).thenReturn(true);
		when(service.saveTrade(dto)).thenReturn("Trade saved successfully");
		ResponseEntity<String> response = controller.saveTrade(dto); 
		assertEquals(response.getBody(), "Trade saved successfully"); 
		assertEquals(response.getStatusCode(), HttpStatus.CREATED); 
	}
	@Test
	public void shouldNotSaveInvalidTrade() throws Exception {
		TradeDTO dto = getTestDTO();
		when(service.isValidTrade(dto)).thenReturn(false);
		ResponseEntity<String> response = controller.saveTrade(dto); 
		assertEquals(response.getBody(), "Invalid Trade received"); 
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST); 
	}

	@Test
	public void shouldNotSaveLowerVersionTrade() throws Exception {
		TradeDTO dto = getTestDTO();
		when(service.isValidTrade(dto)).thenThrow(new Exception("Lower version of Trade received."));
		ResponseEntity<String> response = controller.saveTrade(dto); 
		assertEquals(response.getBody(), "Lower version of Trade received."); 
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST); 
	}

	private TradeDTO getTestDTO() {
		TradeDTO dto = new TradeDTO();
		dto.setVersion("1");
		dto.setBookId("B1");
		dto.setCounterPtyId("CP-1");
		dto.setMaturityDate("20/05/2022");
		dto.setCreatedDate("20/05/2020");
		dto.setExpired("N");
		return dto;
	}
	
}
