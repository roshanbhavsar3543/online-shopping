package net.roshan.onlineshopping.modal;

import java.io.Serializable;

import net.roshan.shoppingBackEnd.dto.Address;
import net.roshan.shoppingBackEnd.dto.User;

public class RegisterModel implements Serializable {
	
	private User user;
	private Address address;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
