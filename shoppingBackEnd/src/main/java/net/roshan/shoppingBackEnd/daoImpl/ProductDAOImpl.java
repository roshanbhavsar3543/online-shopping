package net.roshan.shoppingBackEnd.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.roshan.shoppingBackEnd.dao.ProductDAO;
import net.roshan.shoppingBackEnd.dto.Product;

@Repository("productDao")
@Transactional
public class ProductDAOImpl implements ProductDAO{
	
	@Autowired
	SessionFactory sf;
	
	@Override
	public Product get(int productId) {
		try {
			return sf.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> list() {
		
		return sf.getCurrentSession().createQuery("from Product", Product.class).getResultList();
	}

	@Override
	public boolean addProduct(Product product) {
		try {
			sf.getCurrentSession().persist(product);;
			 return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateProduct(Product product) {
		try {
			sf.getCurrentSession().update(product);;
			 return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProduct(Product product) {
		try {
			product.setActive(false);
			sf.getCurrentSession().update(product);;
			 return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> getActiveProducts() {
		String selectActiveProducts="from Product where active=:activeParam";
		return sf.getCurrentSession()
				.createQuery(selectActiveProducts,Product.class)
				.setParameter("activeParam", true)
				.getResultList();
	}

	@Override
	public List<Product> getActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory="from Product where active=:activeParam and categoryId=:categoryIdParam";
		return sf.getCurrentSession()
				.createQuery(selectActiveProductsByCategory,Product.class)
				.setParameter("activeParam", true)
				.setParameter("categoryIdParam", categoryId)
				.getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		String selectActiveProductsByCategory="from Product  where active=:activeParam order by ID";
		return sf.getCurrentSession()
				.createQuery(selectActiveProductsByCategory,Product.class)
				.setParameter("activeParam", true)
				.setFirstResult(0)
				.setMaxResults(count)
				.getResultList();
	
	}

}
