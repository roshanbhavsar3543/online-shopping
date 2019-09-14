package net.roshan.shoppingBackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.roshan.shoppingBackEnd.dao.UserDAO;
import net.roshan.shoppingBackEnd.dto.Address;
import net.roshan.shoppingBackEnd.dto.Cart;
import net.roshan.shoppingBackEnd.dto.User;

public class UserTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDao;
	private User user =null;
	private Address address=null;
	private Cart cart=null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.roshan.shoppingBackEnd");
		context.refresh();
		userDao = (UserDAO)context.getBean("userDAO");
	}
	
	
	/*@Test
	public void testAdd() {
		user = new User() ;
		  user.setFirstName("Hrithik");
		  user.setLastName("Roshan");
		  user.setEmail("hr@gmail.com");
		  user.setContactNumber("1234512345");
		  user.setRole("USER");
		  user.setPassword("123456");
		  
		  assertEquals("Failed to Add User",true,userDao.addUser(user));
		  
		  
		  address = new Address();
		  address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		  address.setAddressLineTwo("Near Kaabil Store");
		  address.setCity("Mumbai");
		  address.setState("Maharashtra");
		  address.setCountry("India");
		  address.setPostalCode("400001");
		  address.setBilling(true);
		  address.setUserId(user.getId());
		  assertEquals("Failed to Add billing Address",true,userDao.addAddress(address));
		  
		  if(user.getRole().equals("USER")) {
			  cart = new Cart();
			  
			  //cart.setUserId(user.getId());
			  cart.setUser(user);
			  assertEquals("Failed to Add Cart",true,userDao.addCart(cart));
		  }
		  
		  
		  
		  address = new Address();
		  address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		  address.setAddressLineTwo("Near Kudrat Store");
		  address.setCity("Mumbai");
		  address.setState("Maharashtra");
		  address.setCountry("India");
		  address.setPostalCode("400001");
		  address.setShipping(true);
		  
		  address.setUserId(user.getId());
		  assertEquals("Failed to Add Shipping Address",true,userDao.addAddress(address));
	}*/
	
	
	/*@Test
	public void testAdd() {
		user = new User() ;
		  user.setFirstName("Hrithik");
		  user.setLastName("Roshan");
		  user.setEmail("hr@gmail.com");
		  user.setContactNumber("1234512345");
		  user.setRole("USER");
		  user.setPassword("123456");	  
		  
		  if(user.getRole().equals("USER")) {
			  cart = new Cart();
			  
			  //cart.setUserId(user.getId());
			  cart.setUser(user);
			  user.setCart(cart);
		  }
		  assertEquals("Failed to Add User",true,userDao.addUser(user));
	}*/
	/*
	@Test
	public void updateCart() {
		user = userDao.getByEmail("hr@gmail.com") ;
		cart= user.getCart();
		cart.setGrandTotal(5555);
		cart.setCartLines(5);
		
		  assertEquals("Failed to Update Cart",true,userDao.updateCart(cart));
	}*/
	
	

	/*@Test
	public void testAddress() {
		
		user = new User() ;
		  user.setFirstName("Hrithik");
		  user.setLastName("Roshan");
		  user.setEmail("hr@gmail.com");
		  user.setContactNumber("1234512345");
		  user.setRole("USER");
		  user.setPassword("123456");
		  
		  assertEquals("Failed to Add User",true,userDao.addUser(user));
		  
		  
		  address = new Address();
		  address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		  address.setAddressLineTwo("Near Kaabil Store");
		  address.setCity("Mumbai");
		  address.setState("Maharashtra");
		  address.setCountry("India");
		  address.setPostalCode("400001");
		  address.setBilling(true);
		  address.setUser(user);
		  assertEquals("Failed to Add billing Address",true,userDao.addAddress(address));
		
		  address = new Address();
		  address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		  address.setAddressLineTwo("Near Kudrat Store");
		  address.setCity("Mumbai");
		  address.setState("Maharashtra");
		  address.setCountry("India");
		  address.setPostalCode("400001");
		  address.setShipping(true);
		  address.setUser(user);
		  
		  assertEquals("Failed to Add Shipping Address",true,userDao.addAddress(address));		  
	}*/
	
	/*@Test
	public void testAddAddress() {
		
		user = userDao.getByEmail("hr@gmail.com") ;		  
		
		  address = new Address();
		  address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		  address.setAddressLineTwo("Near Kudrat Store");
		  address.setCity("Bangalore");
		  address.setState("karnataka");
		  address.setCountry("India");
		  address.setPostalCode("560068");
		  address.setShipping(true);
		  address.setUser(user);		  
		  assertEquals("Failed to Add Shipping Address",true,userDao.addAddress(address));		  
	}*/
	
	@Test
	public void testgetAddresses() {		
		user = userDao.getByEmail("hr@gmail.com") ;		
		  
		  	
		  assertEquals("Failed to Add Shipping Address",2,userDao.getListOfShippingAddress(user).size());	
		  
		assertEquals("Failed to Add Billing Address","Mumbai",userDao.getBillingAddress(user).getCity());
	}
	
}
