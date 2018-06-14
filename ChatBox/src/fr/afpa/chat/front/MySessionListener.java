/**
 * 
 */
package fr.afpa.chat.front;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 62018-27-15
 * @date 12 juin 2018
 */
@WebListener
public class MySessionListener implements HttpSessionListener {

	/**
	 * 
	 */
	public MySessionListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSessionListener.super.sessionCreated(se);
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSessionListener.super.sessionDestroyed(se);
	}
	
}
