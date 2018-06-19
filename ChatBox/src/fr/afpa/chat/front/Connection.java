package fr.afpa.chat.front;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afpa.dao.DAOFactory;
import fr.afpa.dao.DaoException;

/**
 * Servlet implementation class Connection
 */
@WebServlet(urlPatterns = {"/Connection"}, loadOnStartup=1)
public class Connection extends HttpServlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			DAOFactory.init(this.getServletContext());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
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
		
		response.getWriter().append("<p>"+request.getRequestURL()).append("</p>");
		response.getWriter().append("<p>").append(request.getProtocol().toString()).append("</p>");
		response.getWriter().append("<p>").append(request.getRemoteAddr()).append((new File(request.getServletContext().getRealPath("/WEB-INF/images/compte.png")).exists()?"TRUE":"FALSE")).append("</p>");
		request.getServletContext().getRequestDispatcher("/WEB-INF/footer.jsp").include(request, response);
		
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
