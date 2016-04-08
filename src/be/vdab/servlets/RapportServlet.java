package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.FilmDAO;
import be.vdab.dao.ReservatieDAO;
import be.vdab.entities.Film;

/**
 * Servlet implementation class RapportServlet
 */
@WebServlet("/rapport.htm")
public class RapportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW = "/WEB-INF/JSP/rapport.jsp";
	private static final String REDIRECT = "%s/rapport.htm";

	private static final ReservatieDAO reservatieDAO = new ReservatieDAO();
	private static final FilmDAO filmDAO = new FilmDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// WORKAROUND TO KEEP ATTRIBUTES FROM POST REQUEST
		request.setAttribute("allSuccess", request.getSession().getAttribute("allSuccess"));
		request.setAttribute("filmGereserveerd", request.getSession().getAttribute("filmGereserveerd"));
		
		request.getSession().invalidate();

		request.getRequestDispatcher(VIEW).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		@SuppressWarnings("unchecked")
		List<Long> mandjeIDs = (List<Long>) request.getSession().getAttribute("mandjeIDs");
		Long klantID = (Long) request.getSession().getAttribute("klantID");
		if (mandjeIDs != null && klantID != null) {

			// List<String> met gelukten is efficiënter om op session te zetten
			Map<Film, String> filmGereserveerd = new HashMap<>();
			for (Long filmID : mandjeIDs) {

				Film requestedFilm = filmDAO.findByID(filmID);

				// TODO DEZE CONTROLE BETER UITVOEREN IN DAO !!!!!!!!
				if (requestedFilm.getVoorraad() > requestedFilm.getGereserveerd()
						&& reservatieDAO.insertReservatie(filmID, klantID)) {
					filmGereserveerd.put(requestedFilm, "gelukt");
				} else {
					filmGereserveerd.put(requestedFilm, "mislukt");
				}

			}

			if (!filmGereserveerd.values().contains("mislukt")) {
				request.getSession().setAttribute("allSuccess", true);
			} else {
				request.getSession().setAttribute("allSuccess", false);
				request.getSession().setAttribute("filmGereserveerd", filmGereserveerd);
			}

		}

		response.sendRedirect(String.format(REDIRECT, request.getContextPath()));

	}

	@Resource(name = ReservatieDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		reservatieDAO.setDataSource(dataSource);
		filmDAO.setDataSource(dataSource);
	}

}
