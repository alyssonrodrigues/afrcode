package demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimplesHelloWorldServlet 
  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String respostaHTML = "<html><head>" +
				"<title>Simples Hello World</title>" +
				"</head><body>" +
				"Hello World!" +
				"</body></html>";
		out.write(respostaHTML);
		out.flush();
		
	}

}
