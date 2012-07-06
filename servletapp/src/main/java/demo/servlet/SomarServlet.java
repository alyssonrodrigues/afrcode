package demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SomarServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		executar(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		executar(req, resp);
	}

	protected void executar(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		String valorA = req.getParameter("valorA");
		String valorB = req.getParameter("valorB");
		
		Integer total = null;
		
		if (valorA != null && valorB != null)
		{
		  total = Integer.parseInt(valorA) + 
			  Integer.parseInt(valorB);
		}
		
		resp.sendRedirect("/servlet/jsp/somar.jsp?valorA=" + valorA + 
				"&valorB=" + valorB + "&resultado=" + total);
		
	}
	
	
}
