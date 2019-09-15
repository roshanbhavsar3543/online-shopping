package net.roshan.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.roshan.onlineshopping.modal.UserModel;
import net.roshan.shoppingBackEnd.dao.CartLineDAO;
import net.roshan.shoppingBackEnd.dao.ProductDAO;
import net.roshan.shoppingBackEnd.dto.Cart;
import net.roshan.shoppingBackEnd.dto.CartLine;
import net.roshan.shoppingBackEnd.dto.Product;

@Service("cartService")
public class CartService {
	
	@Autowired
	private CartLineDAO cartLineDAO;
	@Autowired
	HttpSession session;
	
	@Autowired
	private ProductDAO productDAO;
	
	private Cart getCart() {		
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	
	public List<CartLine> getCartLines() {
		return cartLineDAO.list(getCart().getId());
	}

	public String manageCartLine(int cartLineId, int count) {
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine==null) {
			return "result=error";
		}
		Product product = cartLine.getProduct();
		double oldTotal = cartLine.getTotal();
		
		//check if product quantity is available
		if(product.getQuantity()< count) {
			return "result=unavailable";
		}
		cartLine.setProductCount(count);
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setTotal(product.getUnitPrice()*count);
		cartLineDAO.update(cartLine);
		
		Cart cart = getCart();		
		cart.setGrandTotal(cart.getGrandTotal() - oldTotal +cartLine.getTotal());
		cartLineDAO.updateCart(cart);
		return "result=updated";
	}

	public String deleteCartLine(int cartLineId) {
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine==null) {
			return "result=error";
		}
		Cart cart = getCart();
		cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() - 1);
		cartLineDAO.updateCart(cart);
		cartLineDAO.delete(cartLine);
		return "result=deleted";
	}

	public String addCartLine(int productId) {
		String response = null;
		Cart cart = this.getCart();
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		if(cartLine == null) {
			cartLine = new CartLine();			
			Product product	= productDAO.get(productId);
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setProductCount(1);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			cart.setCartLines(cart.getCartLines()+1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			response = "result=added";
		}else {
			//First check cartline has reached the maximum count			
			if(cartLine.getProductCount()<3) {
				response = this.manageCartLine(cartLine.getId(),cartLine.getProductCount()+1);
			}else {
				response = "result=maximum";
			}
		}
		return response;
	}
}
