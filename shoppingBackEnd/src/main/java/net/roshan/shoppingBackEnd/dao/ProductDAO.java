package net.roshan.shoppingBackEnd.dao;

import java.util.List;

import net.roshan.shoppingBackEnd.dto.Product;

public interface ProductDAO {
	
	Product get(int productId);
	List<Product> list();
	boolean addProduct(Product product);
	boolean updateProduct(Product product);
	boolean deleteProduct(Product product);
	
	List<Product> getActiveProducts();
	List<Product> getActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
	
	
}
