package fr.afpa.chat;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import fr.afpa.security.PermissionException;
import fr.afpa.security.UserPermission;

public class User {
	private String name;
	private String firstname;
	private String email;
	private String login;
	private String avatar;
	private UserPermission permission;
	private String ip_address;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * 
	 * @param name
	 * @param firstname
	 * @param email
	 * @param password
	 * @param login
	 * @param ip_address
	 * @throws UserException
	 */
	public User(String name, String firstname, String email, String login) throws UserException {
		super();
		this.setName(name);
		this.setFirstname(firstname);
		this.setEmail(email);
		this.setLogin(login);
	}

	/**
	 * @param name
	 * @param firstname
	 * @param email
	 * @param password
	 * @param login
	 * @param avatar
	 * @param ip_address
	 * @param memberships
	 */
	public User(String name, String firstname, String email, String login, String avatar, String ip_address)
			throws UserException {
		super();
		this.setName(name);
		this.setFirstname(firstname);
		this.setEmail(email);
		this.setLogin(login);
		this.setAvatar(avatar);
		this.setIp_address(ip_address);
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * @return the permission
	 */
	public UserPermission getPermission() {
		return permission;
	}

	/**
	 * @param permission
	 *            the permission to set
	 */
	public void setPermission(UserPermission permission) {
		this.permission = permission;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws UserException {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) throws UserException {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws UserException {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws UserException {
		this.login = login;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) throws UserException {
		this.avatar = avatar;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) throws UserException {
		this.ip_address = ip_address;
	}

	public static User register(String name, String firstname, String email, String password, String login,
			String avatar) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, PermissionException, UserException, InvalidKeyException  {
		User nubs = new User(name, firstname, email, login);
		nubs.setPermission(new UserPermission(Roles.User, password));

		return null;
	}
}
