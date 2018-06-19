package fr.afpa.dao;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

public interface IntDAOImage {

	public String saveImage(Part element, ServletContext servletContext) throws DaoException ;
	public String removeImage(String path, ServletContext servletContext) throws DaoException;
}
