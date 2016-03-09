package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.FilmDAO;
import be.vdab.entities.Film;

/**
 * Servlet implementation class FilmDetailServlet
 */
@WebServlet("/filmDetail.htm")
public class FilmDetailServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW = "/WEB-INF/JSP/filmdetail.jsp";
	
	private static final FilmDAO filmDAO = new FilmDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchString = request.getParameter("film");
		if (searchString != null && !"".equals(searchString)) {
			
			Film film = filmDAO.findByTitel(searchString);
			
			if (film != null) {
				request.setAttribute("film", film);
			}
			else {
				request.setAttribute("error", "Ongeldige film als zoekopdracht. Gelieve te kiezen uit de lijst.");
			}
			
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Resource(name = FilmDAO.JNDI_NAME)
	void setDataSource (DataSource dataSource) {
		filmDAO.setDataSource(dataSource);
	}

}
