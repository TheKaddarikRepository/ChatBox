package fr.afpa.dao;

import java.util.ArrayList;

public interface DAOImplementation<T> {

	/**
	 * 
	 * @param element
	 * @return
	 * @throws DaoException
	 */
	public ArrayList<T> getListOfElements(T element) throws DaoException;

	public void insertElement(T element) throws DaoException;

	public void removeElement(T element) throws DaoException;

	public void updateElement(T element) throws DaoException;

}
