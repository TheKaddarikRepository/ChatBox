package fr.afpa.chat;

public class GroupException extends Exception {
	private static final long serialVersionUID = 6377478096605832037L;

	/**
	 * @param message
	 * @param cause
	 */
	public GroupException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public GroupException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public GroupException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
