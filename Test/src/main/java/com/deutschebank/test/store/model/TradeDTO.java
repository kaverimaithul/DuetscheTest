package com.deutschebank.test.store.model;

public class TradeDTO {


	String tradeId;
	String version;
	String bookId;
	String counterPtyId;
	String maturityDate;
	String createdDate;
	String expired;
	
	public boolean equals(TradeDTO d) {
		return (this.bookId.equalsIgnoreCase(d.bookId) && this.counterPtyId.equalsIgnoreCase(d.getCounterPtyId()) && this.createdDate.equalsIgnoreCase(d.createdDate));
	}
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getCounterPtyId() {
		return counterPtyId;
	}
	public void setCounterPtyId(String counterPtyId) {
		this.counterPtyId = counterPtyId;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getExpired() {
		return expired;
	}
	public void setExpired(String expired) {
		this.expired = expired;
	}
	
	

}
