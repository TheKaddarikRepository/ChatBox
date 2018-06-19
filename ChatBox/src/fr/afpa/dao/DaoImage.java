/**
 * 
 */
package fr.afpa.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

/**
 * @author 62018-27-15
 * @date 18 juin 2018
 */
public class DaoImage implements IntDAOImage {
	public static final int TAILLE_TAMPON = 2048;
	public static final String CHEMIN_FICHIERS = "/images/"; // A changer

	/**
	 * 
	 */
	public DaoImage() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.afpa.dao.DAOImplementation#insertElement(java.lang.Object)
	 */
	@Override
	public String saveImage(Part element, ServletContext servletContext) throws DaoException {
		String nomFichier = getNomFichier(element);
		if (nomFichier != null && !nomFichier.isEmpty()) {
			try (BufferedInputStream entree = new BufferedInputStream(element.getInputStream(), TAILLE_TAMPON);
					BufferedOutputStream sortie = new BufferedOutputStream(
							new FileOutputStream(new File(servletContext.getRealPath(CHEMIN_FICHIERS + nomFichier))),
							TAILLE_TAMPON)) {
				byte[] tampon = new byte[TAILLE_TAMPON];
				int longueur;
				while ((longueur = entree.read(tampon)) > 0) {
					sortie.write(tampon, 0, longueur);
				}
			} catch (IOException e) {
				throw new DaoException("Le fichier n'as pas pue etre enregistré.", e);
			}

			return CHEMIN_FICHIERS + nomFichier;
		} else {
			throw new DaoException("Aucun fichier n'as été reçus", null);
		}

	}

	private static String getNomFichier(Part part) {
		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
			if (contentDisposition.trim().startsWith("filename")) {
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	@Override
	public String removeImage(String path, ServletContext servletContext) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
}
