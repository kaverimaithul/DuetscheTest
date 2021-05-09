package com.deutschebank.test.store.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.deutschebank.test.store.model.TradeDTO;
import com.deutschebank.test.store.repository.TradeRepo;
import com.deutschebank.test.store.repository.TradeRepository;

@SpringBootTest
public class StoreServiceTest {

	@InjectMocks
	StoreServiceImpl serviceImpl;

	@Mock
	TradeRepository repo;

	private final String SUCCESS = "Trade saved successfully";

	@Test
	public void shouldValidTradeReturnTrue() {
		TradeRepo trade = getTestTrade();
		when(repo.findTrade(anyString(), anyString(), anyString())).thenReturn(trade);
		boolean response;
		try {
			response = serviceImpl.isValidTrade(getTestDTO());
			assertEquals(response, true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void shouldValidTradeReturnfalse() {
		TradeDTO trade = getTestDTO();
		trade.setMaturityDate("20/04/2020");
		when(repo.findTrade(anyString(), anyString(), anyString())).thenReturn(null);
		boolean response;
		try {
			response = serviceImpl.isValidTrade(trade);
			assertEquals(response, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void shouldValidTradeThrowException() {
		TradeRepo trade = getTestTrade();
		trade.setVersion("4");
		when(repo.findTrade(anyString(), anyString(), anyString())).thenReturn(trade);
		assertThrows(Exception.class, () -> serviceImpl.isValidTrade(getTestDTO()));
	}

	@Test
	public void shouldSaveExistingTrade() {
		TradeRepo trade = getTestTrade();
		when(repo.findTrade(anyString(), anyString(), anyString())).thenReturn(trade);
		when(repo.save(any())).thenReturn(trade);
		try {
			String response = serviceImpl.saveTrade(getTestDTO());
			assertEquals(response, SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shoulSaveNewTrade() {
		when(repo.findTrade(anyString(), anyString(), anyString())).thenReturn(null);
		when(repo.save(any())).thenReturn(getTestTrade());
		try {
			String response = serviceImpl.saveTrade(getTestDTO());
			assertEquals(response, SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void shoulSaveExpiredTrade() {
		TradeRepo trade = getTestTrade();
		TradeDTO dto = getTestDTO();
		LocalDate maturityDate = LocalDate.now().minusDays(1);	
		DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dto.setMaturityDate(maturityDate.format(formatter_1));
		when(repo.findTrade(anyString(), anyString(), anyString())).thenReturn(trade);
		when(repo.save(any())).thenReturn(getTestTrade());
		try {
			String response = serviceImpl.saveTrade(dto);
			assertEquals(response, SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void shouldNotSaveTrade() {
		TradeRepo trade = getTestTrade();
		when(repo.findTrade(anyString(), anyString(), anyString())).thenReturn(null);
		when(repo.save(any())).thenReturn(null);
		try {
			String response = serviceImpl.saveTrade(getTestDTO());
			assertEquals(response, "Error in saving trade to database");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private TradeRepo getTestTrade() {
		TradeRepo trade = new TradeRepo();
		trade.setVersion("3");
		trade.setBookId("B1");
		trade.setCounterPtyId("CP-1");
		trade.setMaturityDate("20/05/2022");
		trade.setCreatedDate("20/05/2020");
		trade.setExpired("N");
		return trade;
	}

	private TradeDTO getTestDTO() {
		TradeDTO dto = new TradeDTO();
		dto.setVersion("3");
		dto.setBookId("B1");
		dto.setCounterPtyId("CP-1");
		dto.setMaturityDate("20/05/2022");
		dto.setCreatedDate("20/05/2020");
		dto.setExpired("N");
		return dto;
	}

}
