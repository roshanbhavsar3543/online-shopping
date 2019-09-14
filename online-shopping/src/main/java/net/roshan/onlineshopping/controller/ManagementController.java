package net.roshan.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.roshan.onlineshopping.utils.FileUploadUtils;
import net.roshan.onlineshopping.validator.ProductValidator;
import net.roshan.shoppingBackEnd.dao.CategoryDAO;
import net.roshan.shoppingBackEnd.dao.ProductDAO;
import net.roshan.shoppingBackEnd.dto.Category;
import net.roshan.shoppingBackEnd.dto.Product;

@Controller
@RequestMapping(value="/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManagedProducts(@RequestParam(name="operation",required=false) String operation ) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		Product newProduct = new Product();
		newProduct.setSupplierId(1);
		newProduct.setActive(true);
		mv.addObject("product",newProduct);
		
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message","Product Submitted Successfully!");
			}
			else if(operation.equals("category")) {
				mv.addObject("message","Category Addeded Successfully!");
			}
		}
		return mv;
	}
	
	//Below attribute is available for all methods of controller
	@ModelAttribute("categories")
	public List<Category> getListOfCategories(){		
		return categoryDAO.getCategories();
	}
	
	
	//Below attribute is available for all methods of controller
	@ModelAttribute("category")
	public Category getCategory(){		
		return new Category();
	}
		
	@RequestMapping(value="/products",method=RequestMethod.POST)//BindingResult should be exact after ModelAttribute
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product myProduct,BindingResult results,Model model,HttpServletRequest request) {
		
		if(myProduct.getId()== 0) {
			//file upload validation
			new ProductValidator().validate(myProduct, results);
		}else {
			if(!myProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(myProduct, results);
			}
		}
		
		
		if(results.hasErrors()) {
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation Failed For Product Submission");
			return "page";
		}

		logger.info(myProduct.toString());
		//for New Product
		if(myProduct.getId()== 0) {
			productDAO.addProduct(myProduct);
		}else {
			//update Product
			productDAO.updateProduct(myProduct);
		}
		
		
		if(!myProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtils.uploadFile(request,myProduct.getFile(),myProduct.getCode());
		}
		
		
		return "redirect:/manage/products?operation=product";
	}	
	
	//Activate Or Deactivate Product
	@RequestMapping(value="/product/{id}/activation",method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivationOrDeactivation(@PathVariable("id") int productId){
		System.out.println("Activate Or Deactivate Product "+ productId);
		Product product = productDAO.get(productId);
		boolean isActive = product.isActive();
		product.setActive(!product.isActive());	
		productDAO.updateProduct(product);
		if(isActive) {
			return "You Have Successfully Deactivated Product "+productId;
		}
		return "You Have Successfully Activated Product "+productId;
	}
	
	@RequestMapping(value="/{id}/product",method=RequestMethod.GET)
	public ModelAndView showProductForUpdate(@PathVariable(name="id") int productId) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		Product updateProduct = productDAO.get(productId);
		mv.addObject("product",updateProduct); 		
		return mv;
	}
	
	@RequestMapping(value="/category",method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute("category") Category category) {
		categoryDAO.addCategory(category);
		
		return "redirect:/manage/products?operation=category";
	}
}
