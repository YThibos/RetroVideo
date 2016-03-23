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

/**
 * Servlet implementation class BevestigenServlet
 */
@WebServlet("/bevestigen.htm")
public class BevestigenServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW = "/WEB-INF/JSP/bevestigen.jsp";
	
	private static final KlantDAO klantDAO = new KlantDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Long> mandjeIDs = (List<Long>) request.getSession().getAttribute("mandjeIDs");
		
		if (mandjeIDs != null) {
			request.setAttribute("filmCount", mandjeIDs.size());
			
			request.setAttribute("klant", klantDAO.findByID(Long.parseLong(request.getParameter("klant"))));
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}
	
	@Resource(name = KlantDAO.JNDI_NAME)
	void setDataSource (DataSource dataSource) {
		klantDAO.setDataSource(dataSource);
	}

}
