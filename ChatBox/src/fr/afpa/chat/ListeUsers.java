package fr.afpa.chat;

import java.util.ArrayList;

import fr.afpa.dao.DAOFactory;
import fr.afpa.dao.DAOImplementation;
import fr.afpa.dao.DaoException;

public class ListeUsers extends Listing<User> {
	private DAOFactory daoFactory;

	/**
	 * 
	 */
	public ListeUsers() {
		super();
		this.daoFactory = fr.afpa.dao.DAOFactory.getInstance();
	}

	/**
	 * @param liste
	 */
	public ListeUsers(ArrayList<User> liste) {
		super(liste);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<User> getListe() throws DaoException {
		if (liste.isEmpty()) {
			DAOImplementation<User> myDAO = daoFactory.getDaoUser();
			return myDAO.getListOfElements(new User());
		} else {
			return liste;
		}
	}

	@Override
	public void setListe(ArrayList<User> liste) {
		this.liste = liste;
	}

	@Override
	public void addItem(User item) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void suppItem(User item) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public User getItem(User item) throws DaoException {
		if (liste.isEmpty()) {
			DAOImplementation<User> myDAO = daoFactory.getDaoUser();
			return myDAO.getListOfElements(item).get(0);
		} else {
			int index = liste.indexOf(item);
			if (index != -1) {
				return liste.get(index);
			} else {
				throw new DaoException("L'utilisateur n'est pas dans la liste.", null);
			}
		}
	}

}
