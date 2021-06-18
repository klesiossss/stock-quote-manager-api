package br.com.stockquote.repository;


import java.util.HashMap;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.stockquote.model.QuotedStock;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {
   
	@Autowired
    private QuotedStockRepository quotedStockRepo;
	
	
	
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    @DisplayName("Deve salvar uma nova cotacao")
    public void testeCriarPedido(){
        QuotedStock quotedStock = new QuotedStock();
   
        HashMap<String,String> quotes = new HashMap<String,String>();
    
        quotedStock.set_id(1L);
        quotedStock.setIdStock("petr4");
        quotedStock.setDescription("Petrobras PN");
        quotes.put("2019-04-21" , "15");
        quotes.put("2019-04-22" , "16");
        quotes.put("2019-04-22" , "17");
        quotedStock.setQuotes(quotes);
        
        QuotedStock salvaQuotedStock = quotedStockRepo.save(quotedStock);
		
        Assertions.assertThat(salvaQuotedStock.get_id()).isNotNull();
        Assertions.assertThat(salvaQuotedStock.getIdStock()).isEqualTo("petr4");
        Assertions.assertThat(salvaQuotedStock.getDescription()).isEqualTo("Petrobras PN");
        Assertions.assertThat(salvaQuotedStock.getQuotes()).isEqualTo(quotes);
    }
    
}
