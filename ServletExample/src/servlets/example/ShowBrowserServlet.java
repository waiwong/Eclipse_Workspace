package servlets.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBrowserServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userAgent = req.getHeader("user-agent");
		String clientBrowser = "Not known!";
		if (userAgent != null)
			clientBrowser = userAgent;
		req.setAttribute("client.browser", clientBrowser);
		req.getRequestDispatcher("/showBrowser.jsp").forward(req, resp);
	}
}
