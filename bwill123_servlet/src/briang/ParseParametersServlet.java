package briang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ParseParametersServelet
 */
@WebServlet("/ParseParametersServelet")
public class ParseParametersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ParseParametersServlet() {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		printFile(out, "ResultsHeader.html");
		
		 Enumeration<String> names = request.getParameterNames();

	        if(!names.hasMoreElements()){
	          out.println("No Parameters detected.<br/>\n");

	        } else {
	          while (names.hasMoreElements()) {
	            String name = names.nextElement();
	            out.println(name + "=" + request.getParameter(name) + "<br/>\n");
	          }
	        }
		
		printFile(out, "ResultsFooter.html");
	}

	private void printFile(PrintWriter out, String filename) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)));
		String line = in.readLine();
		while(line != null){
		  out.println(line);
		  line = in.readLine();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
