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
import fr.afpa.dao.DAOFactory;
import fr.afpa.dao.DaoException;

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
			KeyGenerator kgen = KeyGenerator.getInstance("HmacSHA256");
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
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, PermissionException {
		if (password.matches("([0-9 A-Za-z!@#$%]{8,}([0-9])([!@#$%]))")) {
			Cipher cipher = Cipher.getInstance("Blowfish");
			SecretKeySpec skeySpec = new SecretKeySpec(this.getKey(), "Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(password.getBytes());
			this.password = encrypted;
		} else {
			throw new PermissionException("Le mot de passe est trop faible.");
		}
	}

	public static UserPermission checkPermission(User login, String password) throws DaoException, PermissionException {
		UserPermission autor;
		
		if (password.matches("([0-9 A-Za-z!@#$%]{8,}([0-9])([!@#$%]))")) {
			autor = DAOFactory.getInstance().getRoles().getElementByUser(login);
			Cipher cipher;
			try {
				cipher = Cipher.getInstance("Blowfish");
				SecretKeySpec skeySpec = new SecretKeySpec(autor.getKey(), "Blowfish");
				cipher.init(Cipher.DECRYPT_MODE, skeySpec);
				byte[] decrypted = cipher.doFinal(autor.getPassword());
				if (password.equals(new String(decrypted))) {
					return autor;
				} else {
					throw new PermissionException("Le couple login/password n'existe pas.");
				}
			} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
					| BadPaddingException e) {
				throw new PermissionException("Le couple login/password n'existe pas.", e);
			}
		} else {
			throw new PermissionException("Le mot de passe est trop faible.");
		}
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

	/**
	 * @return the type
	 */
	public Roles getType() {
		return type;
	}

}
