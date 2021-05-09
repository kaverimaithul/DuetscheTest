package com.deutschebank.test.store.repository;

public class TradeRepo {

	private Long tradeid;
	
	private String version;
	
	private String bookId;
	
	private String counterPtyId;
	
	private String maturityDate;
	
	private String createdDate;
	
	private String expired;
	
	

	public Long getTradeid() {
		return tradeid;
	}

	public void setTradeid(Long tradeid) {
		this.tradeid = tradeid;
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
	 @Override
	    public String toString()
	    {
	        return "Trade [tradeid="
	            + tradeid
	            + ", version="
	            + version
	            + ", bookId="
	            + bookId
	            + ", counterPtyId="
	            + counterPtyId
	            + ", maturityDate="
	            + maturityDate
	            + ", createdDate="
	            + createdDate
	            + ", expired="
	            + expired
	            + "]";
	    }
	
}
