package net.roshan.onlineshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleNohandlerFoundException() {
		ModelAndView mv =new ModelAndView("error");
		mv.addObject("errorTitle", "The Page is Not Constructed!");
		mv.addObject("errorDescription", "The Page You are looking is not available!");
		mv.addObject("title", "404 Error Page");
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleProductNotFoundException() {
		ModelAndView mv =new ModelAndView("error");
		mv.addObject("errorTitle", "Product Not Available!");
		mv.addObject("errorDescription", "The Product You are looking is not available!");
		mv.addObject("title", "Product Unavailable");
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex) {
		ModelAndView mv =new ModelAndView("error");
		mv.addObject("errorTitle", "Contact Your Administrator!");
		
		mv.addObject("title", "Error");

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		mv.addObject("errorDescription",sw.toString());
		return mv;
	}
}
