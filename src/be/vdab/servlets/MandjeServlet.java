package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
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

/**
 * Servlet implementation class MandjeServlet
 */
@WebServlet("/mandje.htm")
public class MandjeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
	private static final String REDIRECT_URL = "%s/mandje.htm";

	private static final FilmDAO filmDAO = new FilmDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// FETCH MANDJE FROM SESSION
		@SuppressWarnings("unchecked")
		List<Long> mandjeIDs = (List<Long>) request.getSession().getAttribute("mandjeIDs");
		
		request.setAttribute("filmsInMandje", filmDAO.findMultipleIds(mandjeIDs));

		request.getRequestDispatcher(VIEW).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// FETCH MANDJE FROM SESSION
		@SuppressWarnings("unchecked")
		List<Long> mandjeIDs = (List<Long>) request.getSession().getAttribute("mandjeIDs");

		// CHECK IF POST SENT AN ID TO ADD TO MANDJE
		if (request.getParameter("id") != null) {

			Long addedID = Long.parseLong(request.getParameter("id"));
			
			// IF NO SESSION YET, OR SESSION HAD NO MANDJE: MAKE ONE
			if (mandjeIDs == null) {
				mandjeIDs = new ArrayList<>();
			}

			// ADD NEW ID TO MANDJE IF NOT IN IT YET
			if (!mandjeIDs.contains(addedID)) {
				mandjeIDs.add(addedID);
			}

		}
		
		// CHECK IF POST SENT ID's TO REMOVE FROM MANDJE
		if (request.getParameterValues("remove") != null) {
			
			// REMOVE EACH ID PARAMETER FROM MANDJE
			for (String idString : request.getParameterValues("remove")) {
				mandjeIDs.remove(Long.parseLong(idString));
			}
			
		}
		
		// SAVE NEW MANDJE TO SESSION
		request.getSession().setAttribute("mandjeIDs", mandjeIDs);

		response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));

	}
	
	@Resource(name = FilmDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		filmDAO.setDataSource(dataSource);
	}

}
