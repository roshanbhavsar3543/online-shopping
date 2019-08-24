package net.roshan.onlineshopping.exception;

import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String messgae;
	
	public ProductNotFoundException() {
		this("Product is Not Available!");
	}
	
	public ProductNotFoundException(String message) {
		this.messgae =System.currentTimeMillis()+" : "+message;
	}

	public String getMessgae() {
		return messgae;
	}
}
