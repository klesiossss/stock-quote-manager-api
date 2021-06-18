package br.com.stockquote.model;

public class PropertySystem {
	private String host;
	private Integer port;
	
	
	public PropertySystem(String host, Integer port) {
		this.host = host;
		this.port = port;
	}
	
	public PropertySystem() {
	
	}
	
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}

	
	
}
