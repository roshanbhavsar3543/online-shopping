package net.roshan.shoppingBackEnd.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.roshan.shoppingBackEnd.dao.CartLineDAO;
import net.roshan.shoppingBackEnd.dao.ProductDAO;
import net.roshan.shoppingBackEnd.dao.UserDAO;
import net.roshan.shoppingBackEnd.dto.Cart;
import net.roshan.shoppingBackEnd.dto.CartLine;
import net.roshan.shoppingBackEnd.dto.Product;
import net.roshan.shoppingBackEnd.dto.User;
import static org.junit.Assert.assertEquals;
public class CartLineTestCase {
	private static AnnotationConfigApplicationContext context;
	private static CartLineDAO cartLineDAO =null;
	private static ProductDAO productDAO = null;
	private static UserDAO userDAO = null;
	
	private Product product = null;
	private Cart cart=null;
	private CartLine cartLine =null;
	private User user = null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.roshan.shoppingBackEnd");
		context.refresh();
		
		productDAO=(ProductDAO)context.getBean("productDao");
		cartLineDAO=(CartLineDAO)context.getBean("cartLineDAO");
		userDAO=(UserDAO)context.getBean("userDAO");
	}
	
	@Test
	public void testAddCartLine() {
		user = userDAO.getByEmail("shine@gmail.com");
		
		cart = user.getCart();
		
		product = productDAO.get(1);
		
		CartLine cartLine = new CartLine();
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount()+1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		
		cartLine.setProduct(product);
		
		assertEquals("Failed to add the Cart Line",true, cartLineDAO.add(cartLine));
		
		cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
		cart.setCartLines(cart.getCartLines()+1);
		assertEquals("Failed to Update Cart",true, cartLineDAO.updateCart(cart));
	} 
}
