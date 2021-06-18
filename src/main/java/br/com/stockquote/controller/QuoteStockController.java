package br.com.stockquote.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.stockquote.model.QuotedStock;
import br.com.stockquote.model.StockDTO;
import br.com.stockquote.service.QuoteStockService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/quote-stock")
public class QuoteStockController {

	
    private QuoteStockService quoteStockService;
  
    
    @Autowired
    public QuoteStockController(QuoteStockService quotStockService) {
        this.quoteStockService = quotStockService;
        
    }
    
    @ApiOperation(value = "Read all Stock Quotes ")
    @GetMapping
    public ResponseEntity<List<QuotedStock>> findAll() {
    	List<QuotedStock> stock =  quoteStockService.findAll();
    	return ResponseEntity.ok(stock);
    }
    
    
    @ApiOperation(value = "Read a Stock Quote by id ")
    @GetMapping("{id}")
    public ResponseEntity<Optional<QuotedStock>> findById(@PathVariable Long id) {
    	Optional<QuotedStock> stock =  quoteStockService.findById(id);
    	return ResponseEntity.ok(stock);
    }
    
    
	@ApiOperation(value = " Create a Stock Quote")
	@PostMapping
	public ResponseEntity<QuotedStock> salvar(@RequestBody QuotedStock stock){
		QuotedStock dev =  quoteStockService.salvar(stock);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dev.get_id()).toUri();
		return ResponseEntity.created(uri).body(dev); 
	}
    
    
    @ApiOperation(value = "Extra: Obtem todo historico de quotacoes para um dado id de Stock")
    @GetMapping("stock/{idStock}")
    public ResponseEntity<List<QuotedStock>> findByStock(@PathVariable String idStock) {
    	List<QuotedStock> stock =  quoteStockService.findByIdStock(idStock);
    	return ResponseEntity.ok(stock);
    }
    
    
	
	 @ApiOperation(value = "Read  Stock from cache by id ")
	 @GetMapping("cache/{id}")
	 public  ResponseEntity<Optional<StockDTO>> findByIdCache (@PathVariable Long id) {
		 Optional<StockDTO> stock =  quoteStockService.findStockByIdFromCash(id);
		 return ResponseEntity.ok(stock);
	 }
   
}
