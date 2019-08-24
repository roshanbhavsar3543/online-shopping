package net.roshan.shoppingBackEnd.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.Assert.assertEquals;
import net.roshan.shoppingBackEnd.dao.CategoryDAO;
import net.roshan.shoppingBackEnd.dao.ProductDAO;
import net.roshan.shoppingBackEnd.dto.Category;
import net.roshan.shoppingBackEnd.dto.Product;

public class ProductTestcase {
	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.roshan.shoppingBackEnd");
		context.refresh();
		
		productDAO=(ProductDAO)context.getBean("productDao");
	}
	
	/*
	@Test
	public void testCRUDofcategory() {
		// create operation
				product = new Product();
						
				product.setName("Oppo Selfie S53");
				product.setBrand("Oppo");
				product.setDescription("This is some description for oppo mobile phones!");
				product.setUnitPrice(25000);
				product.setActive(true);
				product.setCategoryId(3);
				product.setSupplierId(3);
				
				assertEquals("Something went wrong while inserting a new product!",
						true,productDAO.addProduct(product));		
				
				
				// reading and updating the category
				product = productDAO.get(2);
				product.setName("Samsung Galaxy S7");
				assertEquals("Something went wrong while updating the existing record!",
						true,productDAO.updateProduct(product));		
						
				assertEquals("Something went wrong while deleting the existing record!",
						true,productDAO.deleteProduct(product));		
				
				// list
				assertEquals("Something went wrong while fetching the list of products!",
						6,productDAO.list().size());
		
	}*/
	
	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong while fetching the list of products!",
				5,productDAO.getActiveProducts().size());				
	} 
	
	@Test
	public void testListActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching the list of products!",
				3,productDAO.getActiveProductsByCategory(3).size());
		assertEquals("Something went wrong while fetching the list of products!",
				2,productDAO.getActiveProductsByCategory(1).size());
	} 
	
	@Test
	public void testGetLatestActiveProduct() {
		assertEquals("Something went wrong while fetching the list of products!",
				3,productDAO.getLatestActiveProducts(3).size());
		
	}  
	
	
}
