package fr.afpa.chat;

import java.util.ArrayList;

import fr.afpa.dao.DaoException;

public abstract class Listing<T> implements ActionItem<T> {
	protected ArrayList<T> liste;

	public Listing(ArrayList<T> liste) {
		super();
		this.liste = liste;
	}

	public Listing() {
		super();
	}

	abstract public ArrayList<T> getListe() throws DaoException;

	abstract public void setListe(ArrayList<T> liste);

}
