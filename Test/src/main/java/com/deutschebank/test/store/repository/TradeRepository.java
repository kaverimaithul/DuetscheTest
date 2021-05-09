package com.deutschebank.test.store.repository;
import org.springframework.stereotype.Component;

@Component
public class TradeRepository{

	public TradeRepo findTrade(String createdDate , String counterPty , String bookId) {
		
		return null;
	}

	
	public TradeRepo save(TradeRepo trade) {
		/*
		 * ObjectMapper Obj = new ObjectMapper();
		 * 
		 * try {
		 * 
		 * // get Oraganisation object as a json string String jsonStr =
		 * Obj.writeValueAsString(trade); // Displaying JSON String
		 * System.out.println(jsonStr); File file=new File("JsonFile.json");
		 * file.createNewFile(); FileWriter fileWriter = new FileWriter(file);
		 * System.out.println("Writing JSON object to file");
		 * System.out.println("-----------------------");
		 * 
		 * fileWriter.write(jsonStr); }
		 * 
		 * catch (IOException e) { e.printStackTrace(); }
		 */
		return trade;
	}
}
