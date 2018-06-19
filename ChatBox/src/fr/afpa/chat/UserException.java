package fr.afpa.chat;

public class UserException extends Exception {
	private static final long serialVersionUID = 1648913571895486723L;
	private UserAttribute type;

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public UserException(String message, Throwable cause, UserAttribute type) {
		super(message, cause);
		this.setType(type);
	}

	/**
	 * 
	 * @param message
	 */
	public UserException(String message, UserAttribute type) {
		super(message);
		this.setType(type);
	}

	/**
	 * 
	 * @param cause
	 */
	public UserException(Throwable cause, UserAttribute type) {
		super(cause);
		this.setType(type);
	}

	/**
	 * @return the type
	 */
	public UserAttribute getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	private void setType(UserAttribute type) {
		this.type = type;
	}

}
