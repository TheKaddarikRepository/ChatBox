package fr.afpa.chat.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afpa.dao.DAOFactory;

/**
 * Servlet implementation class Connection
 */

public class Connection extends HttpServlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		DAOFactory.getInstance(this.getServletContext());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6685801861716974340L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connection() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getServletContext().getRequestDispatcher("/WEB-INF/Title.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/WEB-INF/Header.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/WEB-INF/Menu.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").include(request, response);
		response.getWriter().append(request.getRequestURL()).append("<br/>");
		response.getWriter().append("Served at: ").append(request.getContextPath()).append("<br/>");
		response.getWriter().append(request.getProtocol().toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
