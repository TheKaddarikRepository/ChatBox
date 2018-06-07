package fr.afpa.dao;

import java.util.ArrayList;
import java.util.Properties;

import fr.afpa.security.PasswordRedirection;

public class DaoLogin implements DAOImplementation<PasswordRedirection> {
	DAOFactory daoFactory;
	
	public DaoLogin(DAOFactory daoFactory) {
		super();
		this.daoFactory=daoFactory;
	}

	@Override
	public ArrayList<PasswordRedirection> getListOfElements(PasswordRedirection element) throws DaoException {


		return null;
	}

	@Override
	public void insertElement(PasswordRedirection element) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeElement(PasswordRedirection element) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateElement(PasswordRedirection element, Properties modified) throws DaoException {
		// TODO Auto-generated method stub
		
	}

}
