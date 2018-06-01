package fr.afpa.chat;

public class UserException extends Exception {
	private static final long serialVersionUID = 1648913571895486723L;

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public UserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param message
	 */
	public UserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param cause
	 */
	public UserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
