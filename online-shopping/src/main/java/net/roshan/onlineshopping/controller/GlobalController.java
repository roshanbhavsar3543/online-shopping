package net.roshan.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.roshan.onlineshopping.modal.UserModel;
import net.roshan.shoppingBackEnd.dao.UserDAO;
import net.roshan.shoppingBackEnd.dto.User;

@ControllerAdvice
public class GlobalController {
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	UserDAO userDao;
	
	private UserModel userModel = null;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel(HttpSession httpSession) {
		if(httpSession.getAttribute("userModel")==null) {
			//Add User to session
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			User user = userDao.getByEmail(authentication.getName());
			if(user!=null) {
				userModel = new UserModel();
				userModel.setRole(user.getRole());
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setFullName(user.getFirstName()+" "+user.getLastName());				
				if(userModel.getRole().equals("USER")) {
					userModel.setCart(user.getCart());
				}
			}
			httpSession.setAttribute("userModel", userModel);
			return userModel;
		}
		
	return (UserModel)httpSession.getAttribute("userModel");
		//return null;
	}
}
