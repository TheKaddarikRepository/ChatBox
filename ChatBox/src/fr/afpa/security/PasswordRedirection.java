package fr.afpa.security;

import fr.afpa.chat.User;

public class PasswordRedirection {
	private String url;
	private User user;
	private String state;

	/**
	 * @param url
	 * @param user
	 * @param date
	 * @param state
	 */
	public PasswordRedirection(String url, User user, String state) {
		super();
		this.url = url;
		this.user = user;
		this.state = state;
	}

	/**
	 * 
	 */
	public PasswordRedirection() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasswordRedirection other = (PasswordRedirection) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PasswordRedirection [url=" + url + ", user=" + user.toString() + ", state=" + state + "]";
	}

}
