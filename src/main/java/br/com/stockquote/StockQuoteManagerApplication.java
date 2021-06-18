package br.com.stockquote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.stockquote.service.QuoteStockService;

@SpringBootApplication
public class StockQuoteManagerApplication implements CommandLineRunner {

	/**
	 * Para acessar a documentacao suba a aplicacao e acesse:
	 * 
	 * http://localhost:8081/swagger-ui.html 
	 * 
	 * 
	 * 
	 * 
	 * */
	
	@Autowired
	QuoteStockService qss = new QuoteStockService();
	
	public static void main(String[] args) {
		SpringApplication.run(StockQuoteManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		if(qss.findAllCash().isEmpty())
			qss.saveStocksFromApi();
	
			qss.registerItSelf();
		
			
	
		
	}

}
