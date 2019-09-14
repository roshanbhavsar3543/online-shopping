package net.roshan.onlineshopping.utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


public class FileUploadUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);
	private static final String ABS_PATH="C:\\Users\\Roshan Bhavsar\\Desktop\\online-shopping-Roshan\\online-shopping\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH="";
	
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		//to get real path
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		logger.info(REAL_PATH);
		
		//check all directories are exits if no create directories
		if(!new File(ABS_PATH).exists()) {
			//create directory
			new File(ABS_PATH).mkdirs();
		}
		if(!new File(REAL_PATH).exists()) {
			//create directory
			new File(REAL_PATH).mkdirs();
		}
		
		try {
			//server upload
			file.transferTo(new File(REAL_PATH+code+".jpg"));
			//project directory upload
			file.transferTo(new File(ABS_PATH+code+".jpg"));
		}catch(IOException ioException) {
			ioException.printStackTrace();
		}
		
	}
	
}
