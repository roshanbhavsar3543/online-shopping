package net.roshan.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.roshan.shoppingBackEnd.dao.CategoryDAO;
import net.roshan.shoppingBackEnd.dto.Category;

@Controller
public class PageController {
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@RequestMapping(value= {"/","/home","/index"})
	public ModelAndView index() {
		ModelAndView mv =new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("categories", categoryDao.getCategories());
		mv.addObject("userClickHome",true);
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
}
