package fr.afpa.dao;

public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9106387057214567437L;

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}
