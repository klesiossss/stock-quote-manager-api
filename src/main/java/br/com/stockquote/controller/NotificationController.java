package br.com.stockquote.controller;



import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/stockcache")
public class NotificationController {

	
    private QuoteStockService quoteStockService;
  
    
    @Autowired
    public NotificationController(QuoteStockService quotStockService) {
        this.quoteStockService = quotStockService;
        
    }
    
    @ApiOperation(value = "notication from stock-manager for deleting all local cache of stocks  ")
    @DeleteMapping
    public ResponseEntity<String> notification() {
    	quoteStockService.deleteAllCache();
    	return ResponseEntity.ok().build();
    }
    
  
   
}


