package net.roshan.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.roshan.shoppingBackEnd.dao.ProductDAO;
import net.roshan.shoppingBackEnd.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {
	
	@Autowired
	ProductDAO productDAO;
	
	@RequestMapping("/all/products")
	@ResponseBody   //It will Look for converter to convert into JSON
	public List<Product> getAllProducts(){
		return productDAO.getActiveProducts();
	}
	
	@RequestMapping("/admin/all/products")
	@ResponseBody   //It will Look for converter to convert into JSON
	public List<Product> getAllProductsForAdmin(){
		return productDAO.list();
	}
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody   //It will Look for converter to convert into JSON
	public List<Product> getAllProductsByCategory(@PathVariable int id){
		return productDAO.getActiveProductsByCategory(id);
	}
	
}
