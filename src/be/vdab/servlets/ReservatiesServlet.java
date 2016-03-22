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

import be.vdab.dao.FilmDAO;
import be.vdab.dao.GenreDAO;
import be.vdab.entities.Film;
import be.vdab.entities.Genre;

/**
 * Servlet implementation class ReservatiesServlet
 */
@WebServlet(description = "Welkomstpagina", urlPatterns = { "/index.htm" }, name = "ReservatiesServlet")
public class ReservatiesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW = "/WEB-INF/JSP/reservaties.jsp";
	
	// TODO Interface type
	private static final GenreDAO genreDAO = new GenreDAO();
	private static final FilmDAO filmDAO = new FilmDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// FIND ALL GENRES & PASS TO REQUEST FOR LISTING
		List<Genre> genres = genreDAO.findAllGenres();
		request.setAttribute("genres", genres);
		
		// IF A GENRE IS CLICKED, DO THINGS
		String selectedGenre = request.getParameter("genre");
		if (selectedGenre != null && !"".equals(selectedGenre)) {
			
			List<Film> filmsByGenre = filmDAO.findByGenre(selectedGenre);
			request.setAttribute("filmsByGenre", filmsByGenre);
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Resource(name = GenreDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		genreDAO.setDataSource(dataSource);
		filmDAO.setDataSource(dataSource);
	}

}
