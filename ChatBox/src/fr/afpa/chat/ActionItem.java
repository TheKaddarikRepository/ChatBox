package fr.afpa.chat;

import fr.afpa.dao.DaoException;

public interface ActionItem<T> {

	public void addItem(T item) throws DaoException;

	public void suppItem(T item) throws DaoException;

	public T getItem(T item) throws DaoException;
}
