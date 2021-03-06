package net.roshan.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.roshan.onlineshopping.service.CartService;
import net.roshan.shoppingBackEnd.dao.ProductDAO;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	
	@RequestMapping("/show")
	public ModelAndView getCart(@RequestParam(name="result",required=false) String result) {
		
		ModelAndView mv = new ModelAndView("page");		
		if(result!=null) {
			switch (result) {
			case "updated":
				mv.addObject("message", "CartLine has been Updated Successfully");
				break;
			case "error":
				mv.addObject("message", "Something Went Wrong !");
				break;
			case "deleted":
				mv.addObject("message", "CartLine has been Deleted Successfully");
				break;	
			case "added":
				mv.addObject("message", "CartLine has been Added Successfully");
				break;
			case "unavailable":
				mv.addObject("message", "Product Quantity is not Available !");
				break;
			case "maximum":
				mv.addObject("message", "CartLine has reached Maximum Count");
				break;
			default:
				break;
			}
		}
		mv.addObject("title", "User Cart");
		mv.addObject("userClickShowCart", true);
		mv.addObject("cartLines",cartService.getCartLines());
		return mv;
	}
	
	@RequestMapping(value="/{cartLineId}/update")
	public  String updateCartLine(@RequestParam(name="count",required=true) int count,@PathVariable(name="cartLineId",required=true) int cartLineId) {
		
		String response = cartService.manageCartLine(cartLineId, count);
		return "redirect:/cart/show?"+response;		
	}
	
	@RequestMapping(value="/{cartLineId}/delete")
	public  String deleteCartLine(@PathVariable(name="cartLineId",required=true) int cartLineId) {		
		String response = cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?"+response;		
	}
	
	@RequestMapping(value="/add/{productId}/product")
	public String addCartLine (@PathVariable(name="productId",required=true) int productId) {		
		
		String response = cartService.addCartLine(productId);
		return "redirect:/cart/show?"+response;		
	}
	
}
