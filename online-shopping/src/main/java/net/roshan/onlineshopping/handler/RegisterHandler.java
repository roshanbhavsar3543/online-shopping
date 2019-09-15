package net.roshan.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import net.roshan.onlineshopping.modal.RegisterModel;
import net.roshan.shoppingBackEnd.dao.UserDAO;
import net.roshan.shoppingBackEnd.dto.Address;
import net.roshan.shoppingBackEnd.dto.Cart;
import net.roshan.shoppingBackEnd.dto.User;

@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public RegisterModel init() {
		
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel modal, User user) {
		modal.setUser(user);		
	}
	
	public void addAddress(RegisterModel modal, Address billing) {
		modal.setAddress(billing);	
	}
	
	public String saveAll(RegisterModel modal) {
		String transitionValue= "success";
		User user = modal.getUser();		
		if(user.getRole().equals("USER")) {
			Cart cart = new Cart();
			user.setCart(cart);
			cart.setUser(user);
		}
		//Encode password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userDao.addUser(user);
		
		Address billing = modal.getAddress();
		billing.setUser(user);
		billing.setBilling(true);
		userDao.addAddress(billing);
		
		return transitionValue;
	} 
	
	
	public String validateUser(User user,MessageContext error) {
		String transitionValue= "success";
		
		//Checking if password matches the confirm password
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			transitionValue= "failure";
			
			error.addMessage(new MessageBuilder().error()
					.source("confirmPassword")
					.defaultText("Password Does Not Match The Confirm Password")
					.build());
		}
		
		User userInDb = userDao.getByEmail(user.getEmail());
		
		if(userInDb!=null) {
			transitionValue= "failure";
			error.addMessage(new MessageBuilder().error()
					.source("email")
					.defaultText("Email Address Already Used !!!")
					.build());
		}
		
		return transitionValue;
	}
}
