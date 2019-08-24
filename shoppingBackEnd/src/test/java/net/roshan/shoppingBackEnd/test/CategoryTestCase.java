package net.roshan.shoppingBackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.roshan.shoppingBackEnd.dao.CategoryDAO;
import net.roshan.shoppingBackEnd.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.roshan.shoppingBackEnd");
		context.refresh();
		
		categoryDAO=(CategoryDAO)context.getBean("categoryDao");
	}
	
	/*@Test
	public void testAddCategory() {
		category = new Category();		
		category.setName("Laptop");
		category.setDescription("Desc for Laptop");
		category.setImageUrl("Cat_2.png");
		category.setActive(true);
		
		category.setName("Mobile");
		category.setDescription("Desc for Mobile");
		category.setImageUrl("Cat_3.png");
		category.setActive(true);
		
		assertEquals("Category Added",true,categoryDAO.addCategory(category));
	}*/
	
	/*@Test
	public void testGetCategory() {
		category = categoryDAO.getCategoryById(1);
		
		assertEquals("category fetched successfully","Television", category.getName());
	};*/
	
	
	/*@Test
	public void testUpdateCategory() {
		category = categoryDAO.getCategoryById(1);
		if(category!=null) {
			category.setName("Television");
		}	
		assertEquals("Category Updated",true,categoryDAO.updateCategory(category));
	}*/
	
	/*@Test
	public void testDeleteCategory() {
		category = categoryDAO.getCategoryById(7);	
		assertEquals("Category Deleted",true,categoryDAO.deleteCategory(category));
	}*/
	
	/*@Test
	public void testGetActiveCategoryList() {
		//categoryDAO.getCategories()	;
		assertEquals("Category Fetched",4,categoryDAO.getCategories().size());
	}*/
	
	
	@Test
	public void testCRUDofcategory() {
		
		//Add Category 
		Category category0 =  new Category();		
		category0.setName("Mobile");
		category0.setDescription("Desc for Mobile");
		category0.setImageUrl("Cat_3.png");
		category0.setActive(true);
		
		assertEquals("Category Added",true,categoryDAO.addCategory(category0));
		/*Category category1 = new Category();
		category1.setName("Television");
		category1.setDescription("Desc for Television");
		category1.setImageUrl("Cat_2.png");
		category1.setActive(true);
		
		assertEquals("Category Added",true,categoryDAO.addCategory(category1));
		*/
		/*//Update Record
		category = categoryDAO.getCategoryById(2);
		if(category!=null) {
			category.setName("TV");
		}	
		assertEquals("Category Updated",true,categoryDAO.updateCategory(category));
		
		
		//Delete category (Deactivate)
		assertEquals("Category Deleted",true,categoryDAO.deleteCategory(category));
		
		//Get All categories
		
		assertEquals("Category Fetched",1,categoryDAO.getCategories().size());*/
	}
	
}
