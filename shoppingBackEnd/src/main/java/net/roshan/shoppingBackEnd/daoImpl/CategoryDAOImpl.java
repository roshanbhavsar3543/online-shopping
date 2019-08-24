package net.roshan.shoppingBackEnd.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.roshan.shoppingBackEnd.dao.CategoryDAO;
import net.roshan.shoppingBackEnd.dto.Category;

@Repository("categoryDao")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {
	
	@Autowired
	SessionFactory sf;
	
	@Override
	public List<Category> getCategories() {
		String fetchActivecategories ="FROM Category where isActive= :active";
		
		Query query = sf.getCurrentSession().createQuery(fetchActivecategories);
		query.setParameter("active", true);
		return query.getResultList();
	}
	
	
	//Get Single category Based on Id
	@Override
	public Category getCategoryById(int id) {
		
		return sf.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
	public boolean addCategory(Category category) {		
		try {
			sf.getCurrentSession().persist(category);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}		
	}

	@Override
	public boolean updateCategory(Category category) {
		try {
			sf.getCurrentSession().update(category);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteCategory(Category category) {		
		try {
			category.setActive(false);
			sf.getCurrentSession().update(category);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
}
