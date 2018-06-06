package fr.afpa.chat;

import java.util.ArrayList;

public class ListeUsers extends Listing<User>{
	
	/**
	 * 
	 */
	public ListeUsers() {
		super();
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setListe(ArrayList<User> liste) {
		this.liste=liste;		
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
		// TODO Auto-generated method stub
		return null;
	}

}
