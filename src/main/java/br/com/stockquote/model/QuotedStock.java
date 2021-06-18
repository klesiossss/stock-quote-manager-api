package br.com.stockquote.model;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "stock_")
public class QuotedStock {
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long _id;
	
	@JsonAlias("id")
	private String idStock;
	
	@JsonIgnore
	private String description; 
	
	private HashMap<String, String> quotes;
	
	
	public QuotedStock(Long _id, String idStock, String description, HashMap<String, String> quotes) {
		this._id = _id;
		this.idStock = idStock;
		this.description = description;
		this.quotes = quotes;
	}

	
	public QuotedStock() {
	
	}
	
	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String getIdStock() {
		return idStock;
	}

	public void setIdStock(String idStock) {
		this.idStock = idStock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HashMap<String, String> getQuotes() {
		return quotes;
	}

	public void setQuotes(HashMap<String, String> quotes) {
		this.quotes = quotes;
	}
		
	
}
