package fr.afpa.chat.front;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.afpa.chat.User;
import fr.afpa.chat.UserException;
import fr.afpa.dao.DAOFactory;
import fr.afpa.dao.DaoException;
import fr.afpa.security.PermissionException;

/**
 * Servlet implementation class Registration
 */
@WebServlet(urlPatterns = { "/Registration" } )
@MultipartConfig(maxFileSize = 10*1024*1024,maxRequestSize = 20*1024*1024,fileSizeThreshold = 5*1024*1024)
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int TAILLE_TAMPON = 2048;
	public static final String CHEMIN_FICHIERS = "/images"; // A changer

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registration() {
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
		request.getServletContext().getRequestDispatcher("/WEB-INF/Register.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/WEB-INF/footer.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("e_mail");
		String pseudo = request.getParameter("pseudo");
		String pass1 = request.getParameter("pswd1");
		String pass2 = request.getParameter("pswd2");
		Part part = request.getPart("monfichier");
		String path = null;
		
		System.out.println(request.getParameterValues("nom"));
		System.out.println("nom = "+ nom +"; prenom = "+ prenom + "; email = "+ email + "; login = "+ pseudo +"; pswd = "+pass1);
		if ((nom!=null && prenom!=null && email!=null && pseudo!=null)&& (!nom.isEmpty() && !prenom.isEmpty() && !email.isEmpty() && !pseudo.isEmpty())) {
			if ( pass1.equals(pass2)) {
				if (part != null && part.getSize() > 0) {
					try {
						path = DAOFactory.getInstance().getImage().saveImage(part, request.getServletContext());
						System.out.println(path);
					} catch (DaoException e) {
						request.setAttribute("avatar_error", e);
					}
				}
				try {
					User.register(nom, prenom, email, pass1, pseudo, path);
				} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
					try {
						DAOFactory.getInstance().getImage().removeImage(path, request.getServletContext());
					} catch (DaoException e1) {
					}
				} catch (PermissionException e) {
					request.setAttribute("password_error", e);
					try {
						DAOFactory.getInstance().getImage().removeImage(path, request.getServletContext());
					} catch (DaoException e1) {
					}
					
				} catch (UserException e) {
					switch (e.getType()) {
					case LOGIN:
						request.setAttribute("login_error", e);
						break;
					case NAME:
						request.setAttribute("name_error", e);
						break;
					case FIRSTNAME:
						request.setAttribute("firstname_error", e);
						break;
					case EMAIL:
						request.setAttribute("email_error", e);
						break;
					case AVATAR:
						request.setAttribute("avatar_error", e);
						break;
					}
					try {
						DAOFactory.getInstance().getImage().removeImage(path, request.getServletContext());
					} catch (DaoException e1) {
					}
				} catch (DaoException e) {
					request.setAttribute("login_error", e);
					try {
						DAOFactory.getInstance().getImage().removeImage(path, request.getServletContext());
					} catch (DaoException e1) {
					}
				}
			}
		}
		doGet(request, response);
	}

}
