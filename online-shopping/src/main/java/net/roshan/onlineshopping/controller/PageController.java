package net.roshan.onlineshopping.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.roshan.onlineshopping.exception.ProductNotFoundException;
import net.roshan.shoppingBackEnd.dao.CategoryDAO;
import net.roshan.shoppingBackEnd.dao.ProductDAO;
import net.roshan.shoppingBackEnd.dto.Category;
import net.roshan.shoppingBackEnd.dto.Product;

@Controller
public class PageController {
	
	private static Logger logger= LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDao;
	@Autowired
	ProductDAO productDAO;
	
	@RequestMapping(value= {"/","/home","/index"})
	public ModelAndView index() {
		ModelAndView mv =new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("categories", categoryDao.getCategories());
		mv.addObject("userClickHome",true);
		logger.info("Inside Page Controller Home Page-Info");
		logger.debug("Inside Page Controller Home Page-Debug");
		return mv;
	}
	
	@RequestMapping(value="/about")
	public ModelAndView about() {
		ModelAndView mv =new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout",true);
		return mv;
	}
	
	
	@RequestMapping(value="/contact")
	public ModelAndView contact() {
		ModelAndView mv =new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact",true);
		
		return mv;
	}
	
	/*
	 * Below method will fetch all Products
	 */
	@RequestMapping(value="/show/all/products")
	public ModelAndView getAllProducts() {
		ModelAndView mv =new ModelAndView("page");
		mv.addObject("title", "All Products");
		mv.addObject("categories", categoryDao.getCategories());
		mv.addObject("userClickAllProducts",true);
		return mv;
	}
	
	
	@RequestMapping(value="/show/category/{id}/products")
	public ModelAndView getCategoryProducts(@PathVariable("id") int id) {
		
		Category category = categoryDao.getCategoryById(id);
		
		ModelAndView mv =new ModelAndView("page");
		mv.addObject("title", category.getName());
		mv.addObject("category", category);
		mv.addObject("categories", categoryDao.getCategories());
		mv.addObject("userClickCategoryProduct",true);
		return mv;
	}	
	
	//Below Methods Are just for Practice
	
	/*@RequestMapping(value= "/test")
	public ModelAndView test(@RequestParam(value="greeting",required=false)String greeting) {
		ModelAndView mv =new ModelAndView("page");
		if(greeting==null) {
			greeting="Hello User";
		}
		mv.addObject("greeting", greeting);
		return mv;
	}*/
	
	@RequestMapping(value= "/test/{greeting}")
	public ModelAndView test(@PathVariable(value="greeting",required=false)String greeting) {
		ModelAndView mv =new ModelAndView("page");
		if(greeting==null) {
			greeting="Hello User";
		}
		mv.addObject("greeting", greeting);
		return mv;
	}
	
	/*
	 * Show Single Product
	 */
	@RequestMapping(value="/show/{id}/product") 
	public ModelAndView showSingleProduct(@PathVariable int id)throws ProductNotFoundException {
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		
		if(product==null) {
			throw new ProductNotFoundException();
		}
		product.setViews(product.getViews()+1);
		productDAO.updateProduct(product);
		mv.addObject("title",product.getName());
		mv.addObject("product",product);
		mv.addObject("userClickedOnShowSingleProduct", true);
		
		return mv;		
	}
	
	
	//having similar mapping as that of flow id
	@RequestMapping(value="/register")
	public ModelAndView register() {
		ModelAndView mv =new ModelAndView("page");
		return mv;
	}
	
	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam(name="error",required=false)String error) {
		ModelAndView mv =new ModelAndView("login");
		if(error!=null) {
			mv.addObject("message","Invalid Username And Password");
		}
		mv.addObject("title","Login");
		
		return mv;
	}
	
	//access denied page
	@RequestMapping(value="/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv =new ModelAndView("error");
		mv.addObject("errorTitle","Aha ! Caught you.");
		mv.addObject("errorDescription","You Are not authorised to view this page");
		mv.addObject("title","403 Access Denied");
		return mv;
	}
}
