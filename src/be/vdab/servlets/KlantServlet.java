package be.vdab.servlets;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.KlantDAO;
import be.vdab.entities.Klant;

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
			if (!request.getParameter("familienaam").equals("")) {
				List<Klant> gevondenKlanten = klantDAO.findByFamilienaam(request.getParameter("familienaam"));
				if (!gevondenKlanten.isEmpty()) {
					request.setAttribute("gevondenKlanten", gevondenKlanten);
				}
				else {
					request.setAttribute("fout", "Geen klanten gevonden met zoekstring");
				}
			}
			else {
				request.setAttribute("fout", "Vul minstens één letter in");
			}
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}
	
	@Resource(name = KlantDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantDAO.setDataSource(dataSource);
	}

}
