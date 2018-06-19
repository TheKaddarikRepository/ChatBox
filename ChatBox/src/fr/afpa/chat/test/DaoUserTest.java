package fr.afpa.chat.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.afpa.dao.DaoException;

class DaoUserTest {
	private static Connection connexion;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		connexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/chatbox","root","root");
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
	void testGetListOfElements() {
		
	}

	@Test
	void testInsertElement() {
		
	}

	@Test
	void testRemoveElement() {
		
	}

	@Test
	void updateElement() {
		
	}
}
