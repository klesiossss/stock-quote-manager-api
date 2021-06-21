package br.com.stockquote.api;


	import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.stockquote.model.StockDTO;

	public class StockAPI {
		
		RestTemplate restTemplate = new RestTemplate();


		public List<StockDTO> getApi() {
			
			String urlBase = "http://localhost:8080/stock";
			
			HttpHeaders header = new HttpHeaders();
			header.set("ContentType","application/json");
			HttpEntity<String> entity = new HttpEntity<>(header);

			ResponseEntity<StockDTO[]> repos = restTemplate.exchange(urlBase, HttpMethod.GET, entity, StockDTO[].class);
			
			List<StockDTO> res = Arrays.asList(repos.getBody());
			
			
			return res;
		}

	}
	


