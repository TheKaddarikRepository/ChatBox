package fr.afpa.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import fr.afpa.chat.Roles;
import fr.afpa.chat.User;

public class UserPermission {
	private Integer id;
	private Roles type;
	private byte[] key;
	private byte[] password;

	/**
	 * @param type
	 * @param key
	 * @param password
	 * @throws PermissionException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public UserPermission(Roles type, String password) throws PermissionException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		super();
		this.type = type;
		this.setPassword(password);
	}

	public UserPermission(String type, byte[] key, byte[] password) throws PermissionException {
		super();
		this.setType(type);
		this.setKey(key);
		this.setPassword(password);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.type.toString();
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return this.type.getDescription();
	}

	/**
	 * @return the key
	 * @throws NoSuchAlgorithmException
	 */
	public byte[] getKey() throws NoSuchAlgorithmException {
		if (key == null) {
			KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
			SecretKey skey = kgen.generateKey();
			byte[] raw = skey.getEncoded();
			this.key = raw;
		}
		return this.key;
	}

	/**
	 * @return the password
	 */
	public byte[] getPassword() {
		return password;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setType(String name) throws PermissionException {
		final String user = "User";
		final String admin = "Admin";

		switch (name) {
		case user:
			this.type = Roles.User;
			break;
		case admin:
			this.type = Roles.Admin;
			break;
		default:
			throw new PermissionException("Not a valid role");
		}
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(byte[] key) {
		this.key = key;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(byte[] password) {
		this.password = password;
	}

	public void setPassword(String password) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("Blowfish");
		SecretKeySpec skeySpec = new SecretKeySpec(getKey(), "Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(password.getBytes());
		this.password = encrypted;
	}

	public UserPermission checkPermission(User login, String password) {

		return null;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
