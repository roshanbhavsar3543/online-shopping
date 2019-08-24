package net.roshan.shoppingBackEnd.dao;

import java.util.List;

import net.roshan.shoppingBackEnd.dto.Category;

public interface CategoryDAO {
	
	List<Category> getCategories();
	
	Category getCategoryById(int id);
	
	boolean addCategory(Category category);
	
	boolean updateCategory(Category category);
	boolean deleteCategory(Category category);
	 

}
