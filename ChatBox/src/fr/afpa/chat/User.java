package fr.afpa.chat;

import java.util.List;

public class User {
	private String name;
	private String firstname;
	private String email;
	private String password;
	private String login;
	private String avatar;
	private String ip_address;
	private List<Group> memberships;

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
	public User(String name, String firstname, String email, String password, String login, String ip_address)
			throws UserException {
		super();
		this.setName(name);
		this.setFirstname(firstname);
		this.setEmail(email);
		this.setPassword(password);
		this.setLogin(login);
		this.setIp_address(ip_address);
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
	public User(String name, String firstname, String email, String password, String login, String avatar,
			String ip_address, List<Group> memberships) throws UserException {
		super();
		this.setName(name);
		this.setFirstname(firstname);
		this.setEmail(email);
		this.setPassword(password);
		this.setLogin(login);
		this.setAvatar(avatar);
		this.setIp_address(ip_address);
		this.setMemberships(memberships);
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws UserException {
		this.password = password;
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

	public List<Group> getMemberships() {
		return this.memberships;
	}

	public void setMemberships(List<Group> memberships) throws UserException {
		this.memberships = memberships;
	}

}
