package fr.afpa.chat.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.afpa.chat.Roles;
import fr.afpa.security.PermissionException;
import fr.afpa.security.UserPermission;

class TestPermission {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetKey() {
		String pswd = new String("myPassword");
		try {
			UserPermission role = new UserPermission(Roles.User, pswd);
			Cipher cipher = Cipher.getInstance("Blowfish");
			SecretKeySpec skeySpec = new SecretKeySpec(role.getKey(), "Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] decrypted = cipher.doFinal(role.getPassword());
			String pass = new String(decrypted);
			assertEquals(pswd, pass);
			assertNotEquals(pswd, role.getPassword());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PermissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testSetPassword_str() {
		String pswd = new String("myPassword1%");
		String pswd2 = new String("myNewPassword1%");
		String pswd3 = new String("not Good");
		UserPermission role;
		try {
			role = new UserPermission(Roles.User, pswd);
			role.setPassword(pswd2);
			Cipher cipher = Cipher.getInstance("Blowfish");
			SecretKeySpec skeySpec = new SecretKeySpec(role.getKey(), "Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] decrypted = cipher.doFinal(role.getPassword());
			String pass = new String(decrypted);
			assertEquals(pswd2, pass);
			assertNotEquals(pswd2, role.getPassword());
			assertNotEquals(pswd, pass);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | PermissionException e) {
			fail("the password did not change");
		}
		try {
			role = new UserPermission(Roles.User, pswd);
			role.setPassword(pswd3);
			fail("the password dose not match");
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | PermissionException exp) {
			assertEquals(exp.getClass(), PermissionException.class);
		}
	}

	@Test
	void testSetType() {
		String pswd = new String("myPassword1%");
		UserPermission role;

		try {
			role = new UserPermission(Roles.User, pswd);
			role.setType("Admin");
			assertEquals(Roles.Admin, role.getType());
		} catch (PermissionException e) {
			fail("User roles did match the enum name");
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			fail("unexpected exception");
		}
		try {
			role = new UserPermission(Roles.User, pswd);
			role.setType("merde");
			fail("User roles did NOT match the enum name");
		} catch (PermissionException e) {
			assertEquals(e.getMessage(), "Not a valid role");
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			fail("unexpected exception");
		}
	}

	@Test
	void testGetText() {
		String pswd = new String("myPassword1%");
		UserPermission role;
		try {
			role = new UserPermission(Roles.User, pswd);
			assertEquals("Un utilisateur du site de chat. Il ne peut administrer que les groups qu'il à créer ou son groupe d'amis.", role.getText());
			role.setType("Admin");
			assertEquals("Un utilisateur pouvant administrer les groupes publique.", role.getText());
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | PermissionException e) {
			fail("unexpected exception");
		}
	}

}
