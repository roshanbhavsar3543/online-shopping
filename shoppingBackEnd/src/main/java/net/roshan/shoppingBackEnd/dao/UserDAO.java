package net.roshan.shoppingBackEnd.dao;

import java.util.List;

import net.roshan.shoppingBackEnd.dto.Address;
import net.roshan.shoppingBackEnd.dto.Cart;
import net.roshan.shoppingBackEnd.dto.User;

public interface UserDAO {
	
	boolean addUser(User user);
	boolean addAddress(Address address);
	//boolean updateCart(Cart cart);
	
	User getByEmail(String email);
	
	Address getBillingAddress(User user);
	
	List<Address> getListOfShippingAddress(User user);
	
}
