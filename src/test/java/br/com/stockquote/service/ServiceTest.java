package br.com.stockquote.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.stockquote.exception.BusinessException;
import br.com.stockquote.exception.ExceptionMessage;
import br.com.stockquote.model.PropertySystem;
import br.com.stockquote.model.QuotedStock;
import br.com.stockquote.model.StockDTO;
import br.com.stockquote.repository.CacheRepository;
import br.com.stockquote.repository.QuotedStockRepository;


@SpringBootTest
@DisplayName("Stock Quote")
public class ServiceTest {
	
	@Mock
	private QuotedStockRepository quotedStockRepo;
	
	@Mock
	private CacheRepository cacheRepo;

	
	@InjectMocks
	private QuoteStockService quotedStockService;
	
	private static QuotedStock quotedStock;
	private static StockDTO stockDTO;
	private static StockDTO stock;
	private static PropertySystem propertySys;
	
	
	@BeforeAll
	static void beforeAll() {
		quotedStock = new QuotedStock();	
		stockDTO = new StockDTO();
		stock = new StockDTO();
		
		stockDTO.set_id(1L);
		stockDTO.setId("petr13");
		stockDTO.setDescription("Petrobras PN");
		
		HashMap<String,String> quotes = new HashMap<String,String>();	    
	   quotedStock.set_id(1L);
	    quotedStock.setIdStock("petr4");
	    quotedStock.setDescription("Petrobras PN");
	    quotes.put("2019-04-10" , "20");
	    quotes.put("2019-04-11" , "30");
	    quotes.put("2019-04-12" , "40");
	    quotedStock.setQuotes(quotes);
	}
	
	
	@Test
	@DisplayName("Deve Salvar uma cotacao seguindo criterio de refletir o mesmo Stock da API na validacao ")
	public  void TesteSalvandoCotacaoComCriterio() {
		when(quotedStockRepo.save(quotedStock)).thenReturn(quotedStock);
		
	
			quotedStock = quotedStockRepo.save(quotedStock);
		
		assertAll(() -> {
			assertEquals(quotedStock.getIdStock(),"petr4");
			assertEquals(quotedStock.getDescription(),"Petrobras PN");
			assertEquals(quotedStock.getQuotes(), quotedStock.getQuotes());	
		});
	}
	
	
	@Test
	@DisplayName("Deve lancar a excecao caso o stock nao reflita com a api")
	void testLancaExcessao() {
		when(quotedStockRepo.findById(1L)).thenReturn(Optional.empty());		
		assertThrows(BusinessException.class, () -> quotedStockService.salvar(quotedStock));
	}

	
}