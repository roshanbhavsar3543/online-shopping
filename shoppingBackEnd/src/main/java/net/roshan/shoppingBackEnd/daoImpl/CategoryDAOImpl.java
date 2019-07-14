package net.roshan.shoppingBackEnd.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.roshan.shoppingBackEnd.dao.CategoryDAO;
import net.roshan.shoppingBackEnd.dto.Category;

@Repository("categoryDao")
public class CategoryDAOImpl implements CategoryDAO {
	
	private static List<Category> categories=new ArrayList<>();
	
	static {
		Category category= new Category();
		category.setId(1);
		category.setName("Television");
		category.setDescription("Desc for Television");
		category.setImageUrl("Cat_1.png");
		category.setActive(true);
		
		categories.add(category);
		
		category= new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("Desc for Mobile");
		category.setImageUrl("Cat_2.png");
		category.setActive(true);
		
		categories.add(category);
		
		category= new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("Desc for Laptop");
		category.setImageUrl("Cat_3.png");
		category.setActive(true);
		
		categories.add(category);
	}
	@Override
	public List<Category> getCategories() {
		
		return categories;
	}
	
	public Category getCategoryById(int id) {
		
		for(Category category:categories) {
			if(category.getId()==id) {
				return category;
			}
		}
		return null;
	}
	
}
