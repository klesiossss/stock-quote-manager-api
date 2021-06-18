package br.com.stockquote.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
public class StockDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long _id;
	private String id;
	private String description;
	
	
	public StockDTO(Long _id, String id, String description) {
		this._id = _id;
		this.id = id;
		this.description = description;
	}
	
	public StockDTO() {
		
	}
	
	public Long get_id() {
		return _id;
	}
	public void set_id(Long _id) {
		this._id = _id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
