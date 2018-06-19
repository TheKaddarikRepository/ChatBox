package fr.afpa.chat;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import fr.afpa.dao.DAOFactory;
import fr.afpa.dao.DaoException;
import fr.afpa.security.PermissionException;
import fr.afpa.security.UserPermission;

public class User {
	private String name;
	private String firstname;
	private String email;
	private String login;
	private String avatar;
	private UserPermission permission;

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
		if (name != null && name.matches("^[ a-zA-Zéèùëôûêïç\'-]{1,35}$")) {
			this.name = name;
		} else {
			throw new UserException("Le nom n'est pas dans un format valide", UserAttribute.NAME);
		}
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) throws UserException {
		if (firstname != null && firstname.matches("^[ a-zA-Zéèùëôûêïç\'-]{1,35}$")) {
			this.firstname = firstname;
		} else {
			throw new UserException("Le prenom n'est pas dans un format valide", UserAttribute.FIRSTNAME);
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws UserException {
		if (email != null && email.matches(
				"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
			this.email = email;
		} else {
			throw new UserException("L'email n'est pas dans un format valide", UserAttribute.EMAIL);
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws UserException {
		if (login != null && login.matches("^[ a-zA-Zéèùëôûêïç\'-]{1,20}$")) {
			this.login = login;
		} else {
			throw new UserException("Le login n'est pas dans un format valide", UserAttribute.LOGIN);
		}
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) throws UserException {
		if (avatar != null && avatar.matches(".*[\\.](gif|jpg|jpeg|tiff|png)$")) {
			this.avatar = avatar;
		} else if (avatar == null) {
			this.avatar = "/images/compte.png";
		} else {
			throw new UserException("L'image du profil n'est pas dans un format valide", UserAttribute.AVATAR);
		}
	}

	public static User register(String name, String firstname, String email, String password, String login,
			String avatar) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, PermissionException, UserException, InvalidKeyException, DaoException {
		User nubs = new User(name, firstname, email, login);
		nubs.setPermission(new UserPermission(Roles.User, password));
		DAOFactory.getInstance().getDaoUser().insertElement(nubs);
		DAOFactory.getInstance().getRoles().insertElement(nubs.getPermission());
		return nubs;
	}
}
