package servlets.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SessionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// Set the response message's MIME type
		response.setContentType("text/html;charset=UTF-8");
		// Allocate a output writer to write the response message into the
		// network socket
		PrintWriter out = response.getWriter();

		// Return the existing session if there is one. Create a new session
		// otherwise.
		HttpSession session = request.getSession();
		Integer accessCount;
		synchronized (session) {
			accessCount = (Integer) session.getAttribute("accessCount");
			if (accessCount == null) {
				accessCount = 0; // autobox int to Integer
			} else {
				accessCount = new Integer(accessCount + 1);
			}
			session.setAttribute("accessCount", accessCount);
		}

		// Write the response message, in an HTML page
		try {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			out.println("<title>Session Test Servlet</title></head><body>");
			out.println("<h2>You have access this site " + accessCount
					+ " times in this session.</h2>");
			out.println("<p>(Session ID is " + session.getId() + ")</p>");

			out.println("<p>(Session creation time is "
					+ new Date(session.getCreationTime()) + ")</p>");
			out.println("<p>(Session last access time is "
					+ new Date(session.getLastAccessedTime()) + ")</p>");
			out.println("<p>(Session max inactive interval  is "
					+ session.getMaxInactiveInterval() + " seconds)</p>");

			out.println("<p><a  href='" + request.getRequestURI()
					+ "'>Refresh</a>");
			out.println("<p><a  href='"
					+ response.encodeURL(request.getRequestURI())
					+ "'>Refresh with  URL rewriting</a>");
			out.println("</body></html>");
		} finally {
			out.close(); // Always close the output writer
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
