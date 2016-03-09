package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MandjeServlet
 */
@WebServlet("/mandje.htm")
public class MandjeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
	private static final String REDIRECT_URL = "%s/mandje.htm";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("id") != null) {
			
			// FETCH MANDJE FROM SESSION
			@SuppressWarnings("unchecked")
			List<Long> mandjeIDs = (List<Long>) request.getSession().getAttribute("mandje");
			
			// IF NO SESSION YET, OR SESSION HAD NO MANDJE: MAKE ONE
			if (mandjeIDs == null) {
				mandjeIDs = new ArrayList<>();
			}
			
			mandjeIDs.add(Long.parseLong(request.getParameter("id")));
			
			request.getSession().setAttribute("mandje", mandjeIDs);
			
			// TODO FOREACH ID IN MANDJE: GET MATCHING FILM
			mandjeIDs.stream();
			
		}
		
		response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
		
	}

}
