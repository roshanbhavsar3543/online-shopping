package net.roshan.shoppingBackEnd.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.roshan.shoppingBackEnd.dao.UserDAO;
import net.roshan.shoppingBackEnd.dto.Address;
import net.roshan.shoppingBackEnd.dto.Cart;
import net.roshan.shoppingBackEnd.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SessionFactory sessionfactory;
	@Override
	public boolean addUser(User user) {
		
		try {
			sessionfactory.getCurrentSession().persist(user);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}		
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			sessionfactory.getCurrentSession().persist(address);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}		
	}
/*
	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionfactory.getCurrentSession().update(cart);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}*/
	
	public User getByEmail(String email) {
		String selectQuery = "from User where email=:email";
		try {
			return (User) sessionfactory.getCurrentSession()
					.createQuery(selectQuery,User.class).setParameter("email", email).getSingleResult();
		}catch(Exception exception) {
			exception.printStackTrace();
			return null;
		}		
	}

	@Override
	public Address getBillingAddress(User user) {
		String selectQuery = "from Address where user=:user and billing=:billing";
		try {
			return (Address) sessionfactory.getCurrentSession()
					.createQuery(selectQuery,Address.class).setParameter("user", user)
					.setParameter("billing", true).getSingleResult();
		}catch(Exception exception) {
			exception.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<Address> getListOfShippingAddress(User user) {
		String selectQuery = "from Address where user=:user and shipping=:shipping";
		try {
			return (List<Address>) sessionfactory.getCurrentSession()
					.createQuery(selectQuery,Address.class).setParameter("user", user)
					.setParameter("shipping", true).getResultList();
		}catch(Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}
}
