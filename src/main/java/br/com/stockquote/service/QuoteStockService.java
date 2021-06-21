package br.com.stockquote.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.stockquote.api.StockAPI;
import br.com.stockquote.exception.BusinessException;
import br.com.stockquote.exception.ResourceNotFoundException;
import br.com.stockquote.model.PropertySystem;
import br.com.stockquote.model.QuotedStock;
import br.com.stockquote.model.StockDTO;
import br.com.stockquote.repository.CacheRepository;
import br.com.stockquote.repository.QuotedStockRepository;

@Service
public class QuoteStockService {

	
	@Autowired
	CacheRepository cacheRepo;
	
	@Autowired
	QuotedStockRepository quotedStockRepo;
	
	public QuoteStockService(CacheRepository cacheRepo, QuotedStockRepository quotedStockRepo) {
			this.cacheRepo = cacheRepo;
			this.quotedStockRepo = quotedStockRepo;
	}
	
	
	public QuoteStockService() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Read a Stock Quote by id 
	 */
	public Optional<QuotedStock> findById(Long id) {
		return Optional.of(quotedStockRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException()));
	}
	
	/**
	 * Read all Stock Quotes 
	 */
	public List<QuotedStock> findAll() {
		return quotedStockRepo.findAll();
	}
	
	
	/**
	 * Salva uma nova cotacao para um determinado Stock
	 */
	public QuotedStock salvar(QuotedStock quote) {
		
		if(cacheRepo.findById(quote.getIdStock()).isPresent())
			return quotedStockRepo.save(quote);
		else throw new BusinessException("Stock inexistente, Esse stock nao reflete a API stock manager");	
	}
	

	/**
	 * Obtem todo historico de quotacoes para um dado id de Stock
	 */
	public List<QuotedStock> findByIdStock(String id) {
		List<QuotedStock> quotedStock = quotedStockRepo.findByIdStock(id);
		if(quotedStock.isEmpty()) throw new ResourceNotFoundException();
		return quotedStock;
	}
	
	
	/**
	 * Busca um Stock por id
	 */
	public Optional<StockDTO> obterStockPorId(String id) {
		return cacheRepo.findById(id);
	}
	
	
	/**
	 * 
	 * Busca todos os Stocks disponiveis na API stock-manager
	 */
	public List<StockDTO> getAllFromApi(){
		StockAPI stockApi = new StockAPI();	
		return stockApi.getApi();
	}
	
	
	/**
	 * Armazena todos Stocks disponiveis na API stock-manager e armazena em cache local
	 */
	public List<StockDTO> saveStocksFromApi(){
		return cacheRepo.saveAll(getAllFromApi());
	}
	
	
	/**
	 * retorna todos Stocks do cache em memoria 
	 * */
	public List<StockDTO> findAllCash() {
		return cacheRepo.findAll();	
	}
	
	
	/**
	 * retorna um Stock por id do cache em memoria 
	 * */
	public Optional<StockDTO> findStockByIdFromCash(Long id) {
		return Optional.of(cacheRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException()));	
	}
	
	
	/**
	 * 
	 * Stock-quote-manager should register itself at stock-manager during startup.
	 * */
	public ResponseEntity<PropertySystem[]>  registerItSelf() {
		RestTemplate restTemplate = new RestTemplate();
		String urlBase = "http://localhost:8080/notification"; 
		PropertySystem ps = new PropertySystem();
		ps.setHost("localhost");
		ps.setPort(8081);
		
		HttpHeaders header = new HttpHeaders();
		header.set("ContentType","application/json");
		HttpEntity<PropertySystem> entity = new HttpEntity<>(ps,header);
		
		return restTemplate.exchange(urlBase, HttpMethod.POST, entity, PropertySystem[].class);
			
	} 
	
	
	/**
	 * Delete all the Cache
	 * */
	public void deleteAllCache() {
		cacheRepo.deleteAll();
		saveStocksFromApi();
	}
	
	
	
	

}
