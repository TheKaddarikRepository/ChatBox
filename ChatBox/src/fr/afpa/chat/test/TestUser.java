/**
 * 
 */
package fr.afpa.chat.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import fr.afpa.chat.User;
import fr.afpa.chat.UserAttribute;
import fr.afpa.chat.UserException;

/**
 * @author 62018-27-15
 * @date 15 juin 2018
 */
class TestUser {

	// List of Valid Email Addresses
	private String[] goodEmails = { "email@example.com", "firstname.lastname@example.com",
			"email@subdomain.example.com", "firstname+lastname@example.com", "email@123.123.123.123",
			"email@[123.123.123.123]",
			// "“email”@example.com",
			"1234567890@example.com", "email@example-one.com", "_______@example.com", "email@example.name",
			"email@example.museum", "email@example.co.jp", "firstname-lastname@example.com",
			// "much.“more\\ unusual”@example.com",
			// "very.unusual.“@”.unusual.com@example.com",
			// "very.“(),:;<>[]”.VERY.“very@\\ “very”.unusual@strange.example.com"
	};

	// List of Invalid Email Addresses
	private String[] badEmail = { "plainaddress", "#@%^%#$@#$@#.com", "@example.com", "Joe Smith <email@example.com>",
			"email.example.com", "email@example@example.com", ".email@example.com", "email.@example.com",
			"email..email@example.com", "あいうえお@example.com", "email@example.com (Joe Smith)", "email@example",
			"email@-example.com",
			// "email@example.web",
			// "email@111.222.333.44444",
			"email@example..com", "Abc..123@example.com", "“(),:;<>[\\]@example.com", "just\"not\"right@example.com",
			"this\\ is\"really\"not\\allowed@example.com" };

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private String[] validImages = { "/root/images/photo.jpg", };

	private String[] invalidImages = { "C:\\ma photo.pdf", "/hello.bmp" };

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		thrown = ExpectedException.none();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEqual() throws UserException {
		User one = new User();
		User two = new User("Doe", "John", "jd@jd.us", "nobody");
		User three = new User("Doe", "John", "jd@jd.us", "nobody");
		assertNotNull(one);
		assertNotNull(two);
		assertFalse(one.equals(two));
		assertTrue(two.equals(three));
	}

	@Test
	void testHashCode() throws UserException {
		User one = new User();
		User two = new User("Doe", "John", "jd@jd.us", "nobody");
		assertNull(one.getName());
		assertEquals(Math.pow(31, 4), one.hashCode());
		assertTrue(two.hashCode() > Math.pow(31, 4));
	}

	@Test
	void testSetName() {
		User one = new User();
		// thrown.expect(fr.afpa.chat.UserException.class);
		// thrown.expectMessage("Le nom n'est pas dans un format valide");
		try {
			one.setName("?./:");
			fail("caractère non autorisé");
		} catch (UserException e) {
			assertEquals(e.getType(), UserAttribute.NAME);
		}
		try {
			one.setName("azertyuiopqsdfghjklmwxcvvbn'- azertyuiopqsdfghjklmwxcvbn");
			fail("trop long");
		} catch (UserException e) {
			assertNotEquals(e.getType(), null);
		}
		try {
			String str = "Luck'one de pont";
			one.setName(str);
			assertEquals(str, one.getName());
		} catch (UserException e) {
			fail("le nom est correcte");
		}
	}

	@Test
	void testSetFirstname() {
		User one = new User();
		// thrown.expect(fr.afpa.chat.UserException.class);
		// thrown.expectMessage("Le nom n'est pas dans un format valide");
		try {
			one.setFirstname("?./:");
			fail("caractère non autorisé");
		} catch (UserException e) {
			assertEquals(e.getType(), UserAttribute.FIRSTNAME);
		}
		try {
			one.setFirstname("azertyuiopqsdfghjklmwxcvvbn'- azertyuiopqsdfghjklmwxcvbn");
			fail("trop long");
		} catch (UserException e) {
			assertNotEquals(e.getType(), null);
		}
		try {
			String str = "Luck'one";
			one.setFirstname(str);
			assertEquals(str, one.getFirstname());
		} catch (UserException e) {
			fail("le prénom est correcte");
		}
	}

	@Test
	void testSetEmail() {
		User one = new User();
		// thrown.expect(fr.afpa.chat.UserException.class);
		// thrown.expectMessage("Le nom n'est pas dans un format valide");
		for (String mail : badEmail) {
			try {
				one.setEmail(mail);
				fail("email au mauvais format : " + mail);
			} catch (UserException e) {
				assertEquals(e.getType(), UserAttribute.EMAIL);
			}
		}

		for (String mail : goodEmails) {
			try {
				one.setEmail(mail);
				assertEquals(mail, one.getEmail());
			} catch (UserException e) {
				fail("Le format de l'email est correcte : " + mail);
			}
		}
	}

	@Test
	void testSetLogin() {
		User one = new User();
		// thrown.expect(fr.afpa.chat.UserException.class);
		// thrown.expectMessage("Le nom n'est pas dans un format valide");
		for (String mail : badEmail) {
			try {
				one.setEmail(mail);
				fail("email au mauvais format : " + mail);
			} catch (UserException e) {
				assertEquals(e.getType(), UserAttribute.EMAIL);
			}
		}

		for (String mail : goodEmails) {
			try {
				one.setEmail(mail);
				assertEquals(mail, one.getEmail());
			} catch (UserException e) {
				fail("Le format de l'email est correcte : " + mail);
			}
		}
	}

	@Test
	void testSetAvatar() {
		User one = new User();
		for (String image : validImages) {
			try {
				one.setAvatar(image);
				assertEquals(image, one.getAvatar());
			} catch (UserException e) {
				fail("chemin de l'image au bon format");
			}
		}
		for (String image : invalidImages) {
			try {
				one.setAvatar(image);
				fail("chemin de l'image est mauvais");
			} catch (UserException e) {
				assertEquals(e.getType(), UserAttribute.AVATAR);
			}
		}
	}

	/**
	 * Il faut penser à nettoyer la base de donnée.
	 */
	@Test
	void testRegister() {

	}
}
