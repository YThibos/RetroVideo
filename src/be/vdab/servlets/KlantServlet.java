package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.KlantDAO;

/**
 * Servlet implementation class KlantServlet
 */
@WebServlet("/klant.htm")
public class KlantServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW = "/WEB-INF/JSP/klant.jsp";
	
	private static final KlantDAO klantDAO = new KlantDAO();
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("familienaam") != null) {
			request.setAttribute("gevondenKlanten", klantDAO.findByFamilienaam(request.getParameter("familienaam")));
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}
	
	@Resource(name = KlantDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantDAO.setDataSource(dataSource);
	}

}
